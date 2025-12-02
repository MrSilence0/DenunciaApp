package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import mx.edu.utng.oic.denunciaapp.ui.components.WireframeGray

// Modelo de datos para las agencias
data class Agencia(
    val id: Int,
    val nombre: String,
    val direccion: String,
    val horario: String,
    val latLng: LatLng
)

// Datos
private val agenciesData = listOf(
    Agencia(
        id = 1,
        nombre = "MP Dolores Hidalgo",
        direccion = "Av. De los Heroes 8, Viñedo, 37804 Dolores Hidalgo...",
        horario = "24 horas",
        latLng = LatLng(21.18597861019045, -100.91918814562699)
    ),
    Agencia(
        id = 2,
        nombre = "Fiscalía San Miguel de Allende",
        direccion = "Blvrd de la Conspiración, La Luz, 37746 San Miguel de Allende, Gto.",
        horario = "09:00 - 18:00",
        latLng = LatLng(20.9149, -100.7583)
    ),
    Agencia(
        id = 3,
        nombre = "Módulo de Atención León",
        direccion = "Blvd. Hermanos Aldama 4321, Ciudad Industrial, 37490 León...",
        horario = "08:00 - 20:00",
        latLng = LatLng(21.0991, -101.5997)
    ),
    Agencia(
        id = 4,
        nombre = "Agencia Silao",
        direccion = "C. 5 de Mayo 13-15, Centro, 36100 Silao de la Victoria, Gto.",
        horario = "24 horas",
        latLng = LatLng(20.9427, -101.4255)
    ),
    Agencia(
        id = 5,
        nombre = "Oficina San Felipe",
        direccion = "San Felipe - Ocampo Sn, 37600 San Felipe, Gto.",
        horario = "09:00 - 17:00",
        latLng = LatLng(21.4883, -101.2185)
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgenciasScreen(
    onNavigateBack: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary
    val surfaceColor = colorScheme.surface
    val onSurfaceColor = colorScheme.onSurface
    val backgroundColor = colorScheme.background
    val outlineColor = colorScheme.outline
    val surfaceVariantColor = colorScheme.surfaceVariant // Para fondo sutil
    val onSurfaceVariantColor = colorScheme.onSurfaceVariant // Para texto sutil

    val defaultLocation = LatLng(21.0188, -101.2587)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 8f)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Agencias cercanas", fontWeight = FontWeight.Bold, color = onSurfaceColor)
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = onSurfaceColor)
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
                .padding(horizontal = 16.dp)
                .background(backgroundColor)
        ) {
            // --- Mapa Interactivo de Google Maps ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(surfaceVariantColor)
                    .border(1.dp, outlineColor, RoundedCornerShape(8.dp))
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    uiSettings = MapUiSettings(
                        zoomControlsEnabled = true,
                        mapToolbarEnabled = false
                    )
                ) {
                    agenciesData.forEach { agencia ->
                        Marker(
                            state = MarkerState(position = agencia.latLng),
                            title = agencia.nombre,
                            snippet = agencia.direccion,
                            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Lista de agencias",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                // CORRECCIÓN 8: Usar onSurfaceColor
                color = onSurfaceColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // --- Lista de Agencias ---
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(agenciesData) { agencia ->
                    AgencyItem(
                        agencia = agencia,
                        onSurfaceColor = onSurfaceColor,
                        surfaceColor = surfaceColor,
                        outlineColor = outlineColor,
                        primaryColor = primaryColor, // Mapeo de AgencyIconBlue
                        onSurfaceVariantColor = onSurfaceVariantColor
                    )
                }
            }
        }
    }
}

@Composable
fun AgencyItem(
    agencia: Agencia,
    onSurfaceColor: Color,
    surfaceColor: Color,
    outlineColor: Color,
    primaryColor: Color,
    onSurfaceVariantColor: Color
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, outlineColor),
        colors = CardDefaults.cardColors(containerColor = surfaceColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Círculo (Icono)
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(primaryColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Datos de la agencia
            Column {
                Text(
                    text = agencia.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = onSurfaceColor
                )
                Spacer(modifier = Modifier.height(4.dp))
                val shortAddress = agencia.direccion.split(",").take(2).joinToString(", ")
                Text(
                    text = shortAddress,
                    fontSize = 14.sp,
                    color = onSurfaceVariantColor
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Horario: ${agencia.horario}",
                    fontSize = 12.sp,
                    color = onSurfaceVariantColor
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AgenciasScreenPreview() {
    MaterialTheme {
        AgenciasScreen(onNavigateBack = {})
    }
}
