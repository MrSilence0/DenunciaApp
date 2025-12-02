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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import mx.edu.utng.oic.denunciaapp.ui.components.LabelText
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray
import mx.edu.utng.oic.denunciaapp.ui.utils.*
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaAppViewModelFactory
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.DenunciaViolenciaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DenunciaViolenciaScreen(
    onCancel: () -> Unit,
    onReportSaved: () -> Unit
) {
    // --- Colores Dinámicos del Tema ---
    val primaryColor = MaterialTheme.colorScheme.primary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val onTertiaryColor = MaterialTheme.colorScheme.onTertiary
    val errorColor = MaterialTheme.colorScheme.error
    val surfaceColor = MaterialTheme.colorScheme.surface
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val backgroundColor = MaterialTheme.colorScheme.background
    val outlineColor = MaterialTheme.colorScheme.outline
    val surfaceVariantColor = MaterialTheme.colorScheme.surfaceVariant
    val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant

    // --- ViewModel y Observación de Estados (Se mantiene) ---
    val viewModel: DenunciaViolenciaViewModel = viewModel(
        factory = DenunciaAppViewModelFactory.createDenunciaViolenciaViewModelFactory()
    )
    val isSaving by viewModel.isSaving.collectAsStateWithLifecycle()
    val saveError by viewModel.saveError.collectAsStateWithLifecycle()
    val saveSuccess by viewModel.saveSuccess.collectAsStateWithLifecycle()


    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // --- Estados del formulario ---
    var descripcionHecho by remember { mutableStateOf("") }
    var ubicacionText by remember { mutableStateOf("") }
    var descripcionConducta by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var confirmarTelefono by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<String?>(null) }

    // --- Estados del Mapa/Ubicación ---
    var showMapDialog by remember { mutableStateOf(false) }
    var selectedLatLng by remember { mutableStateOf(DefaultLocation) }
    var mapCameraPosition by remember { mutableStateOf(CameraPosition.fromLatLngZoom(DefaultLocation, 14f)) }
    var locationPermissionGranted by remember { mutableStateOf(checkLocationPermission(context)) }


    // --- Manejo de Permisos de Ubicación---
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        locationPermissionGranted = isGranted
        if (isGranted) {
            getCurrentLocation(context, fusedLocationClient) { newLatLng ->
                selectedLatLng = newLatLng
                mapCameraPosition = CameraPosition.fromLatLngZoom(newLatLng, 15f)
                ubicacionText = getAddressFromLatLng(context, newLatLng)
                showMapDialog = true
            }
        } else {
            showMapDialog = true
        }
    }

    // --- Efecto para obtener la ubicación inicial---
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
            showMapDialog = true
        } else {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // --- Manejo de Resultado de Guardado ---
    LaunchedEffect(saveSuccess) {
        if (saveSuccess) {
            onReportSaved()
            viewModel.resetStates()
        }
    }

    // --- Snackbar Host State  ---
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(saveError) {
        saveError?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = "Aceptar",
                duration = SnackbarDuration.Short
            )
            viewModel.resetStates()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Violencia de Género", fontWeight = FontWeight.Bold, color = onSurfaceColor)
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
                // CORRECCIÓN 5: Asegurar el fondo dinámico
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
                            tint = onSurfaceVariantColor
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Evidencia fotográfica (opcional)", color = onSurfaceVariantColor)
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
                    colors = ButtonDefaults.buttonColors(containerColor = surfaceVariantColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Tomar foto", modifier = Modifier.size(24.dp), tint = onSurfaceColor)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cámara", color = onSurfaceColor)
                }
                Spacer(modifier = Modifier.width(16.dp))
                // Botón "Galería"
                Button(
                    onClick = { /* Lógica para abrir la galería */ },
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

            // --- Campos de texto ---

            // Descripción del hecho
            LabelText("Describe el hecho", color = onSurfaceColor)
            OutlinedTextField(
                value = descripcionHecho,
                onValueChange = { descripcionHecho = it },
                placeholder = { Text("¿Qué sucedió?", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Ubicación
            LabelText("Ubicación", color = onSurfaceColor)
            OutlinedTextField(
                value = ubicacionText,
                onValueChange = { /* No permitir edición manual */ },
                placeholder = { Text("Toque para seleccionar en el mapa", color = onSurfaceVariantColor) },
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
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Descripción de la conducta
            LabelText("Describe la conducta", color = onSurfaceColor)
            OutlinedTextField(
                value = descripcionConducta,
                onValueChange = { descripcionConducta = it },
                placeholder = { Text("Detalle la conducta del agresor", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Teléfono de contacto
            LabelText("Teléfono de contacto", color = onSurfaceColor)
            OutlinedTextField(
                value = telefono,
                onValueChange = { if (it.length <= 10) telefono = it },
                placeholder = { Text("Número a 10 dígitos", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Confirmar teléfono
            LabelText("Confirmar teléfono", color = onSurfaceColor)
            OutlinedTextField(
                value = confirmarTelefono,
                onValueChange = { if (it.length <= 10) confirmarTelefono = it },
                placeholder = { Text("Repita el número", color = onSurfaceVariantColor) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = outlineColor,
                    focusedPlaceholderColor = onSurfaceVariantColor,
                    unfocusedPlaceholderColor = onSurfaceVariantColor
                )
            )
            Spacer(modifier = Modifier.height(32.dp))

            // --- Texto descriptivo ---
            Text(
                text = "Su información es confidencial y solo será utilizada para los fines de la denuncia. Asegúrese de que todos los datos sean correctos antes de guardar.",
                fontSize = 12.sp,
                color = onSurfaceVariantColor,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Botón "Guardar" ---
            Button(
                onClick = {
                    viewModel.submitDenuncia(
                        descripcionHecho = descripcionHecho,
                        ubicacionText = ubicacionText.ifBlank { null },
                        descripcionConducta = descripcionConducta,
                        telefono = telefono,
                        confirmarTelefono = confirmarTelefono,
                        latitud = selectedLatLng.latitude,
                        longitud = selectedLatLng.longitude,
                        imageUri = selectedImageUri
                    )
                },
                enabled = !isSaving,
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
                        // CORRECCIÓN 19: Usar onTertiary (asegura contraste)
                        color = onTertiaryColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // --- Diálogo REAL con el Mapa de Google  ---
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
    MaterialTheme {
        DenunciaViolenciaScreen(
            onCancel = {},
            onReportSaved = {}
        )
    }
}