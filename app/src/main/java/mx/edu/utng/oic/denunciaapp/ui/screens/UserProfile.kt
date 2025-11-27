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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    onNavigateBack: () -> Unit // Se usa para salir, volver, o hacer logout.
) {
    // --- Estados del Perfil ---
    var name by remember { mutableStateOf("User name") }
    var selectedGender by remember { mutableStateOf("Hombre") }
    var phone by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("Inserte una descripción") }
    var respectScore by remember { mutableStateOf(3) } // 3 estrellas de 5

    // --- Estados de Lógica de UI ---
    var showPhotoDialog by remember { mutableStateOf(false) }
    var showLocationDialog by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    // Estado para el selector de fecha
    val datePickerState = rememberDatePickerState()


    // --- Contenido Principal ---
    Scaffold(
        topBar = {
            // Barra superior simple para el botón de logout (flecha)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HeaderBlue)
                    .height(200.dp) // Altura del encabezado azul
            ) {
                // Botón de Logout (Flecha blanca grande)
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Logout/Volver",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(24.dp)
                        .size(32.dp)
                        .clickable(onClick = onNavigateBack) // Usar onNavigateBack para salir
                )

                // Placeholder de la Imagen de Perfil
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.BottomCenter)
                        .offset(y = (50).dp) // La mitad del tamaño para que quede centrado en el corte
                        .clickable { showPhotoDialog = true } // Abre el diálogo al hacer clic
                ) {
                    ImagePlaceholder(
                        size = 100.dp,
                        color = Color.White,
                        shape = CircleShape,
                        borderColor = HeaderBlue,
                        borderWidth = 3.dp
                    )
                    // Icono de persona dentro del placeholder
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp)) // Espacio para la imagen que se superpone

            // --- Columna 1: Información Personal (Izquierda del Wireframe) ---
            Column(horizontalAlignment = Alignment.Start) {
                // Nombre
                LabelText("Nombre")
                SimpleOutlinedTextField(value = name, onValueChange = { name = it }, placeholder = "User name")

                Spacer(modifier = Modifier.height(16.dp))

                // Género
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GenderOption("Hombre", selectedGender) { selectedGender = "Hombre" }
                    Spacer(modifier = Modifier.width(16.dp))
                    GenderOption("Mujer", selectedGender) { selectedGender = "Mujer" }
                    Spacer(modifier = Modifier.width(16.dp))
                    GenderOption("Otro", selectedGender) { selectedGender = "Otro" }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Teléfono
                LabelText("Teléfono")
                SimpleOutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    placeholder = "",
                    keyboardType = KeyboardType.Phone
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Fecha de Nacimiento (con DatePicker)
                LabelText("Fecha de nacimiento")
                OutlinedTextFieldWithDialog(
                    value = birthDate,
                    placeholder = "",
                    onClick = { showDatePicker = true }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Correo electrónico
                LabelText("Correo electrónico")
                SimpleOutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "",
                    keyboardType = KeyboardType.Email
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Dirección (con botón de Mapa)
                LabelText("Dirección")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Campo de texto para la dirección
                    SimpleOutlinedTextField(
                        value = address,
                        onValueChange = { address = it },
                        placeholder = "",
                        modifier = Modifier.weight(1f),
                        // Al hacer clic, se simula que se activa la ubicación
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

                    // Botón para Maps (Icono de ubicación)
                    IconButton(
                        // NOTA: Se asume que este botón debería navegar a un mapa externo o a otra ruta.
                        // Como no tenemos el callback original, usamos una simulación o un popBackStack.
                        // Para evitar errores de compilación, si la navegación a mapa no está definida,
                        // simplemente se omite o se usa un placeholder de click.
                        onClick = {
                            // Aquí debería ir onNavigateToMap() si estuviera en la firma,
                            // o una llamada al NavController.
                            showLocationDialog = true // Simular la interacción de ubicación.
                        },
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

            // --- Columna 2: Descripción y Puntos de Respeto (Derecha del Wireframe) ---
            Column(horizontalAlignment = Alignment.Start) {
                // Descripción
                LabelText("Descripción")
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
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
                        val isFilled = index < respectScore
                        Icon(
                            imageVector = if (isFilled) Icons.Default.Star else Icons.Outlined.StarBorder,
                            contentDescription = "Estrella de respeto",
                            tint = if (isFilled) StarYellow else StarGray,
                            modifier = Modifier
                                .size(36.dp)
                                .clickable { respectScore = index + 1 } // Permite cambiar la puntuación
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }

    // --- Diálogos y Pop-ups ---

    // 1. Diálogo de Selección de Foto
    if (showPhotoDialog) {
        AlertDialog(
            onDismissRequest = { showPhotoDialog = false },
            title = { Text("Name", fontWeight = FontWeight.Bold) },
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
            confirmButton = {} // Se usan las opciones del texto, no un botón de Confirmar
        )
    }

    // 2. Diálogo de Ubicación (Simulación)
    if (showLocationDialog) {
        AlertDialog(
            onDismissRequest = { showLocationDialog = false },
            title = { Text("Message Title", fontWeight = FontWeight.Bold) },
            text = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Activar su ubicación")
                    Spacer(modifier = Modifier.weight(1f))
                    // Icono de Círculo (Placeholder para Switch o Checkbox)
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

    // 3. Lógica del Pop-up de Calendario (Reutilizado de RegisterScreen)
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        birthDate = formatter.format(Date(millis))
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

