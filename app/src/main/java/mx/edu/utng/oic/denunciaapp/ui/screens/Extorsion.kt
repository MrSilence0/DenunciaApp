package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
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
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.ExtorsionViewModel // Importar el ViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory // Importar la factoría central

// Importar los componentes comunes y estilos
import mx.edu.utng.oic.denunciaapp.ui.components.LabelText
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray
import mx.edu.utng.oic.denunciaapp.ui.utils.RedCancelButton
import mx.edu.utng.oic.denunciaapp.ui.utils.YellowButton

val BlueAttachButton = Color(0xFF1E88E5)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExtorsionScreen(
    onNavigateBack: () -> Unit,
    // El ViewModel se inyecta y se inicializa usando la factoría
    viewModel: ExtorsionViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createExtorsionViewModelFactory()
    )
) {
    // --- Observación de Estados del ViewModel ---
    val isSaving by viewModel.isSaving.collectAsState()
    val saveError by viewModel.saveError.collectAsState()
    val saveSuccess by viewModel.saveSuccess.collectAsState()

    // --- Estados del formulario local ---
    var numeroTelefonico by remember { mutableStateOf("") }
    var descripcionHechos by remember { mutableStateOf("") }
    var audioAdjuntoUri by remember { mutableStateOf<String?>(null) } // URI del audio (simulado)
    var isPlaying by remember { mutableStateOf(false) } // Estado para simular la reproducción

    // --- Efecto para Navegación en Éxito ---
    LaunchedEffect(saveSuccess) {
        if (saveSuccess) {
            onNavigateBack() // Vuelve a la pantalla anterior al guardar con éxito
            viewModel.resetStates()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reporte de Extorsión", fontWeight = FontWeight.Bold) },
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
            Spacer(modifier = Modifier.height(24.dp))

            // --- Campo "Número telefónico" ---
            LabelText("Número telefónico")
            OutlinedTextField(
                value = numeroTelefonico,
                onValueChange = { numeroTelefonico = it },
                placeholder = { Text("Número que realizó la llamada", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                enabled = !isSaving, // Deshabilitar mientras se guarda
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            // --- Campo "Describe los hechos" ---
            LabelText("Describa los hechos")
            OutlinedTextField(
                value = descripcionHechos,
                onValueChange = { descripcionHechos = it },
                placeholder = { Text("Relate lo sucedido, qué pedían y cómo lo pidieron", color = WireframeGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                maxLines = 5,
                enabled = !isSaving, // Deshabilitar mientras se guarda
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            // --- Botón "Adjuntar llamada" (Simulación) ---
            Button(
                onClick = {
                    // Simulación de adjuntar un archivo de audio
                    audioAdjuntoUri = "file://path/to/recording.mp3"
                },
                colors = ButtonDefaults.buttonColors(containerColor = BlueAttachButton),
                shape = RoundedCornerShape(8.dp),
                enabled = !isSaving, // Deshabilitar mientras se guarda
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    if (audioAdjuntoUri == null) "Adjuntar llamada" else "Audio Adjunto (Cambiar)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Controles de reproducción (Simulación) ---
            if (audioAdjuntoUri != null) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Texto simulando el nombre del archivo
                    Text("Llamada extorsión.mp3", fontSize = 14.sp, color = Color.Gray)
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /* Anterior */ }, enabled = !isSaving) { Icon(Icons.Default.SkipPrevious, contentDescription = "Pista anterior", tint = WireframeGray) }
                    IconButton(onClick = { /* Rewind */ }, enabled = !isSaving) { Icon(Icons.Default.FastRewind, contentDescription = "Rebobinar", tint = WireframeGray) }

                    // Botón Play/Pause central
                    Button(
                        onClick = { isPlaying = !isPlaying },
                        modifier = Modifier.size(56.dp),
                        enabled = !isSaving,
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = BlueAttachButton)
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    IconButton(onClick = { /* Fast Forward */ }, enabled = !isSaving) { Icon(Icons.Default.FastForward, contentDescription = "Adelantar", tint = WireframeGray) }
                    IconButton(onClick = { /* Siguiente */ }, enabled = !isSaving) { Icon(Icons.Default.SkipNext, contentDescription = "Pista siguiente", tint = WireframeGray) }
                }
            } else {
                Spacer(modifier = Modifier.height(50.dp)) // Espacio para mantener el layout
            }
            Spacer(modifier = Modifier.height(32.dp))

            // --- Texto descriptivo (Lorem Ipsum) ---
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón "Guardar" ---
            Button(
                onClick = {
                    viewModel.submitDenuncia(numeroTelefonico, descripcionHechos)
                },
                enabled = !isSaving && numeroTelefonico.isNotBlank() && descripcionHechos.isNotBlank(), // Deshabilitado si guarda o campos vacíos
                colors = ButtonDefaults.buttonColors(containerColor = YellowButton),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                if (isSaving) {
                    CircularProgressIndicator(color = Color.DarkGray, modifier = Modifier.size(24.dp))
                } else {
                    Text("Guardar", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // --- Diálogo de Error ---
    saveError?.let { message ->
        AlertDialog(
            onDismissRequest = { viewModel.resetStates() },
            title = { Text("Error al Enviar Reporte") },
            text = { Text(message) },
            confirmButton = {
                Button(onClick = { viewModel.resetStates() }) {
                    Text("Aceptar")
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExtorsionPreview() {
    // Para el Preview, se pasa un ViewModel dummy o se usa el constructor por defecto
    // Nota: La inyección real debe ocurrir en la navegación.
    ExtorsionScreen(onNavigateBack = {})
}