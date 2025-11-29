package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import mx.edu.utng.oic.denunciaapp.navigation.AppScreen


/**
 * Componente principal de la barra de navegación inferior, utilizado por el AppEntryNavigation.
 *
 * @param onNavigateTo Callback que se llama cuando se selecciona un ítem de navegación.
 * Recibe el string de la ruta a la que debe navegar.
 */
@Composable
fun BottomNavigationBar(onNavigateTo: (String) -> Unit) {
    // Lista de ítems para la navegación principal, mapeando a las rutas de AppScreen
    val navItems = listOf(
        BottomNavItemData(Icons.Default.Home, "Inicio", AppScreen.HomePage.route),
        BottomNavItemData(Icons.Default.Warning, "Denuncias", AppScreen.Denuncias.route),
        BottomNavItemData(Icons.Default.Groups, "Foros", AppScreen.ForosPage.route),
        // Nota: Si Messages.route requiere un argumento (forumId), esta navegación directa
        // desde la barra inferior puede fallar, pero se mantiene por estructura.
        BottomNavItemData(Icons.Default.Mail, "Mensajes", AppScreen.Messages.route),
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BottomBarColor)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        // Ítems de Navegación (Inicio, Denuncias, Foros, Mensajes)
        navItems.forEach { item ->
            BottomNavItem(
                icon = item.icon,
                label = item.label,
                // isActive: Debería calcularse usando el currentRoute
                isActive = false,
                onClick = { onNavigateTo(item.route) }
            )
        }

        // Botón SOS destacado
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            // CORRECCIÓN: Usar la ruta Sos declarada
            modifier = Modifier.clickable { onNavigateTo(AppScreen.Sos.route) }
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(SOSBlue, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Botón SOS",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text("SOS", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SOSBlue)
        }
    }
}

data class BottomNavItemData(
    val icon: ImageVector,
    val label: String,
    val route: String
)

/**
 * Componente individual para los ítems de navegación.
 */
@Composable
fun BottomNavItem(
    icon: ImageVector,
    label: String,
    isActive: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = if (isActive) Color.Black else Color.DarkGray
        )
        Text(
            text = label,
            fontSize = 10.sp,
            color = if (isActive) Color.Black else Color.DarkGray,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(onNavigateTo = {})
}