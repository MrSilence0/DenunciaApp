package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.edu.utng.oic.denunciaapp.data.model.Foro
import java.text.SimpleDateFormat
import java.util.*

// Colores
val SuccessGreen = Color(0xFF4CAF50)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateForumScreen(
    // Callback para volver a la lista de foros
    onNavigateBack: () -> Unit,
    // Callback para crear el foro (simula la interacción con un ViewModel)
    onCreateForum: (Foro) -> Unit
) {
    // --- Estados del Formulario ---
    var tema by remember { mutableStateOf("") }
    var isSaving by remember { mutableStateOf(false) }

    // Simulación de datos del usuario/sistema
    val currentUsername = "UsuarioTemporal123"

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Nuevo Tema de Foro", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver a Foros")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- 1. Campo de Tema ---
            Text(
                text = "Tema del Foro",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = tema,
                onValueChange = { tema = it },
                label = { Text("Escribe aquí el título de tu tema...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp, max = 200.dp),
                singleLine = false,
                maxLines = 5,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    unfocusedBorderColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- 2. Información Automática del Foro ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE3F2FD), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                InfoRow("Creador:", currentUsername)
                InfoRow("Fecha de Creación:", SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date()))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- 3. Botón de Creación ---
            Button(
                onClick = {
                    if (tema.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar("El tema no puede estar vacío.", duration = SnackbarDuration.Short)
                        }
                    } else if (!isSaving) {
                        isSaving = true

                        // 1. Crear el objeto Foro con datos simulados
                        val nuevoForo = Foro(
                            id = UUID.randomUUID().toString(),
                            tema = tema,
                            username = currentUsername,
                            creationDate = Date(),
                            responseCount = 0
                        )

                        // 2. Ejecutar la acción de creación (simulación de guardado)
                        onCreateForum(nuevoForo)

                        // 3. Simular un delay para guardar y mostrar éxito
                        scope.launch {
                            delay(500) // Simular latencia de red
                            snackbarHostState.showSnackbar("¡Foro '${nuevoForo.tema}' creado con éxito!", duration = SnackbarDuration.Short)
                            isSaving = false
                            onNavigateBack() // Volver a la lista de foros
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = if (isSaving) Color.Gray else PrimaryBlue),
                enabled = tema.isNotBlank() && !isSaving
            ) {
                if (isSaving) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Guardando...")
                } else {
                    Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Crear Foro", fontSize = 16.sp)
                }
            }
        }
    }
}

// Componente auxiliar para mostrar la información del foro
@Composable
fun InfoRow(label: String, value: String, isCurrent: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            color = Color.Black.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
            color = if (isCurrent) PrimaryBlue else Color.Black
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateForumScreenPreview() {
    CreateForumScreen(
        onNavigateBack = {},
        onCreateForum = {}
    )
}