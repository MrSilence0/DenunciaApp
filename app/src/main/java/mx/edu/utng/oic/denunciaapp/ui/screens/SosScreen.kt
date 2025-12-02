package mx.edu.utng.oic.denunciaapp.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext // <-- IMPORTANTE
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.edu.utng.oic.denunciaapp.R

// --- Modelo de Datos ---
data class EmergencyContact(
    val id: Int,
    val name: String,
    val number: String,
    val icon: ImageVector,
    val color: Color
)

// --- Implementación de la Lógica de Llamada (Recomendada) ---
private fun callNumberActionDial(context: android.content.Context, number: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$number")
            // Usamos FLAG_ACTIVITY_NEW_TASK si se llama desde el contexto de la aplicación.
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        // En un entorno de producción, podrías mostrar un Toast o Snackbar aquí
        // indicando que no se pudo iniciar la aplicación de teléfono.
        println("Error al intentar abrir el dialer: ${e.message}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SosScreen(
    onOpenMenu: () -> Unit
    // Eliminamos onCallNumber ya que se maneja internamente
) {
    val emergencyNumbers = listOf(
        EmergencyContact(1, "Emergencias (General)", "911", Icons.Default.Call, Color(0xFFD32F2F)),
        EmergencyContact(2, "Policía Federal", "088", Icons.Default.Shield, Color(0xFF1976D2)),
        EmergencyContact(3, "Cruz Roja", "065", Icons.Default.Favorite, Color(0xFFF44336)),
        EmergencyContact(4, "Bomberos", "068", Icons.Default.LocalFireDepartment, Color(0xFFFF9800)),
        EmergencyContact(5, "CENAPRED (Desastres)", "018000041300", Icons.Default.Warning, Color(0xFF388E3C)),
        EmergencyContact(6, "Denuncia Anónima", "089", Icons.Default.Security, Color(0xFF6A1B9A)),
    )

    // Obtener el contexto de Android
    val context = LocalContext.current

    // Función lambda para pasar a EmergencyItem
    val onCall: (String) -> Unit = { number ->
        callNumberActionDial(context, number)
    }

    // --- Colores Dinámicos del Tema ---
    val primaryColor = MaterialTheme.colorScheme.primary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant

    Scaffold(
        topBar = {
            // --- TopBar con Logo y Botón de Menú ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                IconButton(
                    onClick = onOpenMenu,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Abrir menú",
                        modifier = Modifier.size(32.dp),
                        tint = onSurfaceColor
                    )
                }

                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.denunciaappicon),
                        contentDescription = "Logo DenunciaApp",
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        "DenunciaApp",
                        fontSize = 10.sp,
                        color = onSurfaceVariantColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = "Números de Emergencia en México",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = primaryColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Toca el botón de llamada para comunicarte inmediatamente.",
                fontSize = 14.sp,
                color = onSurfaceVariantColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // --- Lista de Contactos de Emergencia ---
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(emergencyNumbers) { contact ->
                    EmergencyItem(
                        contact = contact,
                        onCall = onCall // Usamos la lambda interna
                    )
                }
            }
        }
    }
}

// --- Componente Auxiliar para cada contacto ---
@Composable
fun EmergencyItem(
    contact: EmergencyContact,
    onCall: (String) -> Unit
) {
    // --- Colores Dinámicos del Tema ---
    val primaryColor = MaterialTheme.colorScheme.primary
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary // Para asegurar contraste en el FAB
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val surfaceColor = MaterialTheme.colorScheme.surface

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = surfaceColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCall(contact.number) } // La tarjeta completa es clickable
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(contact.color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = contact.icon,
                    contentDescription = contact.name,
                    tint = contact.color,
                    modifier = Modifier.size(32.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = contact.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = onSurfaceColor
                )
                Text(
                    text = contact.number,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = primaryColor
                )
            }

            // 3. Botón de Llamada Rápida
            IconButton(
                onClick = { onCall(contact.number) },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(primaryColor)
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Llamar a ${contact.number}",
                    tint = onPrimaryColor // Usamos onPrimaryColor para garantizar contraste
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SosScreenPreview() {
    MaterialTheme {
        SosScreen(
            onOpenMenu = {}
        )
    }
}

