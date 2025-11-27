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

// Importar los componentes comunes y estilos
import mx.edu.utng.oic.denunciaapp.ui.components.LabelText
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray
import mx.edu.utng.oic.denunciaapp.ui.utils.RedCancelButton
import mx.edu.utng.oic.denunciaapp.ui.utils.YellowButton

val BlueAttachButton = Color(0xFF1E88E5)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExtorsionScreen(
    onNavigateBack: () -> Unit, // Renombrado de 'onCancel' a 'onNavigateBack'
    onSave: (
        numeroTelefonico: String,
        descripcionHechos: String,
        audioUri: String? // URI del archivo de audio adjunto (simulado)
    ) -> Unit
) {
    // --- Estados del formulario ---
    var numeroTelefonico by remember { mutableStateOf("") }
    var descripcionHechos by remember { mutableStateOf("") }
    var audioAdjuntoUri by remember { mutableStateOf<String?>(null) } // URI del audio

    // Estado para simular la reproducción de audio
    var isPlaying by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reporte de Extorsión", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                actions = {
                    TextButton(onClick = onNavigateBack) {
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
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            // --- Botón "Adjuntar llamada" ---
            Button(
                onClick = {
                    // Simulación de adjuntar un archivo de audio
                    audioAdjuntoUri = "file://path/to/recording.mp3"
                },
                colors = ButtonDefaults.buttonColors(containerColor = BlueAttachButton),
                shape = RoundedCornerShape(8.dp),
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
                    IconButton(onClick = { /* Anterior */ }) { Icon(Icons.Default.SkipPrevious, contentDescription = "Pista anterior", tint = WireframeGray) }
                    IconButton(onClick = { /* Rewind */ }) { Icon(Icons.Default.FastRewind, contentDescription = "Rebobinar", tint = WireframeGray) }

                    // Botón Play/Pause central
                    Button(
                        onClick = { isPlaying = !isPlaying },
                        modifier = Modifier.size(56.dp),
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

                    IconButton(onClick = { /* Fast Forward */ }) { Icon(Icons.Default.FastForward, contentDescription = "Adelantar", tint = WireframeGray) }
                    IconButton(onClick = { /* Siguiente */ }) { Icon(Icons.Default.SkipNext, contentDescription = "Pista siguiente", tint = WireframeGray) }
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
                onClick = { onSave(numeroTelefonico, descripcionHechos, audioAdjuntoUri) },
                colors = ButtonDefaults.buttonColors(containerColor = YellowButton),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Guardar", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExtorsionPreview() {
    ExtorsionScreen(
        onNavigateBack = {},
        onSave = { _, _, _ -> }
    )
}

