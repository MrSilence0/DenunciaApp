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
import mx.edu.utng.oic.denunciaapp.ui.utils.RedCancelButton // Asumimos que es un color fijo para peligro
import mx.edu.utng.oic.denunciaapp.ui.utils.YellowButton // Asumimos que es un color fijo para acciones
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.RoboVehiculoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoboVehiculoScreen(
    onNavigateBack: () -> Unit,
    viewModel: RoboVehiculoViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createRoboVehiculoViewModelFactory()
    )
) {
    // --- Estados del formulario y ViewModel (Sin cambios) ---
    var placas by remember { mutableStateOf("") }
    var numeroSerie by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var nombreVehiculo by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<String?>(null) }

    val isSaving by viewModel.isSaving.collectAsState()
    val saveError by viewModel.saveError.collectAsState()
    val saveSuccess by viewModel.saveSuccess.collectAsState()
    val context = LocalContext.current

    // Efectos Secundarios (Sin cambios)
    LaunchedEffect(saveSuccess) {
        if (saveSuccess) {
            Toast.makeText(context, "✅ Reporte de vehículo enviado con éxito.", Toast.LENGTH_LONG).show()
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

    val isFormValid = placas.isNotBlank() && numeroSerie.isNotBlank() && anio.toIntOrNull() != null

    // --- Colores Dinámicos del Tema ---
    val primaryColor = MaterialTheme.colorScheme.primary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant
    val surfaceColor = MaterialTheme.colorScheme.surface

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Robo de Vehículo", fontWeight = FontWeight.Bold, color = onSurfaceColor)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = onSurfaceColor // Usar color dinámico
                ),
                actions = {
                    TextButton(
                        onClick = onNavigateBack,
                        enabled = !isSaving
                    ) {
                        // Se mantiene RedCancelButton como color fijo de utilidad/peligro
                        Text("Cancelar", color = RedCancelButton, fontWeight = FontWeight.Bold)
                    }
                }
            )
        },
        // --- CORRECCIÓN 3: Usar color de fondo dinámico ---
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background), // Asegurar que el scroll también sea dinámico
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // --- Área de Previsualización de Imagen ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(surfaceColor.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp)),
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
                            "Foto del vehículo (opcional)",
                            color = onSurfaceVariantColor
                        )
                    }
                } else {
                    Text("Imagen del vehículo seleccionada", color = onSurfaceColor)
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
                    colors = ButtonDefaults.buttonColors(containerColor = onSurfaceVariantColor.copy(alpha = 0.5f)),
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
                    colors = ButtonDefaults.buttonColors(containerColor = onSurfaceVariantColor.copy(alpha = 0.5f)),
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

            // --- Campos del formulario ---

            // Placas
            LabelText("Placas *",)
            OutlinedTextField(
                value = placas,
                onValueChange = { placas = it },
                placeholder = { Text("ABC-123", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedTextColor = onSurfaceColor,
                    unfocusedTextColor = onSurfaceColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Número de serie
            LabelText("Número de serie (VIN) *",)
            OutlinedTextField(
                value = numeroSerie,
                onValueChange = { numeroSerie = it },
                placeholder = { Text("17 dígitos del VIN", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedTextColor = onSurfaceColor,
                    unfocusedTextColor = onSurfaceColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Marca
            LabelText("Marca",)
            OutlinedTextField(
                value = marca,
                onValueChange = { marca = it },
                placeholder = { Text("Ej: Nissan, Ford, VW", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedTextColor = onSurfaceColor,
                    unfocusedTextColor = onSurfaceColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Color
            LabelText("Color",)
            OutlinedTextField(
                value = color,
                onValueChange = { color = it },
                placeholder = { Text("Ej: Rojo, Gris Oxford", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedTextColor = onSurfaceColor,
                    unfocusedTextColor = onSurfaceColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Año
            LabelText("Año *",)
            OutlinedTextField(
                value = anio,
                onValueChange = { anio = it.filter { char -> char.isDigit() }.take(4) },
                placeholder = { Text("Ej: 2018", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedTextColor = onSurfaceColor,
                    unfocusedTextColor = onSurfaceColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            LabelText("Descripción del Vehículo/Reportante",)
            OutlinedTextField(
                value = nombreVehiculo,
                onValueChange = { nombreVehiculo = it },
                placeholder = { Text("Modelo, tipo de vehículo o nombre del reportante", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                enabled = !isSaving,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedTextColor = onSurfaceColor,
                    unfocusedTextColor = onSurfaceColor
                )
            )
            Spacer(modifier = Modifier.height(32.dp))

            // --- Texto descriptivo ---
            Text(
                text = "Los campos marcados con * son obligatorios. Asegúrese de proporcionar la información del vehículo de la forma más precisa posible para facilitar la búsqueda.",
                fontSize = 12.sp,
                color = onSurfaceVariantColor,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón "Guardar" ---
            Button(
                onClick = {
                    viewModel.submitDenuncia(
                        placas = placas,
                        numeroSerie = numeroSerie,
                        marca = marca,
                        color = color,
                        anio = anio,
                        nombreVehiculo = nombreVehiculo,
                        imageUri = selectedImageUri
                    )
                },
                enabled = !isSaving && isFormValid,
                // Se mantiene YellowButton para el contenedor (color fijo de acción)
                colors = ButtonDefaults.buttonColors(containerColor = YellowButton),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                if (isSaving) {
                    CircularProgressIndicator(
                        // --- CORRECCIÓN 12: Usar color de texto dinámico o un color contrastante fijo ---
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    // El color del texto del botón debe contrastar con YellowButton (generalmente negro o gris oscuro)
                    Text("Guardar", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RoboVehiculoPreview() {
    RoboVehiculoScreen(
        onNavigateBack = {},
        viewModel = viewModel(
            factory = DenunciaAppViewModelFactory.createRoboVehiculoViewModelFactory()
        )
    )
}