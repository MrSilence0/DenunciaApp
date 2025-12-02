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
import mx.edu.utng.oic.denunciaapp.data.service.ForoService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.ForoViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.ForoViewModelFactory
import java.text.SimpleDateFormat
import java.util.*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateForumScreen(
    onNavigateBack: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary
    val surfaceColor = colorScheme.surface
    val onSurfaceColor = colorScheme.onSurface
    val backgroundColor = colorScheme.background
    val outlineColor = colorScheme.outline // Mapeo de Color.LightGray
    val secondaryContainerColor = colorScheme.secondaryContainer // Mapeo de Color(0xFFE3F2FD)
    val onSecondaryContainerColor = colorScheme.onSecondaryContainer
    val errorColor = colorScheme.error


    val foroService = remember { ForoService() }
    val userService = remember { UserService() }
    val factory = remember { ForoViewModelFactory(foroService, userService) }
    val viewModel: ForoViewModel = viewModel(factory = factory)

    val isProcessing by viewModel.isProcessing.collectAsState()
    val operationError by viewModel.operationError.collectAsState()
    val operationSuccess by viewModel.operationSuccess.collectAsState()

    var tema by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(operationError) {
        operationError?.let { errorMsg ->
            scope.launch {
                snackbarHostState.showSnackbar(errorMsg, duration = SnackbarDuration.Short)
            }
        }
    }

    LaunchedEffect(operationSuccess) {
        if (operationSuccess) {
            scope.launch {
                snackbarHostState.showSnackbar("Tema creado con éxito!", duration = SnackbarDuration.Short)
                onNavigateBack()
            }
        }
    }


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text("Nuevo Tema de Foro", fontWeight = FontWeight.Bold, color = onSurfaceColor)
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver a Foros", tint = onSurfaceColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor,
                    navigationIconContentColor = onSurfaceColor
                )
            )
        },
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
                .background(backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Tema del Foro",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = onSurfaceColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = tema,
                onValueChange = { tema = it },
                label = {
                    Text("Escribe aquí el título de tu tema...")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp, max = 200.dp),
                singleLine = false,
                maxLines = 5,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    focusedTextColor = onSurfaceColor,
                    unfocusedTextColor = onSurfaceColor,
                    focusedLabelColor = primaryColor,
                    unfocusedLabelColor = onSurfaceColor.copy(alpha = 0.6f)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(secondaryContainerColor, RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                InfoRow("Nota:", "El creador y fecha se registrarán automáticamente al guardar.",
                    onSecondaryContainerColor = onSecondaryContainerColor,
                    onSurfaceColor = onSurfaceColor
                )
                InfoRow("Fecha de Guardado:", SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date()),
                    onSecondaryContainerColor = onSecondaryContainerColor,
                    onSurfaceColor = onSurfaceColor
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    viewModel.saveForo(
                        foroId = null,
                        tema = tema
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isProcessing) colorScheme.surfaceVariant else primaryColor,
                    contentColor = if (isProcessing) onSurfaceColor else onPrimaryColor
                ),
                enabled = tema.isNotBlank() && !isProcessing
            ) {
                if (isProcessing) {
                    CircularProgressIndicator(color = if (isProcessing) onSurfaceColor else onPrimaryColor, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Creando...", color = if (isProcessing) onSurfaceColor else onPrimaryColor)
                } else {
                    Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(20.dp), tint = onPrimaryColor)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Crear Foro", fontSize = 16.sp, color = onPrimaryColor)
                }
            }
        }
    }
}
@Composable
fun InfoRow(
    label: String,
    value: String,
    onSecondaryContainerColor: Color,
    onSurfaceColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            color = onSecondaryContainerColor.copy(alpha = 0.9f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            fontWeight = FontWeight.Normal,
            color = onSecondaryContainerColor
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateForumScreenPreview() {
    MaterialTheme {
        CreateForumScreen(
            onNavigateBack = {}
        )
    }
}