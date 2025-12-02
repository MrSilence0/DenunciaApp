package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import mx.edu.utng.oic.denunciaapp.R
import mx.edu.utng.oic.denunciaapp.data.model.Foro as ForoModel
import mx.edu.utng.oic.denunciaapp.data.service.ForoService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray // Mantener si WireframeGray es necesario para placeholders
import mx.edu.utng.oic.denunciaapp.ui.theme.White
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.ForoViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.ForoViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForosPageScreen(
    onNavigateToCreateForum: () -> Unit,
    onNavigateTo: (String) -> Unit,
    onOpenMenu: () -> Unit
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val onTertiaryColor = MaterialTheme.colorScheme.onTertiary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val surfaceColor = MaterialTheme.colorScheme.surface
    val backgroundColor = MaterialTheme.colorScheme.background

    val foroService = remember { ForoService() }
    val userService = remember { UserService() }
    val factory = remember { ForoViewModelFactory(foroService, userService) }
    val viewModel: ForoViewModel = viewModel(factory = factory)

    val forosList by viewModel.foros.collectAsState()
    val currentUserId by viewModel.currentUserId.collectAsState()
    val isLoading by viewModel.isLoadingForos.collectAsState()
    val operationError by viewModel.operationError.collectAsState()
    val isDeleting by viewModel.isProcessing.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // 2. Manejar mensajes de error
    LaunchedEffect(operationError) {
        operationError?.let { errorMsg ->
            scope.launch {
                snackbarHostState.showSnackbar(errorMsg, duration = SnackbarDuration.Long)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadForos()
    }


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(surfaceColor)
                    .padding(16.dp)
            ) {
                IconButton(
                    onClick = onOpenMenu,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Abrir menú",
                        modifier = Modifier.size(32.dp),
                        tint = onSurfaceColor
                    )
                }
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.denunciaappicon),
                        contentDescription = "Logo DenunciaApp",
                        modifier = Modifier.size(50.dp)
                    )
                    Text("DenunciaApp", fontSize = 10.sp, color = White, fontWeight = FontWeight.Bold)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToCreateForum,
                containerColor = tertiaryColor,
                shape = CircleShape,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Nuevo tema",
                    tint = onTertiaryColor
                )
            }
        },
        containerColor = backgroundColor
    ) { paddingValues ->

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = primaryColor)
            }
            return@Scaffold
        }

        if (forosList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Text("No hay foros disponibles. ¡Sé el primero en crear uno!", color = onSurfaceColor)
            }
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundColor),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(forosList, key = { it.id }) { foro ->
                ForumItem(
                    foro = foro,
                    currentUserId = currentUserId,
                    onDelete = {
                        if (!isDeleting) {
                            viewModel.deleteForo(foro.id)
                        }
                    },
                    onClick = {
                        onNavigateTo("messages_page_screen/${foro.id}")
                    },
                    primaryColor = primaryColor,
                    onSurfaceColor = onSurfaceColor,
                    onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    surfaceVariantColor = MaterialTheme.colorScheme.surfaceVariant,
                    outlineColor = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@Composable
fun ForumItem(
    foro: ForoModel,
    currentUserId: String?,
    onDelete: (String) -> Unit,
    onClick: () -> Unit,
    primaryColor: Color,
    onSurfaceColor: Color,
    onSurfaceVariantColor: Color,
    surfaceVariantColor: Color,
    outlineColor: Color
) {
    val isOwner = foro.idUser == currentUserId

    val deleteIconColor = Color(0xFFD32F2F)
    val deleteContainerColor = Color(0xFFF44336).copy(alpha = 0.1f)

    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = surfaceVariantColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(primaryColor.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Forum,
                        contentDescription = "Icono de foro",
                        tint = primaryColor,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Título y Creador
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = foro.tema,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = onSurfaceColor,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    // Creador
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Autor",
                            tint = onSurfaceVariantColor,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Autor: ${foro.username}",
                            fontSize = 12.sp,
                            color = onSurfaceVariantColor
                        )
                    }
                }

                // Fecha de creación
                val dateFormatter = remember { SimpleDateFormat("dd/MM/yy", Locale.getDefault()) }
                Text(
                    text = dateFormatter.format(foro.creationDate),
                    fontSize = 12.sp,
                    color = onSurfaceVariantColor,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = outlineColor)
            Spacer(modifier = Modifier.height(12.dp))

            // --- 2. Pie de la tarjeta: Respuestas y Botón Responder/Borrar ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Contador de Respuestas
                Text(
                    text = "${foro.responseCount} respuestas",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = primaryColor
                )

                // Botones de Acción
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    // BOTÓN DE BORRADO CONDICIONAL
                    if (isOwner) {
                        IconButton(
                            onClick = { onDelete(foro.id) },
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                                // Se mantienen los colores fijos para el botón de ELIMINAR (rojo)
                                .background(deleteContainerColor)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar Foro",
                                tint = deleteIconColor
                            )
                        }
                    }

                    // Botón Responder (Navega al detalle)
                    OutlinedButton(
                        onClick = onClick,
                        shape = RoundedCornerShape(8.dp),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = SolidColor(primaryColor)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Responder",
                            modifier = Modifier.size(18.dp).padding(end = 4.dp),
                            tint = primaryColor
                        )
                        Text(
                            "Responder",
                            color = primaryColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ForosPageScreenPreview() {
    MaterialTheme {
        ForosPageScreen(
            onNavigateToCreateForum = {},
            onNavigateTo = {},
            onOpenMenu = {}
        )
    }
}