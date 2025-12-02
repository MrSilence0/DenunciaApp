package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource // Importar painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.edu.utng.oic.denunciaapp.R // Importar la clase R para recursos
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.MenuViewModel
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.MenuViewModelFactory
private const val ROUTE_PROFILE = "profile_screen"
private const val ROUTE_EMERGENCY = "emergency_contact_screen"
private const val ROUTE_TERMS = "terms_and_conditions_screen"
private const val ROUTE_LOGOUT = "logout_action"


// Modelo de datos auxiliar
data class DrawerItem(val icon: ImageVector, val label: String, val route: String)

/**
 * COMPONENTE PRINCIPAL DE PANTALLA: MenuScreen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToEmergency: () -> Unit,
    onNavigateToTerms: () -> Unit,
    onLogOut: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val userService = remember { UserService() }
    val factory = remember { MenuViewModelFactory(userService) }
    val viewModel: MenuViewModel = viewModel(factory = factory)

    val userName by viewModel.userName.collectAsState()

    val surfaceColor = MaterialTheme.colorScheme.surface
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Menú Principal", fontWeight = FontWeight.Bold, color = onSurfaceColor)
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = onSurfaceColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor,
                    titleContentColor = onSurfaceColor,
                    navigationIconContentColor = onSurfaceColor
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        MenuLateral(
            modifier = Modifier.padding(paddingValues),
            userName = userName,
            currentRoute = null,
            onNavigate = { route ->
                when (route) {
                    ROUTE_PROFILE -> onNavigateToProfile()
                    ROUTE_EMERGENCY -> onNavigateToEmergency()
                    ROUTE_TERMS -> onNavigateToTerms()
                    ROUTE_LOGOUT -> onLogOut()
                }
            }
        )
    }
}

// --- COMPONENTE DEL MENÚ LATERAL (Corregido) ---
@Composable
fun MenuLateral(
    modifier: Modifier = Modifier,
    userName: String,
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    // --- Colores Dinámicos del Tema ---
    val primaryColor = MaterialTheme.colorScheme.primary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val background = MaterialTheme.colorScheme.background
    val dividerColor = MaterialTheme.colorScheme.outline
    val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant


    // Definición de las opciones de navegación
    val items = listOf(
        DrawerItem(Icons.Default.Person, "Mi Perfil", ROUTE_PROFILE),
        DrawerItem(Icons.Default.Group, "Contactos de emergencia", ROUTE_EMERGENCY),
        DrawerItem(Icons.Default.Description, "Términos y Condiciones", ROUTE_TERMS),
        DrawerItem(Icons.Default.ExitToApp, "Cerrar Sesión", ROUTE_LOGOUT)
    )

    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(background)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.Start
        ) {

            // Icono de la App
            Image(
                painter = painterResource(id = R.drawable.denunciaappicon),
                contentDescription = "Logo DenunciaApp",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre de la App
            Text(
                text = "DenunciaApp",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = primaryColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre del Usuario
            Text(
                text = userName,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = onSurfaceColor
            )
            Spacer(modifier = Modifier.height(4.dp))

            // Mensaje de Bienvenida
            Text(
                text = "¡Bienvenido/a!",
                fontSize = 14.sp,
                color = onSurfaceVariantColor
            )
        }

        Divider(color = dividerColor.copy(alpha = 0.5f), thickness = 1.dp)
        Spacer(Modifier.height(8.dp))

        items.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationDrawerItem(
                label = { Text(item.label, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = null,
                        tint = if (isSelected) primaryColor else onSurfaceColor
                    )
                },
                selected = isSelected,
                onClick = {
                    onNavigate(item.route)
                },
                modifier = Modifier.padding(vertical = 4.dp),
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = primaryColor.copy(alpha = 0.1f),
                    selectedIconColor = primaryColor,
                    selectedTextColor = primaryColor,
                    unselectedTextColor = onSurfaceColor // Texto de ítem no seleccionado
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MenuScreenPreview() {
    MaterialTheme {
        MenuLateral(
            userName = "Carlos Hernández",
            currentRoute = ROUTE_PROFILE,
            onNavigate = {}
        )
    }
}