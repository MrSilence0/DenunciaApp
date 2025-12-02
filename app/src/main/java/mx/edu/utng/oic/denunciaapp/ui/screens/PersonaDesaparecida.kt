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
import android.widget.Toast
import mx.edu.utng.oic.denunciaapp.ui.utils.RedCancelButton
import mx.edu.utng.oic.denunciaapp.ui.utils.YellowButton
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.PersonaDesaparecidaViewModel
enum class Sexo { MUJER, HOMBRE }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonaDesaparecidaScreen(
    onNavigateBack: () -> Unit,
    viewModel: PersonaDesaparecidaViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createPersonaDesaparecidaViewModelFactory()
    )
) {
    // --- Colores Dinámicos del Tema ---
    val primaryColor = MaterialTheme.colorScheme.primary
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary
    val surfaceColor = MaterialTheme.colorScheme.surface
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant
    val outlineColor = MaterialTheme.colorScheme.outline

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

    // --- Efecto Secundario para manejar el éxito y error (Sin cambios relevantes) ---
    LaunchedEffect(saveSuccess) {
        if (saveSuccess) {
            Toast.makeText(context, "✅ Reporte enviado con éxito.", Toast.LENGTH_LONG).show()
            viewModel.resetStates()
            onNavigateBack()
        }
    }
    LaunchedEffect(saveError) {
        if (saveError != null) {
            Toast.makeText(context, "❌ Error al enviar: $saveError", Toast.LENGTH_LONG).show()
            viewModel.resetStates()
        }
    }

    // --- Colores para OutlinedTextFields ---
    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = primaryColor,
        unfocusedBorderColor = outlineColor,
        focusedTextColor = onSurfaceColor,
        unfocusedTextColor = onSurfaceColor,
        focusedPlaceholderColor = onSurfaceVariantColor,
        unfocusedPlaceholderColor = onSurfaceVariantColor
    )
    val placeholder: @Composable (String) -> @Composable (() -> Unit) = { text -> { Text(text, color = onSurfaceVariantColor) } }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Reporte de Persona Desaparecida", fontWeight = FontWeight.Bold, color = onSurfaceColor)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor
                ),
                actions = {
                    TextButton(
                        onClick = onNavigateBack,
                        enabled = !isSaving
                    ) {
                        Text("Cancelar", color = RedCancelButton, fontWeight = FontWeight.Bold)
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // --- Área de Previsualización de Imagen ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(surfaceColor.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                    .border(1.dp, outlineColor, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (selectedImageUri == null) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = "Placeholder de imagen",
                            modifier = Modifier.size(60.dp),
                            tint = onSurfaceVariantColor
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Foto de la persona",
                            color = onSurfaceVariantColor
                        )
                    }
                } else {
                    Text("Imagen de la persona seleccionada", color = onSurfaceColor)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Botones para Seleccionar Imagen ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Colores para botones secundarios (Cámara/Galería)
                val secondaryButtonColors = ButtonDefaults.buttonColors(
                    containerColor = onSurfaceVariantColor.copy(alpha = 0.5f),
                    contentColor = onSurfaceColor
                )

                // Botón "Cámara"
                Button(
                    onClick = { /* Lógica para abrir la cámara */ },
                    colors = secondaryButtonColors,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f),
                    enabled = !isSaving
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Tomar foto", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cámara", color = onSurfaceColor)
                }
                Spacer(modifier = Modifier.width(16.dp))
                // Botón "Galería"
                Button(
                    onClick = { /* Lógica para abrir la galería */ },
                    colors = secondaryButtonColors,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f),
                    enabled = !isSaving
                ) {
                    Icon(Icons.Default.Image, contentDescription = "Seleccionar de galería", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Galería", color = onSurfaceColor)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // --- Campo "Nombre" ---
            LabelText("Nombre completo",)
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                placeholder = placeholder("Nombre de la persona desaparecida"),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = textFieldColors
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Radio Buttons de Sexo ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Sexo:", fontWeight = FontWeight.Medium, color = onSurfaceColor)
                Spacer(modifier = Modifier.width(16.dp))

                // Colores dinámicos para RadioButton
                val radioColors = RadioButtonDefaults.colors(
                    selectedColor = primaryColor,
                    unselectedColor = outlineColor,
                    disabledSelectedColor = outlineColor,
                    disabledUnselectedColor = outlineColor.copy(alpha = 0.5f)
                )

                // Opción Mujer
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedSexo == Sexo.MUJER,
                        onClick = { selectedSexo = Sexo.MUJER },
                        enabled = !isSaving,
                        colors = radioColors
                    )
                    Text("Mujer", modifier = Modifier.padding(end = 16.dp), color = onSurfaceColor) // CORRECCIÓN 12
                }
                // Opción Hombre
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedSexo == Sexo.HOMBRE,
                        onClick = { selectedSexo = Sexo.HOMBRE },
                        enabled = !isSaving,
                        colors = radioColors
                    )
                    Text("Hombre", color = onSurfaceColor) // CORRECCIÓN 12
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Edad" ---
            LabelText("Edad (aproximada)",)
            OutlinedTextField(
                value = edad,
                onValueChange = { edad = if (it.length <= 3) it else it.substring(0, 3) },
                placeholder = placeholder("Ej: 35"),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = textFieldColors // CORRECCIÓN 9
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Descripción física" ---
            LabelText("Descripción física",)
            OutlinedTextField(
                value = descripcionFisica,
                onValueChange = { descripcionFisica = it },
                placeholder = placeholder("Altura, color de ojos/cabello, señas particulares"),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = textFieldColors // CORRECCIÓN 9
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Vestimenta" ---
            LabelText("Vestimenta (última vez vista)",)
            OutlinedTextField(
                value = vestimenta,
                onValueChange = { vestimenta = it },
                placeholder = placeholder("Color y tipo de ropa, calzado"),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = textFieldColors // CORRECCIÓN 9
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Detalles del hecho" ---
            LabelText("Describe el lugar, la hora y cómo sucedió",)
            OutlinedTextField(
                value = detallesHecho,
                onValueChange = { detallesHecho = it },
                placeholder = placeholder("Proporciona detalles cruciales de la desaparición"),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                maxLines = 5,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                enabled = !isSaving,
                colors = textFieldColors // CORRECCIÓN 9
            )
            Spacer(modifier = Modifier.height(32.dp))

            // --- Texto descriptivo ---
            Text(
                text = "Los campos marcados con * son obligatorios. Asegúrese de proporcionar la información de los la persona desaparecida de la forma más precisa posible para facilitar la búsqueda.",
                fontSize = 12.sp,
                color = onSurfaceVariantColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón "Guardar" ---
            Button(
                onClick = {
                    viewModel.submitDenuncia(
                        nombre = nombre, sexo = selectedSexo, edad = edad, descripcionFisica = descripcionFisica,
                        vestimenta = vestimenta, detallesHecho = detallesHecho
                    )
                },
                enabled = !isSaving && nombre.isNotBlank() && edad.isNotBlank() && detallesHecho.isNotBlank(),
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
    PersonaDesaparecidaScreen(
        onNavigateBack = {},
        viewModel = viewModel(
            factory = DenunciaAppViewModelFactory.createPersonaDesaparecidaViewModelFactory()
        )
    )
}