package mx.edu.utng.oic.denunciaapp.ui.screens

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
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
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import mx.edu.utng.oic.denunciaapp.ui.components.LabelText
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray
import mx.edu.utng.oic.denunciaapp.ui.utils.* // Importar MapSelectionDialog, checkLocationPermission, getCurrentLocation, getAddressFromLatLng

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DenunciaViolenciaScreen(
    onCancel: () -> Unit,
    onSave: (
        descripcionHecho: String,
        ubicacion: String,
        descripcionConducta: String,
        telefono: String,
        confirmarTelefono: String,
        imageUri: String?
    ) -> Unit
) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // --- Estados del formulario ---
    var descripcionHecho by remember { mutableStateOf("") }
    var ubicacionText by remember { mutableStateOf("") } // Texto de dirección legible
    var descripcionConducta by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var confirmarTelefono by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<String?>(null) }

    // --- Estados del Mapa/Ubicación ---
    var showMapDialog by remember { mutableStateOf(false) }
    var selectedLatLng by remember { mutableStateOf(DefaultLocation) }
    var mapCameraPosition by remember { mutableStateOf(CameraPosition.fromLatLngZoom(DefaultLocation, 14f)) }
    var locationPermissionGranted by remember { mutableStateOf(checkLocationPermission(context)) }


    // --- Manejo de Permisos de Ubicación ---
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        locationPermissionGranted = isGranted
        if (isGranted) {
            // Si se otorgan, obtener la ubicación actual y abrir el mapa
            getCurrentLocation(context, fusedLocationClient) { newLatLng ->
                selectedLatLng = newLatLng
                mapCameraPosition = CameraPosition.fromLatLngZoom(newLatLng, 15f)
                ubicacionText = getAddressFromLatLng(context, newLatLng)
                showMapDialog = true
            }
        } else {
            // Si no se otorgan, al menos abrir el mapa con la ubicación por defecto
            showMapDialog = true
        }
    }

    // --- Efecto para obtener la ubicación inicial al cargar (si ya hay permisos) ---
    LaunchedEffect(Unit) {
        if (locationPermissionGranted) {
            getCurrentLocation(context, fusedLocationClient) { newLatLng ->
                selectedLatLng = newLatLng
                mapCameraPosition = CameraPosition.fromLatLngZoom(newLatLng, 15f)
                ubicacionText = getAddressFromLatLng(context, newLatLng)
            }
        }
    }

    // --- Lógica para abrir el mapa/pedir permisos ---
    val openMapAction: () -> Unit = {
        if (checkLocationPermission(context)) {
            // Si ya hay permisos, abrir el diálogo
            showMapDialog = true
        } else {
            // Si no hay permisos, pedirlos
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Violencia de Género", fontWeight = FontWeight.Bold) },
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

            // --- Área de Previsualización de Imagen (Sin cambios) ---
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
                        Text("Evidencia fotográfica (opcional)", color = WireframeGray)
                    }
                } else {
                    Text("Imagen seleccionada", color = Color.DarkGray)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // --- Botones para Seleccionar Imagen (Sin cambios) ---
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

            // --- Campo "Describe el hecho" (Sin cambios) ---
            LabelText("Describe el hecho")
            OutlinedTextField(
                value = descripcionHecho,
                onValueChange = { descripcionHecho = it },
                placeholder = { Text("¿Qué sucedió?", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Ubicación" (INTEGRACIÓN DEL MAPA) ---
            LabelText("Ubicación")
            OutlinedTextField(
                value = ubicacionText, // Usamos la dirección legible
                onValueChange = { /* No permitir edición manual */ },
                placeholder = { Text("Toque para seleccionar en el mapa", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = openMapAction) {
                        Icon(Icons.Default.LocationOn, contentDescription = "Abrir mapa", tint = WireframeGray)
                    }
                },
                // Permite abrir el mapa tocando el campo de texto
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
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Describe la conducta" (Sin cambios) ---
            LabelText("Describe la conducta")
            OutlinedTextField(
                value = descripcionConducta,
                onValueChange = { descripcionConducta = it },
                placeholder = { Text("Detalle la conducta del agresor", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Teléfono de contacto" (Sin cambios) ---
            LabelText("Teléfono de contacto")
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                placeholder = { Text("Número a 10 dígitos", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Campo "Confirmar teléfono" (Sin cambios) ---
            LabelText("Confirmar teléfono")
            OutlinedTextField(
                value = confirmarTelefono,
                onValueChange = { confirmarTelefono = it },
                placeholder = { Text("Repita el número", color = WireframeGray) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = WireframeGray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.height(32.dp))

            // --- Texto descriptivo (Sin cambios) ---
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón "Guardar" (Sin cambios) ---
            Button(
                onClick = {
                    onSave(
                        descripcionHecho,
                        // Guardamos el texto de la ubicación legible, la latLng real se puede inferir o guardar aparte
                        ubicacionText,
                        descripcionConducta,
                        telefono,
                        confirmarTelefono,
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

    // --- Diálogo REAL con el Mapa de Google ---
    if (showMapDialog) {
        MapSelectionDialog(
            cameraPosition = mapCameraPosition,
            markerLocation = selectedLatLng,
            onDismiss = { showMapDialog = false },
            onLocationConfirmed = { newLatLng ->
                selectedLatLng = newLatLng
                ubicacionText = getAddressFromLatLng(context, newLatLng)
                showMapDialog = false
            },
            onCameraMove = { newCameraPosition ->
                mapCameraPosition = newCameraPosition
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DenunciaViolenciaPreview() {
    DenunciaViolenciaScreen(
        onCancel = {},
        onSave = { _, _, _, _, _, _ -> }
    )
}