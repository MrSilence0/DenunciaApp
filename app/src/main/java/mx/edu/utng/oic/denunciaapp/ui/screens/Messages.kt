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
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.MessageViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.MessageViewModelFactory
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesScreen(
    onBack: () -> Unit,
    onOpenDrawer: () -> Unit,
    forumId: String
) {
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary
    val surfaceColor = colorScheme.surface
    val onSurfaceColor = colorScheme.onSurface
    val backgroundColor = colorScheme.background
    val outlineColor = colorScheme.outline
    val onSurfaceVariantColor = colorScheme.onSurfaceVariant
    val primaryContainerColor = colorScheme.primaryContainer
    val onPrimaryContainerColor = colorScheme.onPrimaryContainer
    val surfaceVariantColor = colorScheme.surfaceVariant
    val onSurfaceVariantColor2 = colorScheme.onSurfaceVariant
    val secondaryContainerColor = colorScheme.secondaryContainer
    val onSecondaryContainerColor = colorScheme.onSecondaryContainer
    val tertiaryColor = colorScheme.tertiary // Mapeo de ReplyButtonColor


    val messageService = remember { MessageService() }
    val foroService = remember { ForoService() }
    val userService = remember { UserService() }

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
                        currentForum?.tema ?: "Cargando Foro...",
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        color = onSurfaceColor
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = onSurfaceColor)
                    }
                },
                actions = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Abrir menú lateral", tint = onSurfaceColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor
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
                isEnabled = !isSending && currentUserId != null,
                surfaceColor = surfaceColor,
                onSurfaceColor = onSurfaceColor,
                outlineColor = outlineColor,
                tertiaryColor = tertiaryColor,
                onTertiaryColor = colorScheme.onTertiary,
                surfaceVariantColor = surfaceVariantColor,
                onSurfaceVariantColor = onSurfaceVariantColor
            )
        },
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Cabecera con datos del Foro Original
            currentForum?.let {
                PostHeader(
                    forumName = it.username,
                    dateTime = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(it.creationDate),
                    isSimplified = true,
                    onSurfaceColor = onSurfaceColor,
                    onSurfaceVariantColor = onSurfaceVariantColor,
                    secondaryContainerColor = secondaryContainerColor,
                    onSecondaryContainerColor = onSecondaryContainerColor
                )
            }
            Divider(color = outlineColor, thickness = 1.dp)

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
                                color = onSurfaceVariantColor,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                            )
                            Divider(color = outlineColor)
                            Text(
                                text = "Aún no hay respuestas. ¡Sé el primero en participar!",
                                color = onSurfaceVariantColor,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 32.dp),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        } else {
                            // Muestra el indicador si el foro aún está cargando
                            Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator(color = primaryColor)
                            }
                        }
                    }
                }

                items(messagesList, key = { it.id }) { message ->
                    ChatMessageItem(
                        message = message,
                        isCurrentUser = message.userId == currentUserId,
                        username = "Usuario ${message.userId.take(4)}...",
                        primaryColor = primaryColor,
                        primaryContainerColor = primaryContainerColor,
                        onPrimaryContainerColor = onPrimaryContainerColor,
                        surfaceVariantColor = surfaceVariantColor,
                        onSurfaceColor = onSurfaceColor,
                        onSurfaceVariantColor = onSurfaceVariantColor
                    )
                }
            }
        }
    }
}

@Composable
fun PostHeader(
    forumName: String,
    dateTime: String,
    isSimplified: Boolean = false,
    onSurfaceColor: Color,
    onSurfaceVariantColor: Color,
    secondaryContainerColor: Color,
    onSecondaryContainerColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(secondaryContainerColor)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        if (isSimplified) {
            Text(
                text = "Creador del Post: $forumName",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = onSecondaryContainerColor
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Publicado: $dateTime",
                fontSize = 12.sp,
                color = onSecondaryContainerColor.copy(alpha = 0.8f)
            )
        } else {
        }
    }
}


@Composable
fun ChatMessageItem(
    message: MessageModel,
    isCurrentUser: Boolean,
    username: String,
    primaryColor: Color,
    primaryContainerColor: Color,
    onPrimaryContainerColor: Color,
    surfaceVariantColor: Color,
    onSurfaceColor: Color,
    onSurfaceVariantColor: Color
) {
    val bubbleColor = if (isCurrentUser) primaryContainerColor else surfaceVariantColor
    val contentColor = if (isCurrentUser) onPrimaryContainerColor else onSurfaceColor
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
                        color = primaryColor
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }
                Text(text = message.contenido, fontSize = 14.sp, color = contentColor)
            }
        }

        Text(
            text = timeFormatter.format(message.dateTime),
            fontSize = 10.sp,
            color = onSurfaceVariantColor,
            modifier = Modifier.padding(top = 4.dp, end = 8.dp, start = 8.dp)
        )
    }
}


@Composable
fun MessageInput(
    messageText: String,
    onMessageChange: (String) -> Unit,
    onSend: () -> Unit,
    isEnabled: Boolean,
    surfaceColor: Color,
    onSurfaceColor: Color,
    outlineColor: Color,
    tertiaryColor: Color,
    onTertiaryColor: Color,
    surfaceVariantColor: Color,
    onSurfaceVariantColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(surfaceColor)
            .border(1.dp, outlineColor)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = messageText,
            onValueChange = onMessageChange,
            modifier = Modifier
                .weight(1f)
                .background(surfaceVariantColor, RoundedCornerShape(24.dp))
                .border(1.dp, outlineColor, RoundedCornerShape(24.dp))
                .height(48.dp),
            textStyle = TextStyle(color = onSurfaceColor, fontSize = 16.sp),
            enabled = isEnabled,
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (messageText.isEmpty()) {
                        Text(
                            "Escribe tu respuesta...",
                            color = onSurfaceVariantColor,
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
            containerColor = tertiaryColor,
            shape = CircleShape,
            modifier = Modifier.size(48.dp),

            ) {
            Icon(Icons.Default.Send, contentDescription = "Enviar", tint = onTertiaryColor)
        }
    }
}