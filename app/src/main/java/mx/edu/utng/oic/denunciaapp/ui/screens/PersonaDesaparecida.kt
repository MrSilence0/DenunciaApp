package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import android.widget.Toast // Para mostrar mensajes de éxito/error

// Importar los componentes comunes y estilos
import mx.edu.utng.oic.denunciaapp.ui.components.LabelText
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray
import mx.edu.utng.oic.denunciaapp.ui.utils.RedCancelButton
import mx.edu.utng.oic.denunciaapp.ui.utils.YellowButton

// 🌟 IMPORTANTE: El objeto DenunciaAppViewModelFactory se importa del archivo centralizado.
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.PersonaDesaparecidaViewModel

// Definición de las opciones de sexo
enum class Sexo { MUJER, HOMBRE }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonaDesaparecidaScreen(
    onNavigateBack: () -> Unit,
    // La línea que causaba el error, ahora debe funcionar si la factoría está centralizada.
    viewModel: PersonaDesaparecidaViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createPersonaDesaparecidaViewModelFactory()
    )
) {
    // --- Estados del formulario ---
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var descripcionFisica by remember { mutableStateOf("") }
    var vestimenta by remember { mutableStateOf("") }
    var detallesHecho by remember { mutableStateOf("") }
    var selectedSexo by remember { mutableStateOf(Sexo.MUJER) }
    var selectedImageUri by remember { mutableStateOf<String?>(null) }

    // --- Estados del ViewModel ---
    val isSaving by viewModel.isSaving.collectAsState()
    val saveError by viewModel.saveError.collectAsState()
    val saveSuccess by viewModel.saveSuccess.collectAsState()
    val context = LocalContext.current

    // --- Efecto Secundario para manejar el éxito y error ---
    LaunchedEffect(saveSuccess) {
        if (saveSuccess) {
            Toast.makeText(context, "✅ Reporte enviado con éxito.", Toast.LENGTH_LONG).show()
            viewModel.resetStates() // Limpiar estados después de la acción
            onNavigateBack()
        }
    }

    LaunchedEffect(saveError) {
        if (saveError != null) {
            Toast.makeText(context, "❌ Error al enviar: $saveError", Toast.LENGTH_LONG).show()
            viewModel.resetStates() // Limpiar solo el error después de mostrarlo
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reporte de Persona Desaparecida", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                actions = {
                    TextButton(
                        onClick = onNavigateBack,
                        enabled = !isSaving // Deshabilitar si está guardando
                    ) {
                        Text("Cancelar", color = RedCancelButton, fontWeight = FontWeight.Bold)
                    }
                }
            )
        },
        containerColor = Color.White // Fondo blanco
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // --- Área de Previsualización de Imagen ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Altura para el placeholder de imagen
                    .background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                    .border(1.dp, WireframeGray, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (selectedImageUri == null) {
                    // Placeholder genérico si no hay imagen
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = "Placeholder de imagen",
                            modifier = Modifier.size(60.dp),
                            tint = WireframeGray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Foto de la persona", color = WireframeGray)
                    }
                } else {
                    // Aquí iría el código para mostrar la imagen real
                    Text("Imagen de la persona seleccionada", color = Color.DarkGray) // Placeholder visual
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Botones para Seleccionar Imagen ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Botón "Cámara"
                Button(
                    onClick = { /* Lógica para abrir la cámara */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f),
                    enabled = !isSaving // Deshabilitar si está guardando
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Tomar foto", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cámara")
                }
                Spacer(modifier = Modifier.width(16.dp))
                // Botón "Galería"
                Button(
                    onClick = { /* Lógica para abrir la galería */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f),
                    enabled = !isSaving // Deshabilitar si está guardando
                ) {
                    Icon(Icons.Default.Image, contentDescription = "Seleccionar de galería", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Galería")
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // --- Campo "Nombre" ---
            LabelText("Nombre completo")
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                placeholder = { Text("Nombre de la persona desaparecida", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving, // Deshabilitar si está guardando
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Radio Buttons de Sexo ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Sexo:", fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.width(16.dp))
                // Opción Mujer
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedSexo == Sexo.MUJER,
                        onClick = { selectedSexo = Sexo.MUJER },
                        enabled = !isSaving, // Deshabilitar si está guardando
                        colors = RadioButtonDefaults.colors(selectedColor = Color.Black, unselectedColor = Color.Gray)
                    )
                    Text("Mujer", modifier = Modifier.padding(end = 16.dp))
                }
                // Opción Hombre
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedSexo == Sexo.HOMBRE,
                        onClick = { selectedSexo = Sexo.HOMBRE },
                        enabled = !isSaving, // Deshabilitar si está guardando
                        colors = RadioButtonDefaults.colors(selectedColor = Color.Black, unselectedColor = Color.Gray)
                    )
                    Text("Hombre")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Edad" ---
            LabelText("Edad (aproximada)")
            OutlinedTextField(
                value = edad,
                onValueChange = { edad = if (it.length <= 3) it else it.substring(0, 3) }, // Limitar a 3 dígitos
                placeholder = { Text("Ej: 35", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                enabled = !isSaving, // Deshabilitar si está guardando
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Descripción física" ---
            LabelText("Descripción física")
            OutlinedTextField(
                value = descripcionFisica,
                onValueChange = { descripcionFisica = it },
                placeholder = { Text("Altura, color de ojos/cabello, señas particulares", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving, // Deshabilitar si está guardando
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Vestimenta" ---
            LabelText("Vestimenta (última vez vista)")
            OutlinedTextField(
                value = vestimenta,
                onValueChange = { vestimenta = it },
                placeholder = { Text("Color y tipo de ropa, calzado", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving, // Deshabilitar si está guardando
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Detalles del hecho" ---
            LabelText("Describe el lugar, la hora y cómo sucedió")
            OutlinedTextField(
                value = detallesHecho,
                onValueChange = { detallesHecho = it },
                placeholder = { Text("Proporciona detalles cruciales de la desaparición", color = WireframeGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                maxLines = 5,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                enabled = !isSaving, // Deshabilitar si está guardando
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(32.dp))

            // --- Texto descriptivo (Lorem Ipsum) ---
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón "Guardar" ---
            Button(
                onClick = {
                    // Llamar al método del ViewModel para guardar
                    viewModel.submitDenuncia(
                        nombre = nombre,
                        sexo = selectedSexo,
                        edad = edad,
                        descripcionFisica = descripcionFisica,
                        vestimenta = vestimenta,
                        detallesHecho = detallesHecho
                    )
                },
                enabled = !isSaving && nombre.isNotBlank() && edad.isNotBlank() && detallesHecho.isNotBlank(), // Deshabilitar si está guardando o si los campos obligatorios están vacíos
                colors = ButtonDefaults.buttonColors(containerColor = YellowButton),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                if (isSaving) {
                    CircularProgressIndicator(
                        color = Color.DarkGray,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text("Guardar", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PersonaDesaparecidaPreview() {
    // Usamos la factoría centralizada para el preview
    PersonaDesaparecidaScreen(
        onNavigateBack = {},
        viewModel = viewModel(
            factory = DenunciaAppViewModelFactory.createPersonaDesaparecidaViewModelFactory()
        )
    )
}