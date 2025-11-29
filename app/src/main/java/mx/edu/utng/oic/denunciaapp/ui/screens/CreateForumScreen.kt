package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import mx.edu.utng.oic.denunciaapp.data.service.ForoService // Necesario para la Factoría
import mx.edu.utng.oic.denunciaapp.data.service.UserService // Necesario para la Factoría
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.ForoViewModel // ViewModel real
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.ForoViewModelFactory // Factoría real
import java.text.SimpleDateFormat
import java.util.*

val SuccessGreen = Color(0xFF4CAF50)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateForumScreen(
    onNavigateBack: () -> Unit
) {
    // --- 1. Inicializar ViewModel y Factory (usando servicios simulados/reales) ---
    // NOTA: En una app real, los servicios se obtendrían mediante inyección de dependencia
    val foroService = remember { ForoService() }
    val userService = remember { UserService() }
    val factory = remember { ForoViewModelFactory(foroService, userService) }
    val viewModel: ForoViewModel = viewModel(factory = factory)

    // --- 2. Observar Estados del ViewModel ---
    val isProcessing by viewModel.isProcessing.collectAsState()
    val operationError by viewModel.operationError.collectAsState()
    val operationSuccess by viewModel.operationSuccess.collectAsState()

    // --- 3. Estados de Formulario Local ---
    var tema by remember { mutableStateOf("") }
    // Eliminamos la simulación de username, se manejará en el VM

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // --- 4. Manejo de Efectos (Error y Éxito) ---

    // Manejar errores
    LaunchedEffect(operationError) {
        operationError?.let { errorMsg ->
            scope.launch {
                snackbarHostState.showSnackbar(errorMsg, duration = SnackbarDuration.Short)
            }
        }
    }

    // Manejar éxito y volver
    LaunchedEffect(operationSuccess) {
        if (operationSuccess) {
            scope.launch {
                snackbarHostState.showSnackbar("Tema creado con éxito!", duration = SnackbarDuration.Short)
                // Usar delay si quieres que el usuario vea el mensaje de éxito antes de navegar
                // delay(500)
                onNavigateBack()
            }
        }
    }


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Nuevo Tema de Foro", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver a Foros")
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

            // --- 2. Información Automática del Foro (Datos Ficticios/Temporales) ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE3F2FD), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                // Ya no necesitamos el nombre de usuario aquí, pues se obtiene en el VM
                InfoRow("Nota:", "El creador y fecha se registrarán automáticamente al guardar.")
                InfoRow("Fecha de Guardado:", SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date()))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- 3. Botón de Creación ---
            Button(
                onClick = {
                    // Llamar al ViewModel, el VM se encarga de la validación interna, auth y guardado.
                    viewModel.saveForo(
                        foroId = null, // Creamos uno nuevo
                        tema = tema
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = if (isProcessing) Color.Gray else PrimaryBlue),
                // Deshabilitar si está vacío o en proceso
                enabled = tema.isNotBlank() && !isProcessing
            ) {
                if (isProcessing) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Creando...")
                } else {
                    Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Crear Foro", fontSize = 16.sp)
                }
            }
        }
    }
}
@Composable
fun InfoRow(label: String, value: String) {
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
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateForumScreenPreview() {
    CreateForumScreen(
        onNavigateBack = {}
    )
}