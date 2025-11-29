package mx.edu.utng.oic.denunciaapp.ui.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import mx.edu.utng.oic.denunciaapp.data.model.User
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.UserProfileViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.UserProfileViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

// ⚠️ IMPORTACIONES DE MAPS ELIMINADAS

// ⚠️ IMPORTAR LAS FUNCIONES COMUNES DE TU PAQUETE:
import mx.edu.utng.oic.denunciaapp.ui.components.GenderOption
import mx.edu.utng.oic.denunciaapp.ui.components.ImagePlaceholder
import mx.edu.utng.oic.denunciaapp.ui.components.LabelText
import mx.edu.utng.oic.denunciaapp.ui.components.OutlinedTextFieldWithDialog
import mx.edu.utng.oic.denunciaapp.ui.components.SimpleOutlinedTextField
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray


val HeaderBlue = Color(0xFF42A5F5)
val StarYellow = Color(0xFFFFCC00)
val StarGray = Color(0xFFE0E0E0)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    onNavigateBack: () -> Unit
) {
    // --- 1. Inicializar ViewModel y Factory ---
    val userService = remember { UserService() }
    val factory = remember { UserProfileViewModelFactory(userService) }
    val viewModel: UserProfileViewModel = viewModel(factory = factory)

    // --- 2. Observar Estados del ViewModel ---
    val user: User = viewModel.userState
    val isLoading = viewModel.isLoading
    val isSaving = viewModel.isSaving
    val feedbackMessage = viewModel.feedbackMessage

    // --- 3. Estados de Lógica de UI ---
    var showPhotoDialog by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    // ESTADO ELIMINADO: var showMapSelection by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    // val context = LocalContext.current // No se usa si se elimina la ubicación

    // --- ESTADO LOCAL ELIMINADO: Dirección ---
    // var addressState by remember(user.idUser) { mutableStateOf("Calle y número, Colonia, Ciudad") }
    // LaunchedEffect(user.descripcion) {
    //     if (user.descripcion.isNotEmpty() && addressState == "Calle y número, Colonia, Ciudad") {
    //         addressState = user.descripcion
    //     }
    // }


    // --- 4. Manejo de Permisos de Ubicación (LÓGICA ELIMINADA) ---
    // val locationPermissionLauncher = rememberLauncherForActivityResult(...)
    // val checkAndRequestLocation = { ... }


    // --- 5. Manejo de Mensajes (Snackbar) ---
    LaunchedEffect(feedbackMessage) {
        if (feedbackMessage != null) {
            scope.launch {
                snackbarHostState.showSnackbar(feedbackMessage)
            }
            viewModel.clearFeedbackMessage()
        }
    }


    // --- Contenido Principal ---
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            // Encabezado con Logout/Guardar y la Imagen
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HeaderBlue)
                    .height(200.dp)
            ) {
                // Botones de ACCIÓN
                Row(
                    // ⚠️ CAMBIO CLAVE: Alineamos el Row a TopStart y usamos padding para separarlo de los bordes.
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth() // Necesario para que el Spacer funcione
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    // 1. Botón de REGRESAR (Alineado a la IZQUIERDA)
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color(0xFF1976D2), CircleShape) // Azul más oscuro
                            .clickable { onNavigateBack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar a la pantalla anterior",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    // ⚠️ SPACER: Empuja todo lo que sigue (el botón Guardar) hacia la derecha.
                    Spacer(Modifier.weight(1f))

                    // 2. Botón de GUARDAR (Alineado a la DERECHA)
                    if (!user.isAnonymus && !isLoading) {
                        Button(
                            onClick = viewModel::saveUserProfile,
                            enabled = !isSaving,
                            // Hacemos el botón compacto y le damos un color de éxito
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // Verde vibrante
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Save,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                tint = Color.White
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = if (isSaving) "Guardando" else "Guardar",
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                } // Fin del Row de botones

                // Placeholder de la Imagen de Perfil (Sin cambios)
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.BottomCenter)
                        .offset(y = (50).dp)
                        .clickable { showPhotoDialog = true }
                ) {
                    ImagePlaceholder(
                        size = 100.dp,
                        color = Color.White,
                        shape = CircleShape,
                        borderColor = HeaderBlue,
                        borderWidth = 3.dp
                    )
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Foto de perfil",
                        tint = HeaderBlue,
                        modifier = Modifier
                            .size(70.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        },
        containerColor = Color.White
    ) { paddingValues ->

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // --- Columna 1: Información Personal ---
            Column(horizontalAlignment = Alignment.Start) {
                // Nombre
                LabelText("Nombre")
                SimpleOutlinedTextField(
                    value = user.nombre,
                    onValueChange = { viewModel.onUserFieldChange(user.copy(nombre = it)) },
                    placeholder = "User name"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Género
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GenderOption("Hombre", user.sexo) { viewModel.onUserFieldChange(user.copy(sexo = "Hombre")) }
                    Spacer(modifier = Modifier.width(16.dp))
                    GenderOption("Mujer", user.sexo) { viewModel.onUserFieldChange(user.copy(sexo = "Mujer")) }
                    Spacer(modifier = Modifier.width(16.dp))
                    GenderOption("Otro", user.sexo) { viewModel.onUserFieldChange(user.copy(sexo = "Otro")) }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Teléfono
                LabelText("Teléfono")
                SimpleOutlinedTextField(
                    value = user.telefono,
                    onValueChange = { viewModel.onUserFieldChange(user.copy(telefono = it)) },
                    placeholder = "",
                    keyboardType = KeyboardType.Phone
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Fecha de Nacimiento (con DatePicker)
                LabelText("Fecha de nacimiento")
                OutlinedTextFieldWithDialog(
                    value = user.fechaNacimiento,
                    placeholder = "",
                    onClick = { showDatePicker = true }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Correo electrónico
                LabelText("Correo electrónico")
                SimpleOutlinedTextField(
                    value = user.correoElectronico,
                    onValueChange = { viewModel.onUserFieldChange(user.copy(correoElectronico = it)) },
                    placeholder = "",
                    keyboardType = KeyboardType.Email,
                    enabled = user.isAnonymus
                )

                Spacer(modifier = Modifier.height(16.dp))

            }
            // Separador (Ajustado si eliminaste el anterior)
            Spacer(modifier = Modifier.height(32.dp))

            // --- Columna 2: Descripción y Puntos de Respeto ---
            Column(horizontalAlignment = Alignment.Start) {
                // Descripción/Bio
                LabelText("Descripción")
                OutlinedTextField(
                    value = user.descripcion,
                    onValueChange = { viewModel.onUserFieldChange(user.copy(descripcion = it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp),
                    placeholder = { Text("Inserte una descripción", color = WireframeGray) },
                    maxLines = 5,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = WireframeGray,
                        unfocusedBorderColor = Color.LightGray
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Puntos de Respeto
                LabelText("Puntos de respeto")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    repeat(5) { index ->
                        val isFilled = index < user.respectPoints
                        Icon(
                            imageVector = if (isFilled) Icons.Default.Star else Icons.Outlined.StarBorder,
                            contentDescription = "Estrella de respeto",
                            tint = if (isFilled) StarYellow else StarGray,
                            modifier = Modifier
                                .size(36.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }

    // --- Diálogos y Pop-ups ---

    // 1. Diálogo de Selección de Foto (Sin cambios)
    if (showPhotoDialog) {
        AlertDialog(
            onDismissRequest = { showPhotoDialog = false },
            title = { Text(user.nombre, fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* Abrir Cámara */ showPhotoDialog = false }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Abrir camara", fontSize = 16.sp)
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(Icons.Default.Menu, contentDescription = null, tint = WireframeGray)
                    }
                    Divider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* Abrir Galería */ showPhotoDialog = false }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Abrir Galeria", fontSize = 16.sp)
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(Icons.Default.Menu, contentDescription = null, tint = WireframeGray)
                    }
                }
            },
            confirmButton = {}
        )
    }

    // 2. Lógica del Pop-up de Calendario (Sin cambios)
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val newDate = formatter.format(Date(millis))
                        viewModel.onUserFieldChange(user.copy(fechaNacimiento = newDate))
                    }
                    showDatePicker = false
                }) {
                    Text("OK", color = HeaderBlue)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancelar", color = HeaderBlue)
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserProfileScreenPreview() {
    UserProfileScreen(
        onNavigateBack = {}
    )
}

