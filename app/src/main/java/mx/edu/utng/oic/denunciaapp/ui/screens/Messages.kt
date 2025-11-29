package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import mx.edu.utng.oic.denunciaapp.data.model.Message as MessageModel
import mx.edu.utng.oic.denunciaapp.data.service.ForoService
import mx.edu.utng.oic.denunciaapp.data.service.MessageService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.MessageViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.MessageViewModelFactory

val AppPrimaryColor = Color(0xFF1976D2)
val AppDividerColor = Color(0xFFE0E0E0)
val ReplyButtonColor = Color(0xFF43A047)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesScreen(
    onBack: () -> Unit,
    onOpenDrawer: () -> Unit,
    forumId: String
) {
    // --- 1. Inicializar ViewModel y Observar Estados ---
    val messageService = remember { MessageService() }
    val foroService = remember { ForoService() }
    val userService = remember { UserService() }

    // ⭐️ AJUSTE: Si el forumId se usa para inicializar la fábrica, debe estar disponible.
    // Aunque la fábrica solo usa los servicios, es importante la coherencia.
    val factory = remember { MessageViewModelFactory(messageService, foroService, userService) }
    val viewModel: MessageViewModel = viewModel(factory = factory)

    val messagesList by viewModel.messages.collectAsState()
    val currentForum by viewModel.forum.collectAsState()
    val currentUserId by viewModel.currentUserId.collectAsState()
    val error by viewModel.error.collectAsState()
    val isSending by viewModel.isSending.collectAsState()

    var messageText by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    LaunchedEffect(forumId) {
        viewModel.startListeningForMessages(forumId)
        viewModel.fetchForumDetails(forumId)
    }

    LaunchedEffect(error) {
        error?.let { errorMsg ->
            scope.launch {
                snackbarHostState.showSnackbar(errorMsg, duration = SnackbarDuration.Long)
                viewModel.clearError()
            }
        }
    }

    LaunchedEffect(messagesList.size) {
        if (messagesList.isNotEmpty()) {
            listState.animateScrollToItem(messagesList.size - 1)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        // Muestra el tema del foro o "Cargando..."
                        currentForum?.tema ?: "Cargando Foro...",
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Abrir menú lateral")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        bottomBar = {
            MessageInput(
                messageText = messageText,
                onMessageChange = { messageText = it },
                onSend = {
                    if (messageText.isNotBlank() && currentForum != null) {
                        viewModel.sendMessage(currentForum!!.id, messageText)
                        messageText = ""
                    }
                },
                // Habilitado si no está enviando y si el usuario actual está identificado
                isEnabled = !isSending && currentUserId != null
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Cabecera con datos del Foro Original (Simplificada)
            currentForum?.let {
                PostHeader(
                    forumName = it.username,
                    dateTime = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(it.creationDate),
                    isSimplified = true
                )
            }
            Divider(color = AppDividerColor, thickness = 1.dp)

            // Columna para las Respuestas (Chat)
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                state = listState,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (messagesList.isEmpty()) {
                    item {
                        if (!currentForum?.tema.isNullOrBlank()) {
                            Text(
                                text = "Tema del Foro: ${currentForum!!.tema}",
                                color = WireframeGray,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                            )
                            Divider(color = AppDividerColor)
                            Text(
                                text = "Aún no hay respuestas. ¡Sé el primero en participar!",
                                color = WireframeGray,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 32.dp),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        } else {
                            // Muestra el indicador si el foro aún está cargando
                            Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }

                items(messagesList, key = { it.id }) { message ->
                    ChatMessageItem(
                        message = message,
                        isCurrentUser = message.userId == currentUserId,
                        username = "Usuario ${message.userId.take(4)}..."
                    )
                }
            }
        }
    }
}

// ----------------------------------------------------------------------
// --- COMPONENTES AUXILIARES ---
// ----------------------------------------------------------------------

@Composable
fun PostHeader(forumName: String, dateTime: String, isSimplified: Boolean = false) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE3F2FD))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        if (isSimplified) {
            Text(
                text = "Creador del Post: $forumName",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Publicado: $dateTime",
                fontSize = 12.sp,
                color = Color.DarkGray
            )
        } else {
            // Lógica para la versión no simplificada (si la conservas)
        }
    }
}


@Composable
fun ChatMessageItem(message: MessageModel, isCurrentUser: Boolean, username: String) {
    val bubbleColor = if (isCurrentUser) Color(0xFFDCF8C6) else Color(0xFFF0F0F0)
    val alignment = if (isCurrentUser) Alignment.End else Alignment.Start
    val paddingStart = if (isCurrentUser) 60.dp else 0.dp
    val paddingEnd = if (isCurrentUser) 0.dp else 60.dp
    val timeFormatter = remember { SimpleDateFormat("HH:mm", Locale.getDefault()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 4.dp),
        horizontalAlignment = alignment
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = bubbleColor),
            modifier = Modifier
                .padding(start = paddingStart, end = paddingEnd)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                if (!isCurrentUser) {
                    Text(
                        text = username,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = AppPrimaryColor
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }
                Text(text = message.contenido, fontSize = 14.sp)
            }
        }

        Text(
            text = timeFormatter.format(message.dateTime),
            fontSize = 10.sp,
            color = WireframeGray,
            modifier = Modifier.padding(top = 4.dp, end = 8.dp, start = 8.dp)
        )
    }
}


@Composable
fun MessageInput(messageText: String, onMessageChange: (String) -> Unit, onSend: () -> Unit, isEnabled: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(1.dp, AppDividerColor)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Usamos BasicTextField para evitar el conflicto de Material3
        BasicTextField(
            value = messageText,
            onValueChange = onMessageChange,
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFFF7F7F7), RoundedCornerShape(24.dp))
                .border(1.dp, AppDividerColor, RoundedCornerShape(24.dp))
                .height(48.dp), // Altura fija similar a OutlinedTextField
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp), // Estilo del texto
            enabled = isEnabled,
            singleLine = true,
            // decorationBox nos permite añadir el placeholder y el padding interno
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (messageText.isEmpty()) {
                        Text(
                            "Escribe tu respuesta...",
                            color = WireframeGray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.width(8.dp))
        FloatingActionButton(
            onClick = onSend,
            containerColor = ReplyButtonColor,
            shape = CircleShape,
            modifier = Modifier.size(48.dp),

        ) {
            Icon(Icons.Default.Send, contentDescription = "Enviar", tint = Color.White)
        }
    }
}

