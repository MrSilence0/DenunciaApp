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
import mx.edu.utng.oic.denunciaapp.ui.components.LabelText
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray
import mx.edu.utng.oic.denunciaapp.ui.utils.RedCancelButton
import mx.edu.utng.oic.denunciaapp.ui.utils.YellowButton
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.RoboVehiculoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoboVehiculoScreen(
    onNavigateBack: () -> Unit,
    // 1. Inyectar ViewModel usando la factoría centralizada
    viewModel: RoboVehiculoViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createRoboVehiculoViewModelFactory()
    )
) {
    // --- Estados del formulario ---
    var placas by remember { mutableStateOf("") }
    var numeroSerie by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var nombreVehiculo by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<String?>(null) }

    // --- Estados del ViewModel ---
    val isSaving by viewModel.isSaving.collectAsState()
    val saveError by viewModel.saveError.collectAsState()
    val saveSuccess by viewModel.saveSuccess.collectAsState()
    val context = LocalContext.current

    // 2. Efecto Secundario para manejar el éxito
    LaunchedEffect(saveSuccess) {
        if (saveSuccess) {
            Toast.makeText(context, "✅ Reporte de vehículo enviado con éxito.", Toast.LENGTH_LONG).show()
            viewModel.resetStates()
            onNavigateBack() // Vuelve al menú principal o pantalla anterior
        }
    }

    // 3. Efecto Secundario para manejar el error
    LaunchedEffect(saveError) {
        if (saveError != null) {
            Toast.makeText(context, "❌ Error al enviar: $saveError", Toast.LENGTH_LONG).show()
            viewModel.resetStates()
        }
    }

    // Validación básica
    val isFormValid = placas.isNotBlank() && numeroSerie.isNotBlank() && anio.toIntOrNull() != null

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Robo de Vehículo", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
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
                    .height(200.dp)
                    .background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                    .border(1.dp, WireframeGray, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (selectedImageUri == null) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = "Placeholder de imagen",
                            modifier = Modifier.size(60.dp),
                            tint = WireframeGray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Foto del vehículo (opcional)", color = WireframeGray)
                    }
                } else {
                    Text("Imagen del vehículo seleccionada", color = Color.DarkGray)
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
                    enabled = !isSaving
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
                    enabled = !isSaving
                ) {
                    Icon(Icons.Default.Image, contentDescription = "Seleccionar de galería", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Galería")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Campos del formulario ---
            // Placas
            LabelText("Placas *")
            OutlinedTextField(
                value = placas,
                onValueChange = { placas = it },
                placeholder = { Text("ABC-123", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Número de serie
            LabelText("Número de serie (VIN) *")
            OutlinedTextField(
                value = numeroSerie,
                onValueChange = { numeroSerie = it },
                placeholder = { Text("17 dígitos del VIN", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Marca
            LabelText("Marca")
            OutlinedTextField(
                value = marca,
                onValueChange = { marca = it },
                placeholder = { Text("Ej: Nissan, Ford, VW", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Color
            LabelText("Color")
            OutlinedTextField(
                value = color,
                onValueChange = { color = it },
                placeholder = { Text("Ej: Rojo, Gris Oxford", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Año
            LabelText("Año *")
            OutlinedTextField(
                value = anio,
                onValueChange = { anio = it.filter { char -> char.isDigit() }.take(4) },
                placeholder = { Text("Ej: 2018", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                enabled = !isSaving,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Nombre del vehículo (Mapeado a nombreReportante en el ViewModel)
            LabelText("Descripción del Vehículo/Reportante")
            OutlinedTextField(
                value = nombreVehiculo,
                onValueChange = { nombreVehiculo = it },
                placeholder = { Text("Modelo, tipo de vehículo o nombre del reportante", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                enabled = !isSaving,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(32.dp))

            // --- Texto descriptivo (Lorem Ipsum) ---
            Text(
                text = "Los campos marcados con * son obligatorios. Asegúrese de proporcionar la información del vehículo de la forma más precisa posible para facilitar la búsqueda.",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón "Guardar" ---
            Button(
                // 4. Llamar a submitDenuncia del ViewModel
                onClick = {
                    viewModel.submitDenuncia(
                        placas = placas,
                        numeroSerie = numeroSerie,
                        marca = marca,
                        color = color,
                        anio = anio,
                        nombreVehiculo = nombreVehiculo, // Usado como nombreReportante
                        imageUri = selectedImageUri
                    )
                },
                // Deshabilitar si está guardando o si el formulario no es válido
                enabled = !isSaving && isFormValid,
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
fun RoboVehiculoPreview() {
    RoboVehiculoScreen(
        onNavigateBack = {},
        // Se utiliza la factoría para generar una vista previa del ViewModel (puede ser un mock)
        viewModel = viewModel(
            factory = DenunciaAppViewModelFactory.createRoboVehiculoViewModelFactory()
        )
    )
}