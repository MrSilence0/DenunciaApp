package mx.edu.utng.oic.denunciaapp.ui.screens

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SosScreen(
    onOpenMenu: () -> Unit,
    onCallNumber: (String) -> Unit
) {
    val emergencyNumbers = listOf(
        EmergencyContact(1, "Emergencias (General)", "911", Icons.Default.Call, Color(0xFFD32F2F)),
        EmergencyContact(2, "Policía Federal", "088", Icons.Default.Shield, Color(0xFF1976D2)),
        EmergencyContact(3, "Cruz Roja", "065", Icons.Default.Favorite, Color(0xFFF44336)),
        EmergencyContact(4, "Bomberos", "068", Icons.Default.LocalFireDepartment, Color(0xFFFF9800)),
        EmergencyContact(5, "CENAPRED (Desastres)", "018000041300", Icons.Default.Warning, Color(0xFF388E3C)),
        EmergencyContact(6, "Denuncia Anónima", "089", Icons.Default.Security, Color(0xFF6A1B9A)),
    )

    Scaffold(
        topBar = {
            // --- TopBar con Logo y Botón de Menú ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 1. Botón Abrir Menú (Izquierda)
                IconButton(
                    onClick = onOpenMenu,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Abrir menú",
                        modifier = Modifier.size(32.dp),
                        tint = Color.Black
                    )
                }

                // 2. Logo de la App (Centro)
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.denunciaappicon),
                        contentDescription = "Logo DenunciaApp",
                        modifier = Modifier.size(40.dp)
                    )
                    Text("DenunciaApp", fontSize = 10.sp, color = WireframeGray, fontWeight = FontWeight.Bold)
                }
            }
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Números de Emergencia en México",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = PrimaryColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Toca el botón de llamada para comunicarte inmediatamente.",
                fontSize = 14.sp,
                color = WireframeGray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // --- Lista de Contactos de Emergencia ---
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(emergencyNumbers) { contact ->
                    EmergencyItem(
                        contact = contact,
                        onCall = onCallNumber
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
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCall(contact.number) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 1. Imagen/Icono (Estética)
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

            // 2. Información del Contacto
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = contact.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = contact.number,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = PrimaryColor
                )
            }

            // 3. Botón de Llamada Rápida
            IconButton(
                onClick = { onCall(contact.number) },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(PrimaryColor)
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Llamar a ${contact.number}",
                    tint = Color.White
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SosScreenPreview() {
    SosScreen(
        onOpenMenu = {},
        onCallNumber = {}
    )
}
