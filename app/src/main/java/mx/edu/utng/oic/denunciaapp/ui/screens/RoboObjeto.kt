package mx.edu.utng.oic.denunciaapp.ui.screens

import android.app.Activity
import android.content.Context
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
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

// Importar los componentes comunes y utilidades de mapa
import mx.edu.utng.oic.denunciaapp.ui.components.LabelText
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray
import mx.edu.utng.oic.denunciaapp.ui.utils.RedCancelButton
import mx.edu.utng.oic.denunciaapp.ui.utils.YellowButton
import mx.edu.utng.oic.denunciaapp.ui.utils.MapSelectionDialog
import mx.edu.utng.oic.denunciaapp.ui.utils.checkLocationPermission
import mx.edu.utng.oic.denunciaapp.ui.utils.getCurrentLocation
import mx.edu.utng.oic.denunciaapp.ui.utils.getAddressFromLatLng

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoboObjetoScreen(
    onNavigateBack: () -> Unit,
    onSave: (
        tipoObjeto: String,
        marca: String,
        estado: String,
        color: String,
        anio: String,
        ubicacion: String,
        imageUri: String?
    ) -> Unit
) {
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
    var markerLocation by remember { mutableStateOf<LatLng?>(null) } // Ubicación seleccionada en el mapa
    var cameraPosition by remember {
        mutableStateOf(CameraPosition.fromLatLngZoom(DefaultLocation, 15f))
    }
    var locationPermissionGranted by remember { mutableStateOf(checkLocationPermission(context)) }

    // --- Launcher para Permisos de Ubicación ---
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        locationPermissionGranted = isGranted
        if (isGranted) {
            // Si se concede, obtener la ubicación actual
            getCurrentLocation(context, fusedLocationClient) { latLng ->
                currentLocation = latLng
                markerLocation = latLng
                cameraPosition = CameraPosition.fromLatLngZoom(latLng, 15f)
            }
        }
    }

    // --- Efecto para Cargar la Ubicación Inicial ---
    LaunchedEffect(Unit) {
        if (locationPermissionGranted) {
            getCurrentLocation(context, fusedLocationClient) { latLng ->
                currentLocation = latLng
                markerLocation = latLng
                cameraPosition = CameraPosition.fromLatLngZoom(latLng, 15f)
                ubicacion = getAddressFromLatLng(context, latLng)
            }
        } else {
            // Solicitar permisos al inicio si no están granted
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // --- Funciones para el Mapa ---
    val openMapAction: () -> Unit = {
        if (locationPermissionGranted) {
            showMapDialog = true
        } else {
            // Solicitar permisos antes de abrir el mapa si no están granted
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    val onLocationConfirmed: (LatLng) -> Unit = { latLng ->
        markerLocation = latLng // Actualiza el pin seleccionado
        ubicacion = getAddressFromLatLng(context, latLng)
        cameraPosition = CameraPosition.fromLatLngZoom(latLng, cameraPosition.zoom)
        showMapDialog = false
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Robo de Objeto", fontWeight = FontWeight.Bold) },
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
            Spacer(modifier = Modifier.height(16.dp))

            // --- Área de Previsualización de Imagen ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Altura para el placeholder de imagen
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
                        Text("Foto del objeto (opcional)", color = WireframeGray)
                    }
                } else {
                    Text("Imagen seleccionada", color = Color.DarkGray)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Botones para Seleccionar Imagen (Lógica placeholder) ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Botón "Cámara"
                Button(
                    onClick = { /* Lógica para abrir la cámara */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Tomar foto", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cámara")
                }
                Spacer(modifier = Modifier.width(16.dp))
                // Botón "Galería"
                Button(
                    onClick = { /* Lógica para abrir la galería */ },
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

            // --- Campo "Tipo de objeto" ---
            LabelText("Tipo de objeto")
            OutlinedTextField(
                value = tipoObjeto,
                onValueChange = { tipoObjeto = it },
                placeholder = { Text("Ej: Laptop, Joyería, Bicicleta", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campos restantes (Marca, Estado, Color, Año) ---
            LabelText("Marca")
            OutlinedTextField(
                value = marca,
                onValueChange = { marca = it },
                placeholder = { Text("Ej: HP, Samsung, Sin marca", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            LabelText("Estado")
            OutlinedTextField(
                value = estado,
                onValueChange = { estado = it },
                placeholder = { Text("Ej: Nuevo, Usado, Dañado", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            LabelText("Color")
            OutlinedTextField(
                value = color,
                onValueChange = { color = it },
                placeholder = { Text("Color predominante", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            LabelText("Año")
            OutlinedTextField(
                value = anio,
                onValueChange = { anio = it },
                placeholder = { Text("Año de compra o fabricación", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Ubicación" (Integración con Mapa) ---
            LabelText("Ubicación")
            OutlinedTextField(
                value = ubicacion,
                onValueChange = { ubicacion = it },
                placeholder = { Text("Toque para seleccionar en el mapa", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true, // Evita escritura manual directa para forzar el mapa
                trailingIcon = {
                    IconButton(onClick = openMapAction) {
                        Icon(Icons.Default.LocationOn, contentDescription = "Abrir mapa", tint = WireframeGray)
                    }
                },
                // Permite abrir el mapa al tocar el campo
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
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
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
                    onSave(
                        tipoObjeto,
                        marca,
                        estado,
                        color,
                        anio,
                        ubicacion,
                        selectedImageUri
                    )
                },
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

    // --- Diálogo de Selección de Ubicación con Google Maps ---
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
        onSave = { _, _, _, _, _, _, _ -> }
    )
}