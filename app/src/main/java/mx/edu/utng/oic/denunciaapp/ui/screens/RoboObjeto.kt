package mx.edu.utng.oic.denunciaapp.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocationOn
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
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import mx.edu.utng.oic.denunciaapp.ui.utils.RedCancelButton // Asumir color fijo de utilidad
import mx.edu.utng.oic.denunciaapp.ui.utils.YellowButton // Asumir color fijo de acción
import mx.edu.utng.oic.denunciaapp.ui.utils.MapSelectionDialog
import mx.edu.utng.oic.denunciaapp.ui.utils.checkLocationPermission
import mx.edu.utng.oic.denunciaapp.ui.utils.getCurrentLocation
import mx.edu.utng.oic.denunciaapp.ui.utils.getAddressFromLatLng
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.RoboObjetoViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoboObjetoScreen(
    onNavigateBack: () -> Unit
) {
    // --- Inyección de ViewModel y Estados ---
    val viewModel: RoboObjetoViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createRoboObjetoViewModelFactory()
    )
    val isSaving by viewModel.isSaving.collectAsState()
    val saveError by viewModel.saveError.collectAsState()
    val saveSuccess by viewModel.saveSuccess.collectAsState()

    // --- Contexto y Cliente de Ubicación ---
    val context = LocalContext.current
    val fusedLocationClient: FusedLocationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    // --- Estados del Formulario ---
    var tipoObjeto by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<String?>(null) }

    // --- Estados del Mapa y Ubicación ---
    var showMapDialog by remember { mutableStateOf(false) }
    var currentLocation by remember { mutableStateOf(DefaultLocation) }
    var markerLocation by remember { mutableStateOf<LatLng?>(null) }
    var cameraPosition by remember {
        mutableStateOf(CameraPosition.fromLatLngZoom(DefaultLocation, 15f))
    }
    var locationPermissionGranted by remember { mutableStateOf(checkLocationPermission(context)) }

    // --- Feedback y Snackbar ---
    val snackbarHostState = remember { SnackbarHostState() }

    // --- Colores Dinámicos del Tema ---
    val primaryColor = MaterialTheme.colorScheme.primary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant
    val surfaceColor = MaterialTheme.colorScheme.surface


    // --- Efectos y Permisos (Sin cambios relevantes al tema) ---
    LaunchedEffect(saveSuccess, saveError) {
        when {
            saveSuccess -> { /* ... lógica de snackbar ... */ viewModel.resetStates(); onNavigateBack() }
            saveError != null -> { /* ... lógica de snackbar ... */ viewModel.resetStates() }
        }
    }
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        locationPermissionGranted = isGranted
        if (isGranted) {
            getCurrentLocation(context, fusedLocationClient) { latLng ->
                currentLocation = latLng; markerLocation = latLng; cameraPosition = CameraPosition.fromLatLngZoom(latLng, 15f)
                ubicacion = getAddressFromLatLng(context, latLng)
            }
        }
    }
    LaunchedEffect(Unit) {
        if (locationPermissionGranted) {
            getCurrentLocation(context, fusedLocationClient) { latLng ->
                currentLocation = latLng; markerLocation = latLng; cameraPosition = CameraPosition.fromLatLngZoom(latLng, 15f)
                ubicacion = getAddressFromLatLng(context, latLng)
            }
        } else {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // --- Funciones para el Mapa (Sin cambios relevantes al tema) ---
    val openMapAction: () -> Unit = {
        if (locationPermissionGranted) { showMapDialog = true } else { locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION) }
    }
    val onLocationConfirmed: (LatLng) -> Unit = { latLng ->
        markerLocation = latLng; ubicacion = getAddressFromLatLng(context, latLng); cameraPosition = CameraPosition.fromLatLngZoom(latLng, cameraPosition.zoom); showMapDialog = false
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Robo de Objeto", fontWeight = FontWeight.Bold, color = onSurfaceColor)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor
                ),
                actions = {
                    TextButton(onClick = onNavigateBack) {
                        Text("Cancelar", color = RedCancelButton, fontWeight = FontWeight.Bold)
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
                            "Foto del objeto (opcional)",
                            color = onSurfaceVariantColor
                        )
                    }
                } else {
                    Text("Imagen seleccionada", color = onSurfaceColor)
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
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Tomar foto", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    // El texto debe ser visible sobre el color del botón
                    Text("Cámara", color = onSurfaceColor)
                }
                Spacer(modifier = Modifier.width(16.dp))
                // Botón "Galería"
                Button(
                    onClick = { /* Lógica para abrir la galería */ },
                    colors = ButtonDefaults.buttonColors(containerColor = onSurfaceVariantColor.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Image, contentDescription = "Seleccionar de galería", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Galería", color = onSurfaceColor)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Campos del Formulario (Ajuste de colores) ---
            val textFieldColors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedTextColor = onSurfaceColor,
                unfocusedTextColor = onSurfaceColor
            )
            val placeholder: @Composable (String) -> @Composable (() -> Unit) = { text -> { Text(text, color = onSurfaceVariantColor) } }

            // Tipo de objeto
            LabelText("Tipo de objeto",)
            OutlinedTextField(
                value = tipoObjeto,
                onValueChange = { tipoObjeto = it },
                placeholder = placeholder("Ej: Laptop, Joyería, Bicicleta"),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = textFieldColors
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Marca
            LabelText("Marca",)
            OutlinedTextField(
                value = marca,
                onValueChange = { marca = it },
                placeholder = placeholder("Ej: HP, Samsung, Sin marca"),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = textFieldColors
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Estado
            LabelText("Estado",)
            OutlinedTextField(
                value = estado,
                onValueChange = { estado = it },
                placeholder = placeholder("Ej: Nuevo, Usado, Dañado"),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = textFieldColors
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Color
            LabelText("Color",)
            OutlinedTextField(
                value = color,
                onValueChange = { color = it },
                placeholder = placeholder("Color predominante"),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = textFieldColors
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Año
            LabelText("Año",)
            OutlinedTextField(
                value = anio,
                onValueChange = { anio = it },
                placeholder = placeholder("Año de compra o fabricación"),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                colors = textFieldColors
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Ubicación" (Integración con Mapa) ---
            LabelText("Ubicación",)
            OutlinedTextField(
                value = ubicacion,
                onValueChange = { ubicacion = it },
                placeholder = placeholder("Toque para seleccionar en el mapa"),
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = openMapAction) {
                        Icon(Icons.Default.LocationOn, contentDescription = "Abrir mapa", tint = primaryColor)
                    }
                },
                interactionSource = remember { MutableInteractionSource() }
                    .also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    openMapAction()
                                }
                            }
                        }
                    },
                colors = textFieldColors
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Los campos marcados con * son obligatorios. Asegúrese de proporcionar la información de tu objeto robado de la forma más precisa posible para facilitar la búsqueda.",
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
                        tipoObjeto, marca, estado, color, anio, ubicacion, markerLocation?.latitude, markerLocation?.longitude
                    )
                },
                enabled = !isSaving,
                colors = ButtonDefaults.buttonColors(containerColor = YellowButton),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                if (isSaving) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.DarkGray,
                        strokeWidth = 3.dp
                    )
                } else {
                    Text("Guardar", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // --- Diálogo de Selección de Ubicación con Google Maps (Asumimos que MapSelectionDialog usa el tema) ---
    if (showMapDialog) {
        MapSelectionDialog(
            cameraPosition = cameraPosition,
            markerLocation = markerLocation,
            onDismiss = { showMapDialog = false },
            onLocationConfirmed = onLocationConfirmed,
            onCameraMove = { newPosition ->
                cameraPosition = newPosition
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RoboObjetoPreview() {
    RoboObjetoScreen(
        onNavigateBack = {},
    )
}