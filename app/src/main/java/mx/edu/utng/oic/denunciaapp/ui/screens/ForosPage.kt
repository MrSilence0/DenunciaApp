package mx.edu.utng.oic.denunciaapp.ui.screens

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import mx.edu.utng.oic.denunciaapp.data.model.Foro as ForoModel
import mx.edu.utng.oic.denunciaapp.data.service.ForoService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.ForoViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.ForoViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// --- Definiciones de Colores (Añadidos para completar el código) ---
val FABColor = Color(0xFFFFC107) // Amarillo para el FAB
val CardBackground = Color(0xFFF0F0F0) // Fondo de tarjeta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForosPageScreen(
    onNavigateToCreateForum: () -> Unit,
    onNavigateTo: (String) -> Unit
) {
    // --- 1. Inicializar ViewModel y Observar Estados ---
    // NOTA: En una app real, los servicios se inyectarían. Usamos remember para simular el singleton
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

    // 3. ⭐️ CORRECCIÓN CLAVE: Usar LaunchedEffect(Unit)
    // Esto asegura que loadForos() se llame cada vez que la pantalla
    // se vuelve activa (al entrar o al volver de la navegación).
    LaunchedEffect(Unit) {
        viewModel.loadForos()
    }


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Foros", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                actions = {
                    IconButton(onClick = { /* Lógica de búsqueda */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar foro"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToCreateForum,
                containerColor = FABColor,
                shape = CircleShape,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Nuevo tema",
                    tint = Color.Black
                )
            }
        },
        containerColor = Color.White
    ) { paddingValues ->

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        if (forosList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Text("No hay foros disponibles. ¡Sé el primero en crear uno!")
            }
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
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
                        onNavigateTo("foro_detalle/${foro.id}")
                    }
                )
            }
        }
    }
}

// --- Componente de Item de Foro Individual ---
@Composable
fun ForumItem(
    foro: ForoModel,
    currentUserId: String?,
    onDelete: (String) -> Unit,
    onClick: () -> Unit
) {
    val isOwner = foro.idUser == currentUserId

    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            // --- 1. Contenido principal y meta data ---
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Icono de Foro (Izquierda)
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.Gray.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Forum,
                        contentDescription = "Icono de foro",
                        tint = Color.White,
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
                        color = PrimaryBlue,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    // Creador
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Autor",
                            tint = WireframeGray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Autor: ${foro.username}",
                            fontSize = 12.sp,
                            color = WireframeGray
                        )
                    }
                }

                // Fecha de creación
                val dateFormatter = remember { SimpleDateFormat("dd/MM/yy", Locale.getDefault()) }
                Text(
                    text = dateFormatter.format(foro.creationDate),
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color.LightGray)
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
                    color = PrimaryBlue
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
                                .background(Color(0xFFF44336).copy(alpha = 0.1f)) // Rojo claro
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar Foro",
                                tint = Color(0xFFD32F2F) // Rojo oscuro
                            )
                        }
                    }

                    // Botón Responder (Navega al detalle)
                    OutlinedButton(
                        onClick = onClick,
                        shape = RoundedCornerShape(8.dp),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = SolidColor(PrimaryBlue)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Responder",
                            modifier = Modifier.size(18.dp).padding(end = 4.dp),
                            tint = PrimaryBlue
                        )
                        Text(
                            "Responder",
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

// Para que el código compile y se pueda previsualizar (si tienes acceso a tus ViewModels)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ForosPageScreenPreview() {
    // Necesitarías implementar una versión mock de los servicios o usar un ViewModelFactory simple
    // para que la previsualización funcione fuera del contexto de la app.
    // Por simplicidad, se deja la estructura, pero esto puede fallar si no hay mocks.
    // ForosPageScreen( onNavigateToCreateForum = {}, onNavigateTo = {} )

    // Simulación simple para Preview:
    ForumItem(
        foro = ForoModel(
            id = "1",
            tema = "Ejemplo de Foro Creado por Usuario",
            username = "AdminUser",
            creationDate = Date(),
            responseCount = 5,
            idUser = "user123" // ID del usuario creador
        ),
        currentUserId = "user123", // Coincide para mostrar el botón Delete
        onDelete = {},
        onClick = {}
    )
}