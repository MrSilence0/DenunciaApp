package mx.edu.utng.oic.denunciaapp.ui.screens

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
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import mx.edu.utng.oic.denunciaapp.data.model.User
import mx.edu.utng.oic.denunciaapp.data.service.UserService // Necesario para la Factory
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.UserProfileViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.UserProfileViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

// ⚠️ IMPORTAR LAS FUNCIONES COMUNES DE TU PAQUETE:
import mx.edu.utng.oic.denunciaapp.ui.components.GenderOption
import mx.edu.utng.oic.denunciaapp.ui.components.ImagePlaceholder
import mx.edu.utng.oic.denunciaapp.ui.components.LabelText
import mx.edu.utng.oic.denunciaapp.ui.components.OutlinedTextFieldWithDialog
import mx.edu.utng.oic.denunciaapp.ui.components.SimpleOutlinedTextField
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray


// Colores (Mantener aquí si solo se usan en esta pantalla, sino mover a un Theme file)
val HeaderBlue = Color(0xFF42A5F5)
val StarYellow = Color(0xFFFFCC00)
val StarGray = Color(0xFFE0E0E0)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    onNavigateBack: () -> Unit // Se usa para salir o volver.
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
    var showLocationDialog by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    // --- 4. Manejo de Mensajes (Snackbar) ---
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
                // Botón de Logout
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Botón de GUARDAR (Si no queremos un botón de guardar explícito)
                    if (!user.isAnonymus && !isLoading) {
                        TextButton(
                            onClick = viewModel::saveUserProfile,
                            enabled = !isSaving
                        ) {
                            Text(
                                text = if (isSaving) "Guardando..." else "Guardar",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Botón de Logout (Flecha)
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "Logout",
                        tint = Color.White,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(start = 8.dp)
                            .clickable { viewModel.logoutUser(onNavigateBack) }
                    )
                }

                // Placeholder de la Imagen de Perfil
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
                    // Si el usuario no es anónimo, el email no debería ser editable
                    enabled = user.isAnonymus
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Dirección
                LabelText("Dirección")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SimpleOutlinedTextField(
                        value = user.descripcion, // Usando descripción como dirección temporal
                        onValueChange = { viewModel.onUserFieldChange(user.copy(descripcion = it)) },
                        placeholder = "",
                        modifier = Modifier.weight(1f),
                        interactionSource = remember { MutableInteractionSource() }
                            .also { interactionSource ->
                                LaunchedEffect(interactionSource) {
                                    interactionSource.interactions.collect {
                                        if (it is PressInteraction.Release) {
                                            showLocationDialog = true
                                        }
                                    }
                                }
                            }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = { showLocationDialog = true },
                        modifier = Modifier
                            .size(56.dp)
                            .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                            .padding(8.dp)
                    ) {
                        Icon(Icons.Default.Map, contentDescription = "Abrir Mapa", tint = WireframeGray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- Columna 2: Descripción y Puntos de Respeto ---
            Column(horizontalAlignment = Alignment.Start) {
                // Descripción (Si no se usa para dirección, usar un nuevo campo de User)
                LabelText("Descripción")
                OutlinedTextField(
                    value = user.descripcion, // Si usaste descripción arriba, cambia este
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
                            // La puntuación es solo visual, no se permite cambiar en este VM por simplicidad
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

    // 2. Diálogo de Ubicación (Simulación, sin cambios)
    if (showLocationDialog) {
        AlertDialog(
            onDismissRequest = { showLocationDialog = false },
            title = { Text("Ubicación", fontWeight = FontWeight.Bold) },
            text = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Activar su ubicación")
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .border(2.dp, WireframeGray, CircleShape)
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showLocationDialog = false }) {
                    Text("OK", color = HeaderBlue)
                }
            }
        )
    }

    // 3. Lógica del Pop-up de Calendario
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