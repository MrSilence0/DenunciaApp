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
import mx.edu.utng.oic.denunciaapp.ui.components.LabelText // Se añade LabelText que se usa
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray
import mx.edu.utng.oic.denunciaapp.ui.utils.MapSelectionDialog
import mx.edu.utng.oic.denunciaapp.ui.utils.checkLocationPermission
import mx.edu.utng.oic.denunciaapp.ui.utils.getCurrentLocation
import mx.edu.utng.oic.denunciaapp.ui.utils.getAddressFromLatLng
import mx.edu.utng.oic.denunciaapp.ui.utils.YellowButton
import mx.edu.utng.oic.denunciaapp.ui.utils.RedCancelButton
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaFotograficaViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory

val DefaultLocation = LatLng(19.4326, -99.1332)
const val DefaultZoom = 15f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DenunciaFotograficaScreen(
    onCancel: () -> Unit,
    onSuccess: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary
    val tertiaryColor = colorScheme.tertiary
    val onTertiaryColor = colorScheme.onTertiary
    val errorColor = colorScheme.error
    val surfaceColor = colorScheme.surface
    val onSurfaceColor = colorScheme.onSurface
    val backgroundColor = colorScheme.background
    val outlineColor = colorScheme.outline
    val surfaceVariantColor = colorScheme.surfaceVariant
    val onSurfaceVariantColor = colorScheme.onSurfaceVariant

    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val snackbarHostState = remember { SnackbarHostState() }

    val viewModel: DenunciaFotograficaViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createDenunciaFotograficaViewModelFactory()
    )

    var description by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<String?>(null) }

    var showMapDialog by remember { mutableStateOf(false) }
    var currentCameraPosition by remember { mutableStateOf(CameraPosition.fromLatLngZoom(DefaultLocation, DefaultZoom)) }
    var selectedMarkerLocation by remember { mutableStateOf<LatLng?>(null) }
    var isLoadingLocation by remember { mutableStateOf(false) }

    var hasLocationPermission by remember {
        mutableStateOf(checkLocationPermission(context))
    }

    val saveSuccess by viewModel.saveSuccess.collectAsState()
    val saveError by viewModel.saveError.collectAsState()
    val isSaving by viewModel.isSaving.collectAsState()

    // Efecto para manejar el éxito y el error
    LaunchedEffect(saveSuccess, saveError) {
        if (saveSuccess) {
            onSuccess()
            viewModel.resetStates()
        }

        saveError?.let { errorMsg ->
            snackbarHostState.showSnackbar(
                message = errorMsg,
                actionLabel = "OK"
            )
            viewModel.resetStates()
        }
    }


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
                title = {
                    Text("Denuncia Fotográfica", fontWeight = FontWeight.Bold, color = onSurfaceColor)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor
                ),
                actions = {
                    TextButton(onClick = onCancel) {
                        Text("Cancelar", color = errorColor, fontWeight = FontWeight.Bold)
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
                .background(backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // --- Área de Previsualización de Imagen ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    // CORRECCIÓN 6: Usar surfaceVariant para el fondo de placeholder
                    .background(surfaceVariantColor, RoundedCornerShape(8.dp))
                    // CORRECCIÓN 7: Usar outline color para el borde
                    .border(1.dp, outlineColor, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (selectedImageUri == null) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = "Placeholder de imagen",
                            modifier = Modifier.size(60.dp),
                            // CORRECCIÓN 8: Usar onSurfaceVariant para iconos sutiles
                            tint = onSurfaceVariantColor
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        // CORRECCIÓN 9: Usar onSurfaceVariant para texto sutil
                        Text("Imagen a subir", color = onSurfaceVariantColor)
                    }
                } else {
                    // CORRECCIÓN 10: Usar onSurfaceColor para texto principal
                    Text("Imagen seleccionada", color = onSurfaceColor)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Botones para Seleccionar Imagen ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { selectedImageUri = "simulated_camera_uri_${System.currentTimeMillis()}" },
                    // CORRECCIÓN 11: Usar surfaceVariant para botones de media/secundarios
                    colors = ButtonDefaults.buttonColors(containerColor = surfaceVariantColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Tomar foto", modifier = Modifier.size(24.dp), tint = onSurfaceColor)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cámara", color = onSurfaceColor)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { selectedImageUri = "simulated_gallery_uri_${System.currentTimeMillis()}" },
                    // CORRECCIÓN 12: Usar surfaceVariant para botones de media/secundarios
                    colors = ButtonDefaults.buttonColors(containerColor = surfaceVariantColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Image, contentDescription = "Seleccionar de galería", modifier = Modifier.size(24.dp), tint = onSurfaceColor)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Galería", color = onSurfaceColor)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            LabelText("Describe el hecho", color = onSurfaceColor)
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                placeholder = { Text("Escribe aquí los detalles del hecho", color = onSurfaceVariantColor) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                maxLines = 5,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            LabelText("Ubicación del hecho", color = onSurfaceColor)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    placeholder = { Text("Ubicación actual o seleccionada", color = onSurfaceVariantColor) },
                    modifier = Modifier
                        .weight(1f),
                    readOnly = false,
                    trailingIcon = {
                        IconButton(onClick = {
                            requestLocationAndUpdate()
                        }, enabled = !isLoadingLocation) {
                            if (isLoadingLocation) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    strokeWidth = 2.dp,
                                    color = primaryColor
                                )
                            } else {
                                Icon(Icons.Default.LocationOn, contentDescription = "Obtener GPS", tint = primaryColor)
                            }
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryColor,
                        unfocusedBorderColor = outlineColor,
                        focusedPlaceholderColor = onSurfaceVariantColor,
                        unfocusedPlaceholderColor = onSurfaceVariantColor
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Botón para abrir el Mapa en un diálogo
                Button(
                    onClick = {
                        showMapDialog = true
                        if (selectedMarkerLocation == null) {
                            requestLocationAndUpdate()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = surfaceVariantColor),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp)
                ) {
                    Icon(Icons.Default.LocationOn, contentDescription = "Mapa", tint = onSurfaceColor)
                }
            }

            if (!hasLocationPermission && location.contains("denegado")) {
                Text(
                    "Pulsa el botón de GPS para solicitar permisos de ubicación.",
                    color = errorColor,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }


            Spacer(modifier = Modifier.height(32.dp))

            // --- Texto descriptivo ---
            Text(
                text = "Los campos marcados con * son obligatorios. Asegúrese de proporcionar la información de la ubicación de la forma más precisa posible para facilitar la búsqueda.",
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
                        description = description,
                        locationAddress = location,
                        latitud = selectedMarkerLocation?.latitude,
                        longitud = selectedMarkerLocation?.longitude,
                        imageUri = selectedImageUri
                    )
                },
                enabled = !isSaving && description.isNotBlank() && location.isNotBlank(),
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

    // --- Diálogo para mostrar el Mapa interactivo ---
    if (showMapDialog) {
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
    MaterialTheme {
        DenunciaFotograficaScreen(
            onCancel = {},
            onSuccess = {}
        )
    }
}