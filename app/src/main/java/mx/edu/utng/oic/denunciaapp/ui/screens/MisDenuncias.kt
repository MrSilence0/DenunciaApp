package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.automirrored.filled.ArrowBack // Importación corregida para flecha izquierda

// --- 1. MODELO DE DATOS PARA LA DENUNCIA (Sin cambios) ---
data class Denuncia(
    val id: String,
    val tipo: String, // Ejemplo: Violencia de Género, Robo, Acoso
    val estatus: String, // Ejemplo: En Revisión, Asignada, Resuelta, Rechazada
    val fecha: String,
    val hora: String,
    val detalle: String = "Detalle breve de la denuncia."
)

// --- 2. COMPONENTE INDIVIDUAL DE ITEM DE DENUNCIA (Sin cambios) ---
@Composable
fun DenunciaItem(denuncia: Denuncia, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onClick(denuncia.id) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Fila principal: Tipo de Denuncia y Estatus
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tipo de Denuncia
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Tipo de Denuncia",
                        tint = PrimaryColor,
                        modifier = Modifier.size(24.dp).padding(end = 4.dp)
                    )
                    Text(
                        text = denuncia.tipo,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                // Estatus (etiqueta con color)
                val statusColor = when (denuncia.estatus) {
                    "Resuelta" -> Color(0xFF4CAF50) // Verde
                    "En Revisión" -> Color(0xFFFF9800) // Naranja
                    "Rechazada" -> Color(0xFFF44336) // Rojo
                    else -> WireframeGray // Gris
                }
                Text(
                    text = denuncia.estatus,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = statusColor,
                    modifier = Modifier
                        .background(statusColor.copy(alpha = 0.15f), shape = MaterialTheme.shapes.small)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = WireframeGray.copy(alpha = 0.2f))
            Spacer(modifier = Modifier.height(8.dp))

            // Fila de detalles: ID, Fecha y Hora
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // ID de la Denuncia
                Column {
                    Text("ID:", fontSize = 10.sp, color = WireframeGray)
                    Text(denuncia.id, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.Black)
                }

                // Fecha
                Column(horizontalAlignment = Alignment.End) {
                    Text("Fecha:", fontSize = 10.sp, color = WireframeGray)
                    Text(denuncia.fecha, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.Black)
                }

                // Hora
                Column(horizontalAlignment = Alignment.End) {
                    Text("Hora:", fontSize = 10.sp, color = WireframeGray)
                    Text(denuncia.hora, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.Black)
                }
            }
        }
    }
}


// --- 3. PANTALLA COMPLETA MIS DENUNCIAS (Corregida) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisDenunciasScreen(
    onNavigateBack: () -> Unit, // Callback para regresar a Denuncias
    onOpenDrawer: () -> Unit, // Callback para abrir el Drawer/Menu (aunque ya no se usa, lo mantenemos en la firma si es necesario)
    onNavigateToDenunciaDetail: (String) -> Unit
) {
    // Datos de ejemplo para la lista
    val mockDenuncias = listOf(
        Denuncia(
            id = "2023-A001",
            tipo = "Violencia de Género",
            estatus = "En Revisión",
            fecha = "24/11/2025",
            hora = "14:30"
        ),
        Denuncia(
            id = "2023-B002",
            tipo = "Robo a Casa",
            estatus = "Resuelta",
            fecha = "22/11/2025",
            hora = "10:15"
        ),
        Denuncia(
            id = "2023-C003",
            tipo = "Acoso Cibernético",
            estatus = "Rechazada",
            fecha = "20/11/2025",
            hora = "08:00"
        ),
        Denuncia(
            id = "2023-D004",
            tipo = "Vandalismo",
            estatus = "Asignada",
            fecha = "18/11/2025",
            hora = "17:45"
        ),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Denuncias", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    // CORRECCIÓN: Botón para regresar a Denuncias.kt
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            // Usamos Icons.AutoMirrored.Filled.ArrowBack para la flecha de regreso
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar a Denuncias"
                        )
                    }
                },
                // Nota: Los botones de acción (como el de menú o drawer) irían en el bloque actions,
                // pero si solo queremos volver, solo necesitamos el navigationIcon.
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color(0xFFF5F5F5) // Fondo ligeramente gris
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(mockDenuncias) { denuncia ->
                DenunciaItem(
                    denuncia = denuncia,
                    onClick = { denunciaId ->
                        onNavigateToDenunciaDetail(denunciaId)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MisDenunciasScreenPreview() {
    MisDenunciasScreen(
        onNavigateBack = {},
        onOpenDrawer = {},
        onNavigateToDenunciaDetail = {}
    )
}