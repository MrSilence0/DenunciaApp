package mx.edu.utng.oic.denunciaapp.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import mx.edu.utng.oic.denunciaapp.ui.components.LabelText
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray
import mx.edu.utng.oic.denunciaapp.ui.utils.MapSelectionDialog
import mx.edu.utng.oic.denunciaapp.ui.utils.checkLocationPermission
import mx.edu.utng.oic.denunciaapp.ui.utils.getCurrentLocation
import mx.edu.utng.oic.denunciaapp.ui.utils.getAddressFromLatLng
import mx.edu.utng.oic.denunciaapp.ui.utils.YellowButton
import mx.edu.utng.oic.denunciaapp.ui.utils.RedCancelButton
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaFotograficaViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory


// --- Constantes de la pantalla ---
// Latitud y Longitud inicial (Ejemplo: Centro de México)
val DefaultLocation = LatLng(19.4326, -99.1332)
const val DefaultZoom = 15f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DenunciaFotograficaScreen(
    onCancel: () -> Unit,
    onSuccess: () -> Unit // Nueva acción para cuando la denuncia se guarda exitosamente
) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val snackbarHostState = remember { SnackbarHostState() }

    // 1. Inicialización del ViewModel
    val viewModel: DenunciaFotograficaViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createDenunciaFotograficaViewModelFactory()
    )

    // --- Estados del formulario ---
    var description by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<String?>(null) } // URI de la imagen (simulada)

    // --- Estado del Mapa y Ubicación ---
    var showMapDialog by remember { mutableStateOf(false) }
    var currentCameraPosition by remember { mutableStateOf(CameraPosition.fromLatLngZoom(DefaultLocation, DefaultZoom)) }
    var selectedMarkerLocation by remember { mutableStateOf<LatLng?>(null) } // Coordenadas del marcador
    var isLoadingLocation by remember { mutableStateOf(false) }

    // --- Estados de Permisos ---
    var hasLocationPermission by remember {
        mutableStateOf(checkLocationPermission(context))
    }

    // --- Feedback del ViewModel ---
    // CORRECCIÓN: Usar collectAsState para observar los StateFlow del ViewModel
    val saveSuccess by viewModel.saveSuccess.collectAsState()
    val saveError by viewModel.saveError.collectAsState()
    val isSaving by viewModel.isSaving.collectAsState()

    // Efecto para manejar el éxito y el error
    LaunchedEffect(saveSuccess, saveError) {
        if (saveSuccess) {
            onSuccess() // Navega o muestra éxito
            viewModel.resetStates() // Limpiar después de la navegación
        }

        saveError?.let { errorMsg ->
            snackbarHostState.showSnackbar(
                message = errorMsg,
                actionLabel = "OK"
            )
            // Limpiar el error después de mostrar
            viewModel.resetStates()
        }
    }


    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            hasLocationPermission = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                    permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

            // Si el permiso fue concedido, intenta obtener la ubicación.
            if (hasLocationPermission) {
                isLoadingLocation = true
                getCurrentLocation(context, fusedLocationClient) { latLng ->
                    // Mueve la cámara al lugar actual después de obtener la ubicación.
                    currentCameraPosition = CameraPosition.fromLatLngZoom(latLng, DefaultZoom)
                    selectedMarkerLocation = latLng
                    location = getAddressFromLatLng(context, latLng)
                    isLoadingLocation = false
                }
            } else {
                location = "Permiso de ubicación denegado."
                isLoadingLocation = false
            }
        }
    )

    // Función para solicitar permisos e intentar obtener ubicación
    fun requestLocationAndUpdate() {
        if (hasLocationPermission) {
            isLoadingLocation = true
            getCurrentLocation(context, fusedLocationClient) { latLng ->
                currentCameraPosition = CameraPosition.fromLatLngZoom(latLng, DefaultZoom)
                selectedMarkerLocation = latLng
                location = getAddressFromLatLng(context, latLng)
                isLoadingLocation = false
            }
        } else {
            locationPermissionLauncher.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ))
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Denuncia Fotográfica", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                actions = {
                    TextButton(onClick = onCancel) {
                        Text("Cancelar", color = RedCancelButton, fontWeight = FontWeight.Bold)
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }, // <--- Se añade el SnackbarHost
        containerColor = Color.White
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
                        Text("Imagen a subir", color = WireframeGray)
                    }
                } else {
                    Text("Imagen seleccionada", color = Color.DarkGray)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Botones para Seleccionar Imagen (Simulación) ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { selectedImageUri = "simulated_camera_uri_${System.currentTimeMillis()}" }, // Simulación
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Tomar foto", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cámara")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { selectedImageUri = "simulated_gallery_uri_${System.currentTimeMillis()}" }, // Simulación
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Image, contentDescription = "Seleccionar de galería", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Galería")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Campo "Describe el hecho" ---
            LabelText("Describe el hecho")
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                placeholder = { Text("Escribe aquí los detalles del hecho", color = WireframeGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                maxLines = 5,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- Campo "Ubicación del hecho" (con botón de GPS y Mapa) ---
            LabelText("Ubicación del hecho")
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    placeholder = { Text("Ubicación actual o seleccionada", color = WireframeGray) },
                    modifier = Modifier
                        .weight(1f),
                    readOnly = false,
                    trailingIcon = {
                        // Botón para obtener la ubicación actual (GPS)
                        IconButton(onClick = {
                            requestLocationAndUpdate()
                        }, enabled = !isLoadingLocation) {
                            if (isLoadingLocation) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Icon(Icons.Default.LocationOn, contentDescription = "Obtener GPS", tint = WireframeGray)
                            }
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = WireframeGray,
                        unfocusedBorderColor = Color.LightGray
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Botón para abrir el Mapa en un diálogo
                Button(
                    onClick = {
                        showMapDialog = true
                        // Si no hay marcador, inicia en la última ubicación conocida
                        if (selectedMarkerLocation == null) {
                            requestLocationAndUpdate()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp)
                ) {
                    Icon(Icons.Default.LocationOn, contentDescription = "Mapa", tint = Color.White)
                }
            }

            if (!hasLocationPermission && location.contains("denegado")) {
                Text(
                    "Pulsa el botón de GPS para solicitar permisos de ubicación.",
                    color = RedCancelButton,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }


            Spacer(modifier = Modifier.height(32.dp))

            // --- Texto descriptivo (Lorem Ipsum) ---
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón "Guardar" ---
            Button(
                onClick = {
                    // 2. Llamada al ViewModel con los datos del formulario
                    viewModel.submitDenuncia(
                        description = description,
                        locationAddress = location,
                        latitud = selectedMarkerLocation?.latitude,
                        longitud = selectedMarkerLocation?.longitude,
                        imageUri = selectedImageUri
                    )
                },
                // Deshabilitar si está guardando
                enabled = !isSaving && description.isNotBlank() && location.isNotBlank(), // Validación básica
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

    // --- Diálogo para mostrar el Mapa interactivo ---
    if (showMapDialog) {
        // Usando el componente de Diálogo COMPARTIDO
        MapSelectionDialog(
            cameraPosition = currentCameraPosition,
            markerLocation = selectedMarkerLocation,
            onDismiss = { showMapDialog = false },
            onLocationConfirmed = { latLng ->
                selectedMarkerLocation = latLng
                location = getAddressFromLatLng(context, latLng)
                currentCameraPosition = CameraPosition.fromLatLngZoom(latLng, DefaultZoom)
                showMapDialog = false
            },
            onCameraMove = { cameraPos ->
                currentCameraPosition = cameraPos
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DenunciaFotograficaPreview() {
    DenunciaFotograficaScreen(
        onCancel = {},
        onSuccess = {}
    )
}