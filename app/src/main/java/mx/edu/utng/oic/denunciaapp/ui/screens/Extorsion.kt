package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.ExtorsionViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory

import mx.edu.utng.oic.denunciaapp.ui.components.LabelText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExtorsionScreen(
    onNavigateBack: () -> Unit,
    viewModel: ExtorsionViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createExtorsionViewModelFactory()
    )
) {
    // --- Colores Dinámicos del Tema ---
    val primaryColor = MaterialTheme.colorScheme.primary
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary
    val errorColor = MaterialTheme.colorScheme.error
    val outlineColor = MaterialTheme.colorScheme.outline
    val surfaceColor = MaterialTheme.colorScheme.surface
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val backgroundColor = MaterialTheme.colorScheme.background
    val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val onTertiaryColor = MaterialTheme.colorScheme.onTertiary

    // --- Observación de Estados del ViewModel ---
    val isSaving by viewModel.isSaving.collectAsState()
    val saveError by viewModel.saveError.collectAsState()
    val saveSuccess by viewModel.saveSuccess.collectAsState()

    // --- Estados del formulario local ---
    var numeroTelefonico by remember { mutableStateOf("") }
    var descripcionHechos by remember { mutableStateOf("") }
    var audioAdjuntoUri by remember { mutableStateOf<String?>(null) }
    var isPlaying by remember { mutableStateOf(false) }

    // --- Efecto para Navegación en Éxito ---
    LaunchedEffect(saveSuccess) {
        if (saveSuccess) {
            onNavigateBack()
            viewModel.resetStates()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Reporte de Extorsión", fontWeight = FontWeight.Bold, color = onSurfaceColor)
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
                        Text("Cancelar", color = errorColor, fontWeight = FontWeight.Bold)
                    }
                }
            )
        },
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
                // CORRECCIÓN 5: Asegurar el fondo dinámico
                .background(backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // --- Campo "Número telefónico" ---
            LabelText("Número telefónico", color = onSurfaceColor) // Usar color de texto dinámico
            OutlinedTextField(
                value = numeroTelefonico,
                onValueChange = { numeroTelefonico = it },
                placeholder = { Text("Número que realizó la llamada", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                enabled = !isSaving,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    disabledBorderColor = outlineColor.copy(alpha = 0.5f),
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            // --- Campo "Describe los hechos" ---
            LabelText("Describa los hechos", color = onSurfaceColor) // Usar color de texto dinámico
            OutlinedTextField(
                value = descripcionHechos,
                onValueChange = { descripcionHechos = it },
                placeholder = { Text("Relate lo sucedido, qué pedían y cómo lo pidieron", color = onSurfaceVariantColor) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                maxLines = 5,
                enabled = !isSaving,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    disabledBorderColor = outlineColor.copy(alpha = 0.5f),
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            // --- Botón "Adjuntar llamada" (Simulación) ---
            Button(
                onClick = { audioAdjuntoUri = "file://path/to/recording.mp3" },
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                shape = RoundedCornerShape(8.dp),
                enabled = !isSaving,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    if (audioAdjuntoUri == null) "Adjuntar llamada" else "Audio Adjunto (Cambiar)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = onPrimaryColor
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
                    Text("Llamada extorsión.mp3", fontSize = 14.sp, color = onSurfaceVariantColor)
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Iconos pequeños
                    IconButton(onClick = { /* Anterior */ }, enabled = !isSaving) {
                        Icon(Icons.Default.SkipPrevious, contentDescription = "Pista anterior", tint = onSurfaceVariantColor)
                    }
                    IconButton(onClick = { /* Rewind */ }, enabled = !isSaving) {
                        Icon(Icons.Default.FastRewind, contentDescription = "Rebobinar", tint = onSurfaceVariantColor)
                    }

                    // Botón Play/Pause central
                    Button(
                        onClick = { isPlaying = !isPlaying },
                        modifier = Modifier.size(56.dp),
                        enabled = !isSaving,
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                            tint = onPrimaryColor,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    IconButton(onClick = { /* Fast Forward */ }, enabled = !isSaving) {
                        // CORRECCIÓN 15: Usar color de texto sutil
                        Icon(Icons.Default.FastForward, contentDescription = "Adelantar", tint = onSurfaceVariantColor)
                    }
                    IconButton(onClick = { /* Siguiente */ }, enabled = !isSaving) {
                        // CORRECCIÓN 16: Usar color de texto sutil
                        Icon(Icons.Default.SkipNext, contentDescription = "Pista siguiente", tint = onSurfaceVariantColor)
                    }
                }
            } else {
                Spacer(modifier = Modifier.height(50.dp))
            }
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Los campos marcados con * son obligatorios. Asegúrese de proporcionar la información de la llamada de la forma más precisa posible para facilitar la búsqueda.",
                fontSize = 12.sp,
                color = onSurfaceVariantColor,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón "Guardar" ---
            Button(
                onClick = { viewModel.submitDenuncia(numeroTelefonico, descripcionHechos) },
                enabled = !isSaving && numeroTelefonico.isNotBlank() && descripcionHechos.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = tertiaryColor),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                if (isSaving) {
                    CircularProgressIndicator(color = onTertiaryColor, modifier = Modifier.size(24.dp))
                } else {
                    Text(
                        "Guardar",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = onTertiaryColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // --- Diálogo de Error ---
    saveError?.let { message ->
        AlertDialog(
            onDismissRequest = { viewModel.resetStates() },
            title = { Text("Error al Enviar Reporte", color = onSurfaceColor) },
            text = { Text(message, color = onSurfaceColor) },
            confirmButton = {
                Button(onClick = { viewModel.resetStates() }) {
                    Text("Aceptar")
                }
            },
            containerColor = surfaceColor
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExtorsionPreview() {
    MaterialTheme {
        ExtorsionScreen(onNavigateBack = {})
    }
}