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
    val colorScheme = MaterialTheme.colorScheme
    val surfaceColor = colorScheme.surface
    val errorColor = colorScheme.error
    val onErrorColor = colorScheme.onError
    val primaryColor = colorScheme.primary
    val onSurfaceVariantColor = colorScheme.onSurfaceVariant
    val onSurfaceColor = colorScheme.onSurface


    val navItems = listOf(
        BottomNavItemData(Icons.Default.Home, "Inicio", AppScreen.HomePage.route),
        BottomNavItemData(Icons.Default.Warning, "Denuncias", AppScreen.Denuncias.route),
        BottomNavItemData(Icons.Default.Groups, "Foros", AppScreen.ForosPage.route),
        BottomNavItemData(Icons.Default.Mail, "Mensajes", AppScreen.Messages.route),
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(surfaceColor)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        navItems.forEach { item ->
            BottomNavItem(
                icon = item.icon,
                label = item.label,
                // isActive: Debería calcularse usando el currentRoute
                isActive = false,
                onClick = { onNavigateTo(item.route) },
                primaryColor = primaryColor,
                onSurfaceVariantColor = onSurfaceVariantColor
            )
        }

        // Botón SOS destacado
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { onNavigateTo(AppScreen.Sos.route) }
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(errorColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Botón SOS",
                    tint = onErrorColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                "SOS",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = errorColor
            )
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
    onClick: () -> Unit,
    primaryColor: Color,
    onSurfaceVariantColor: Color
) {
    val iconColor = if (isActive) primaryColor else onSurfaceVariantColor
    val textColor = if (isActive) primaryColor else onSurfaceVariantColor

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = iconColor
        )
        Text(
            text = label,
            fontSize = 10.sp,
            color = textColor,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    MaterialTheme {
        BottomNavigationBar(onNavigateTo = {})
    }
}
