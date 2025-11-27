import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.size

// --- COLORES GLOBALES ---
val WireframeGray = Color(0xFF9E9E9E)
val PrimaryColor = Color(0xFF0D47A1) // Azul principal

// --- MODELOS DE DATOS GLOBALES ---
data class Post(
    val id: Int,
    val username: String,
    val text: String,
    val rating: Int, // Valoración de 1 a 5
    val timeAgo: String
)

// --- COMPONENTES GLOBALES ---

// Mockup para el componente RatingStars (Necesario para Posts.kt y Messages.kt)
@Composable
fun RatingStars(rating: Int) {
    val StarColor = Color(0xFFFFCC00)
    Row {
        for (i in 1..5) {
            Icon(
                imageVector = if (i <= rating) Icons.Filled.Star else Icons.Filled.StarBorder,
                contentDescription = if (i <= rating) "Estrella rellena" else "Estrella vacía",
                tint = StarColor,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}