package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- DEFINICIONES REQUERIDAS ---
val PrimaryBlue = Color(0xFF0D47A1) // Azul principal
val DividerColor = Color(0xFFE0E0E0) // Color para divisores

// Modelo de datos para simular un Post (igual al definido en MessagesScreen.kt)
data class Post(
    val id: Int,
    val username: String,
    val text: String,
    val rating: Int,
    val timeAgo: String,
    val replies: Int = 0,
    val forumName: String = "Soporte legal"
)

// Componente de Estrellas de Valoración (Reutilizado)
@Composable
fun RatingStars(rating: Int) {
    Row {
        repeat(5) { index ->
            Icon(
                imageVector = if (index < rating) Icons.Filled.Star else Icons.Filled.StarBorder,
                contentDescription = "Rating star",
                tint = Color(0xFFFFC107), // Amarillo para las estrellas
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
// --- FIN DE DEFINICIONES REQUERIDAS ---


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(
    onBack: () -> Unit,
    onNavigateToChat: (Int) -> Unit, // Navegar al chat del post por ID
    onNavigateToMenu: () -> Unit // Abrir el menú lateral
) {
    // Datos de ejemplo para el feed de posts
    val samplePosts = listOf(
        Post(1, "Ana_Segura", "Excelente respuesta del 911 en mi caso de robo a vehículo. La policía llegó en menos de 10 minutos.", 5, "Hace 2 horas", 3),
        Post(2, "Luis_Cano", "¿Alguien sabe cómo reportar anonimamente una pelea vecinal en la CDMX?", 3, "Ayer", 12),
        Post(3, "ModeradorApp", "Recordatorio: Usen el foro de 'Ayuda psicológica' para temas delicados.", 4, "1 semana", 0),
        Post(4, "RobertoX", "Traté de hacer una denuncia en línea y la página se cayó. ¿Alguna alternativa?", 1, "Hace 3 días", 5),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Feed de Denuncias y Consejos", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToMenu) {
                        Icon(Icons.Default.Menu, contentDescription = "Abrir menú lateral")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        containerColor = Color(0xFFF5F5F5) // Fondo ligeramente gris
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(samplePosts) { post ->
                PostItem(post = post, onChatClick = { onNavigateToChat(post.id) })
            }
        }
    }
}

// --- Componente de Tarjeta de Post Individual ---
@Composable
fun PostItem(post: Post, onChatClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // 1. HEADER: Usuario y Rating
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Foto de Perfil (Placeholder)
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(PrimaryBlue.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = post.username.first().toString(),
                        color = PrimaryBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Nombre y Foro
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = post.username,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Foro: ${post.forumName}",
                        fontSize = 12.sp,
                        color = WireframeGray
                    )
                }

                // Estrellas de Valoración
                RatingStars(rating = post.rating)
            }

            // 2. CONTENIDO DEL POST
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = post.text,
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.fillMaxWidth()
            )

            // 3. FOOTER: Hora y Botón de Responder
            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = DividerColor, thickness = 0.5.dp)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = post.timeAgo,
                    fontSize = 12.sp,
                    color = WireframeGray
                )
                Spacer(modifier = Modifier.weight(1f))

                // Botón de Responder/Respuestas
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(onClick = onChatClick)
                        .background(PrimaryBlue.copy(alpha = 0.1f))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Comment,
                        contentDescription = "Respuestas",
                        tint = PrimaryBlue,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${post.replies} Respuestas",
                        color = PrimaryBlue,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PostsScreenPreview() {
    PostsScreen(
        onBack = {},
        onNavigateToChat = {},
        onNavigateToMenu = {}
    )
}