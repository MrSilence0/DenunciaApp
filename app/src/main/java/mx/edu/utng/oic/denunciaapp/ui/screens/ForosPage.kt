package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.edu.utng.oic.denunciaapp.navigation.AppScreen // Importación corregida a .ui.navigation

// --- Colores y Constantes ---
val FABColor = Color(0xFFFFC107) // Amarillo para el FAB
val CardBackground = Color(0xFFF0F0F0) // Fondo de tarjeta

// Modelo de datos para simular un foro
data class Foro(
    val id: Int,
    val titulo: String,
    val participantes: Int,
    val ultimaActividad: String,
    val responseCount: Int, // Añadido para el contador de respuestas
    val url: String = "https://example.com/foro/$id"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForosPageScreen(
    // Parámetro de navegación específico para crear foro
    onNavigateToCreateForum: () -> Unit,
    // Parámetro general para navegar a los detalles del foro (Messages.kt)
    onNavigateTo: (String) -> Unit
) {
    // Datos de ejemplo para la lista de foros
    val forosList = listOf(
        Foro(1, "Soporte legal para violencia doméstica", 125, "Hace 2 horas", 5),
        Foro(2, "Cómo tramitar un acta de denuncia", 42, "Ayer", 18),
        Foro(3, "Me robaron el coche, ¿qué hago?", 88, "23/11/2025", 73),
        Foro(4, "Ayuda psicológica y líneas de apoyo", 301, "Hace 10 minutos", 301),
        Foro(5, "Temas de seguridad pública en mi colonia", 67, "1 semana", 1),
        Foro(6, "Experiencias con el 911", 15, "01/10/2025", 0),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Foros", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                actions = {
                    IconButton(onClick = { /* Lógica de búsqueda */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar foro"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                // USA el callback para navegar a CreateForumScreen
                onClick = onNavigateToCreateForum,
                containerColor = FABColor,
                shape = CircleShape,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Nuevo tema",
                    tint = Color.Black
                )
            }
        },
        containerColor = Color.White
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(forosList) { foro ->
                // Pasa la función de navegación al ForumItem
                ForumItem(
                    foro = foro,
                    onClick = {
                        // Navega a la ruta de mensajes/detalle (Messages.kt)
                        onNavigateTo("foro_detalle/${foro.id}")
                    }
                )
            }
        }
    }
}

// --- Componente de Item de Foro Individual ---
@Composable
fun ForumItem(foro: Foro, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
        // NOTA: Se quita el clickable del modifier principal para que el botón lo maneje
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            // --- 1. Contenido principal y meta data ---
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Icono de Foro (Izquierda)
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.Gray.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Forum,
                        contentDescription = "Icono de foro",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Título y Participantes
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = foro.titulo,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    // Participantes
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Group,
                            contentDescription = "Participantes",
                            tint = WireframeGray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${foro.participantes} participantes",
                            fontSize = 12.sp,
                            color = WireframeGray
                        )
                    }
                }

                // Fecha de última actividad (Derecha)
                Text(
                    text = foro.ultimaActividad,
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color.LightGray)
            Spacer(modifier = Modifier.height(12.dp))

            // --- 2. Pie de la tarjeta: Respuestas y Botón Responder ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Contador de Respuestas
                Text(
                    text = "${foro.responseCount} respuestas",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = PrimaryBlue
                )

                // Botón Responder (Navega a Messages.kt)
                OutlinedButton(
                    onClick = onClick, // Usa el callback para navegar al detalle
                    shape = RoundedCornerShape(8.dp),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = SolidColor(PrimaryBlue)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Responder",
                        modifier = Modifier.size(18.dp).padding(end = 4.dp),
                        tint = PrimaryBlue
                    )
                    Text(
                        "Responder",
                        color = PrimaryBlue,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ForosPageScreenPreview() {
    ForosPageScreen(
        onNavigateToCreateForum = {},
        onNavigateTo = {}
    )
}