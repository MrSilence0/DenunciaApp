package mx.edu.utng.oic.denunciaapp.ui.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Looper
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import mx.edu.utng.oic.denunciaapp.ui.screens.DefaultLocation
import java.io.IOException
import java.util.Locale

// --- Constantes de Color (Reutilizadas para el Diálogo) ---
val YellowButton = Color(0xFFFFC107) // Amarillo del botón
val RedCancelButton = Color(0xFFD32F2F) // Rojo para el botón Cancelar

// --- Componente de Diálogo con el Mapa (Compartido) ---
@Composable
fun MapSelectionDialog(
    cameraPosition: CameraPosition,
    markerLocation: LatLng?,
    onDismiss: () -> Unit,
    onLocationConfirmed: (LatLng) -> Unit,
    onCameraMove: (CameraPosition) -> Unit
) {
    var confirmedLocation by remember { mutableStateOf(markerLocation ?: DefaultLocation) }

    // Controlador de la Cámara del Mapa
    val cameraPositionState = rememberCameraPositionState {
        position = cameraPosition
    }

    // Sincronizar el estado externo con el interno del mapa
    LaunchedEffect(cameraPosition) {
        if (cameraPositionState.position.target != cameraPosition.target ||
            cameraPositionState.position.zoom != cameraPosition.zoom) {
            cameraPositionState.animate(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }

    // Usar LaunchedEffect con snapshotFlow para detectar cuando el mapa deja de moverse
    LaunchedEffect(cameraPositionState) {
        snapshotFlow { cameraPositionState.isMoving }
            .collect { isMoving ->
                if (!isMoving) {
                    // Cuando el mapa está IDLE, actualizamos la ubicación confirmada
                    val newTarget = cameraPositionState.position.target
                    confirmedLocation = newTarget
                    onCameraMove(cameraPositionState.position)
                }
            }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Selecciona la Ubicación del Hecho") },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                ) {
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState,
                        onMapClick = { latLng ->
                            confirmedLocation = latLng
                        },
                        onMapLoaded = { /* Mapa cargado */ }
                    ) {
                        if (markerLocation != null) {
                            Marker(
                                state = rememberMarkerState(position = confirmedLocation),
                                title = "Ubicación del Hecho",
                                snippet = "Arrastra el mapa para mover el pin"
                            )
                        }
                    }

                    // Pin central para indicar la ubicación actual al arrastrar el mapa
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Pin de ubicación central",
                        tint = RedCancelButton,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(48.dp)
                            .offset(y = (-24).dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Lat: ${String.format("%.4f", confirmedLocation.latitude)}, Lon: ${String.format("%.4f", confirmedLocation.longitude)}",
                    fontWeight = FontWeight.Bold
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onLocationConfirmed(confirmedLocation) }) {
                Text("Confirmar", color = YellowButton)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = RedCancelButton)
            }
        }
    )
}


// --- Funciones Auxiliares de Ubicación (Compartidas) ---

// 1. Verificar Permisos
fun checkLocationPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

// 2. Obtener la ubicación actual (última conocida o una nueva)
@SuppressLint("MissingPermission")
fun getCurrentLocation(
    context: Context,
    fusedLocationClient: FusedLocationProviderClient,
    onLocationResult: (LatLng) -> Unit
) {
    val locationRequest = LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        numUpdates = 1
    }

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation?.let { location ->
                onLocationResult(LatLng(location.latitude, location.longitude))
            } ?: run {
                onLocationResult(DefaultLocation)
            }
            fusedLocationClient.removeLocationUpdates(this)
        }
    }

    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
        if (location != null) {
            onLocationResult(LatLng(location.latitude, location.longitude))
        } else {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }
}

// 3. Obtener dirección a partir de LatLng (Geocoding Inverso)
fun getAddressFromLatLng(context: Context, latLng: LatLng): String {
    val geocoder = Geocoder(context, Locale.getDefault())
    return try {
        // Suprimir la advertencia de deprecación para compatibilidad
        @Suppress("DEPRECATION")
        val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        if (addresses != null && addresses.isNotEmpty()) {
            val address = addresses[0]
            address.getAddressLine(0) ?: "Lat: ${latLng.latitude}, Lon: ${latLng.longitude}"
        } else {
            "Lat: ${latLng.latitude}, Lon: ${latLng.longitude}"
        }
    } catch (e: IOException) {
        e.printStackTrace()
        "Lat: ${latLng.latitude}, Lon: ${latLng.longitude} (Error al obtener dirección)"
    }
}