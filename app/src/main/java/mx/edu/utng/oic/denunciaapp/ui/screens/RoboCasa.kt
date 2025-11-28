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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import mx.edu.utng.oic.denunciaapp.ui.components.LabelText
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory // Importar la factoría central
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.RoboCasaViewModel // Importar el ViewModel
import mx.edu.utng.oic.denunciaapp.ui.utils.MapSelectionDialog
import mx.edu.utng.oic.denunciaapp.ui.utils.checkLocationPermission
import mx.edu.utng.oic.denunciaapp.ui.utils.getCurrentLocation
import mx.edu.utng.oic.denunciaapp.ui.utils.getAddressFromLatLng
import mx.edu.utng.oic.denunciaapp.ui.utils.RedCancelButton
import mx.edu.utng.oic.denunciaapp.ui.utils.YellowButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoboCasaScreen(
    onNavigateBack: () -> Unit,
    viewModel: RoboCasaViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createRoboCasaViewModelFactory()
    )
) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // --- Observación de Estados del ViewModel ---
    val isSaving by viewModel.isSaving.collectAsState()
    val saveError by viewModel.saveError.collectAsState()
    val saveSuccess by viewModel.saveSuccess.collectAsState()

    // --- Estados del formulario local ---
    var descripcion by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var confirmarTelefono by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<String?>(null) } // Simulación de URI de imagen

    // --- Estado del Mapa y Ubicación ---
    var showMapDialog by remember { mutableStateOf(false) }
    var currentCameraPosition by remember { mutableStateOf(CameraPosition.fromLatLngZoom(DefaultLocation, DefaultZoom)) }
    var selectedMarkerLocation by remember { mutableStateOf<LatLng?>(null) }
    var isLoadingLocation by remember { mutableStateOf(false) }

    // --- Estados de Permisos ---
    var hasLocationPermission by remember {
        mutableStateOf(checkLocationPermission(context))
    }

    // --- Efecto para Navegación en Éxito ---
    LaunchedEffect(saveSuccess) {
        if (saveSuccess) {
            onNavigateBack() // Vuelve a la pantalla anterior al guardar con éxito
            viewModel.resetStates()
        }
    }

    // Launcher para solicitar permisos de ubicación
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            hasLocationPermission = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                    permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

            if (hasLocationPermission) {
                isLoadingLocation = true
                getCurrentLocation(context, fusedLocationClient) { latLng ->
                    currentCameraPosition = CameraPosition.fromLatLngZoom(latLng, DefaultZoom)
                    selectedMarkerLocation = latLng
                    ubicacion = getAddressFromLatLng(context, latLng)
                    isLoadingLocation = false
                }
            } else {
                ubicacion = "Permiso de ubicación denegado."
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
                ubicacion = getAddressFromLatLng(context, latLng)
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
                title = { Text("Robo a Casa Habitación", fontWeight = FontWeight.Bold) },
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
                        Text("Foto del lugar (opcional)", color = WireframeGray)
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
                // Botón "Cámara"
                Button(
                    onClick = { /* Lógica para abrir la cámara */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    shape = RoundedCornerShape(8.dp),
                    enabled = !isSaving,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Tomar foto", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cámara")
                }
                Spacer(modifier = Modifier.width(16.dp))
                // Botón "Galería"
                Button(
                    onClick = {
                        // Simulación de adjuntar imagen
                        selectedImageUri = "file://path/to/image.jpg"
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    shape = RoundedCornerShape(8.dp),
                    enabled = !isSaving,
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
                value = descripcion,
                onValueChange = { descripcion = it },
                placeholder = { Text("Detalle lo sucedido...", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isSaving,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Ubicación" (Con GPS y Mapa) ---
            LabelText("Ubicación del Domicilio Robado")
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = ubicacion,
                    onValueChange = { ubicacion = it },
                    placeholder = { Text("Ubicación actual o seleccionada", color = WireframeGray) },
                    modifier = Modifier
                        .weight(1f),
                    readOnly = false,
                    enabled = !isSaving,
                    trailingIcon = {
                        // Botón para obtener la ubicación actual (GPS)
                        IconButton(onClick = {
                            requestLocationAndUpdate()
                        }, enabled = !isLoadingLocation && !isSaving) {
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
                    enabled = !isSaving,
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp)
                ) {
                    Icon(Icons.Default.LocationOn, contentDescription = "Mapa", tint = Color.White)
                }
            }

            if (!hasLocationPermission && ubicacion.contains("denegado")) {
                Text(
                    "Pulsa el botón de GPS para solicitar permisos de ubicación.",
                    color = RedCancelButton,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Teléfono de contacto" ---
            LabelText("Teléfono de contacto")
            OutlinedTextField(
                value = telefono,
                onValueChange = {
                    if (it.length <= 10) {
                        telefono = it.filter { char -> char.isDigit() }
                    }
                },
                placeholder = { Text("Número a 10 dígitos", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isSaving,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Confirmar teléfono" ---
            LabelText("Confirmar teléfono")
            OutlinedTextField(
                value = confirmarTelefono,
                onValueChange = {
                    if (it.length <= 10) {
                        confirmarTelefono = it.filter { char -> char.isDigit() }
                    }
                },
                placeholder = { Text("Repita el número", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isSaving,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(32.dp))

            // --- Texto descriptivo (Lorem Ipsum) ---
            Text(
                text = "La ubicación es crucial para la investigación. Puede usar el GPS para su ubicación actual o el mapa para señalar la dirección exacta del domicilio robado.",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón "Guardar" ---
            Button(
                onClick = {
                    viewModel.submitDenuncia(
                        descripcion = descripcion,
                        locationAddress = ubicacion,
                        latitud = selectedMarkerLocation?.latitude,
                        longitud = selectedMarkerLocation?.longitude,
                        telefono = telefono,
                        confirmarTelefono = confirmarTelefono
                    )
                },
                // Habilitado si no está guardando y los campos obligatorios están llenos
                enabled = !isSaving && descripcion.isNotBlank() && ubicacion.isNotBlank() && telefono.isNotBlank() && confirmarTelefono.isNotBlank(),
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

    // --- Diálogo para mostrar el Mapa interactivo (Componente Compartido) ---
    if (showMapDialog) {
        MapSelectionDialog(
            cameraPosition = currentCameraPosition,
            markerLocation = selectedMarkerLocation,
            onDismiss = { showMapDialog = false },
            onLocationConfirmed = { latLng ->
                selectedMarkerLocation = latLng
                // Actualiza la ubicación en el TextField
                ubicacion = getAddressFromLatLng(context, latLng)
                currentCameraPosition = CameraPosition.fromLatLngZoom(latLng, DefaultZoom)
                showMapDialog = false
            },
            onCameraMove = { cameraPos ->
                currentCameraPosition = cameraPos
            }
        )
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
fun RoboCasaPreview() {
    // Se usa el constructor por defecto para el preview
    RoboCasaScreen(onNavigateBack = {})
}