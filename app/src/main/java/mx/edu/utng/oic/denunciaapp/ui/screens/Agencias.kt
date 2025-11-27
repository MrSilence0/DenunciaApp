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

val AgencyPinColor = Color(0xFFD32F2F) // Rojo para los pines
val AgencyIconBlue = Color(0xFF0D47A1) // Azul fuerte para el icono de la lista

// Modelo de datos para las agencias
data class Agencia(
    val id: Int,
    val nombre: String,
    val direccion: String,
    val horario: String,
    val latLng: LatLng // Nueva propiedad para las coordenadas
)

// Datos de las Agencias con sus coordenadas (Aproximadas para Guanajuato)
private val agenciesData = listOf(
    Agencia(
        id = 1,
        nombre = "MP Dolores Hidalgo",
        direccion = "Av. De los Heroes 8, Viñedo, 37804 Dolores Hidalgo...",
        horario = "24 horas",
        latLng = LatLng(21.18597861019045, -100.91918814562699) // Dolores Hidalgo 21.18597861019045, -100.91918814562699
    ),
    Agencia(
        id = 2,
        nombre = "Fiscalía San Miguel de Allende",
        direccion = "Blvrd de la Conspiración, La Luz, 37746 San Miguel de Allende, Gto.",
        horario = "09:00 - 18:00",
        latLng = LatLng(20.9149, -100.7583) // San Miguel de Allende
    ),
    Agencia(
        id = 3,
        nombre = "Módulo de Atención León",
        direccion = "Blvd. Hermanos Aldama 4321, Ciudad Industrial, 37490 León...",
        horario = "08:00 - 20:00",
        latLng = LatLng(21.0991, -101.5997) // León
    ),
    Agencia(
        id = 4,
        nombre = "Agencia Silao",
        direccion = "C. 5 de Mayo 13-15, Centro, 36100 Silao de la Victoria, Gto.",
        horario = "24 horas",
        latLng = LatLng(20.9427, -101.4255) // Silao
    ),
    Agencia(
        id = 5,
        nombre = "Oficina San Felipe",
        direccion = "San Felipe - Ocampo Sn, 37600 San Felipe, Gto.",
        horario = "09:00 - 17:00",
        latLng = LatLng(21.4883, -101.2185) // San Felipe
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgenciasScreen(
    onNavigateBack: () -> Unit
) {
    val defaultLocation = LatLng(21.0188, -101.2587) // Punto central aproximado de Guanajuato
    val cameraPositionState = rememberCameraPositionState {
        // Inicializar la cámara apuntando al centro de Guanajuato con un buen zoom
        position = CameraPosition.fromLatLngZoom(defaultLocation, 8f)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agencias cercanas", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // --- Mapa Interactivo de Google Maps ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp) // Altura para el mapa
                    .clip(RoundedCornerShape(8.dp))
                    .background(WireframeGray)
                    .border(1.dp, WireframeGray, RoundedCornerShape(8.dp))
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    uiSettings = MapUiSettings(
                        zoomControlsEnabled = true, // Permite controles de zoom
                        mapToolbarEnabled = false // Deshabilita la barra de navegación de Maps
                    )
                ) {
                    // 2. Añadir un marcador por cada agencia
                    agenciesData.forEach { agencia ->
                        Marker(
                            state = MarkerState(position = agencia.latLng),
                            title = agencia.nombre,
                            snippet = agencia.direccion,
                            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED) // Pines rojos
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Lista de agencias",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // --- Lista de Agencias con los nuevos datos ---
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(agenciesData) { agencia -> // Usamos la lista de datos reales
                    AgencyItem(agencia)
                }
            }
        }
    }
}

@Composable
fun AgencyItem(agencia: Agencia) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Círculo Azul (Icono)
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(AgencyIconBlue, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Datos de la agencia
            Column {
                Text(
                    text = agencia.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                // Mostrar solo una parte de la dirección en la lista para que no sea muy larga
                val shortAddress = agencia.direccion.split(",").take(2).joinToString(", ")
                Text(
                    text = shortAddress,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Horario: ${agencia.horario}",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

// Se elimina MapPin y MapPlaceholderContent ya que usamos GoogleMap

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AgenciasScreenPreview() {
    AgenciasScreen(onNavigateBack = {})
}