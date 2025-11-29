package mx.edu.utng.oic.denunciaapp.ui.screens

import androidx.compose.foundation.Image // Importar Image
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

// --- DEFINICIÓN DE COLORES COMO CONSTANTES LOCALES ---
val WireframeGray = Color(0xFF9E9E9E)
val PrimaryColor = Color(0xFF0D47A1) // Azul principal para botones/iconos

// Constantes usadas internamente en MenuLateral para mapear las acciones
private const val ROUTE_PROFILE = "profile_screen"
private const val ROUTE_EMERGENCY = "emergency_contact_screen"
private const val ROUTE_TERMS = "terms_and_conditions_screen"
private const val ROUTE_LOGOUT = "logout_action"


// Modelo de datos auxiliar
data class DrawerItem(val icon: ImageVector, val label: String, val route: String)

/**
 * COMPONENTE PRINCIPAL DE PANTALLA: MenuScreen
 * Esta función es la que llama el AppNavHost.
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
    // 1. Inicializar ViewModel y Factory
    val userService = remember { UserService() }
    val factory = remember { MenuViewModelFactory(userService) }
    val viewModel: MenuViewModel = viewModel(factory = factory)

    // 2. Observar el nombre del usuario
    val userName by viewModel.userName.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú Principal", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        // Envuelve tu contenido MenuLateral, mapeando la función de navegación interna
        // a las funciones de navegación externas.
        MenuLateral(
            modifier = Modifier.padding(paddingValues),
            userName = userName, // ⬅️ Pasar el nombre del usuario
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

// --- COMPONENTE DEL MENÚ LATERAL (Adaptado del código original) ---
@Composable
fun MenuLateral(
    modifier: Modifier = Modifier,
    userName: String,
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
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
            .background(Color.White)
            .padding(16.dp)
    ) {
        // --- 1. SECCIÓN DE ENCABEZADO: LOGO, NOMBRE APP, USUARIO Y BIENVENIDA ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.Start
        ) {

            // Icono de la App (REAL)
            Image(
                painter = painterResource(id = R.drawable.denunciaappicon),
                contentDescription = "Logo DenunciaApp",
                modifier = Modifier
                    .size(64.dp) // Mantenemos el tamaño
                    .clip(CircleShape) // Aplicamos la forma circular
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre de la App
            Text(
                text = "DenunciaApp",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryColor // Color azul para el nombre de la app
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre del Usuario (CORREGIDO para usar el valor de la base de datos)
            Text(
                text = userName,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))

            // Mensaje de Bienvenida
            Text(
                text = "¡Bienvenido/a!",
                fontSize = 14.sp,
                color = WireframeGray
            )
        }

        Divider(color = WireframeGray.copy(alpha = 0.5f), thickness = 1.dp)
        Spacer(Modifier.height(8.dp))

        // --- 2. OPCIONES DE NAVEGACIÓN (Botones) ---
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationDrawerItem(
                label = { Text(item.label, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                icon = { Icon(item.icon, contentDescription = null, tint = if (isSelected) PrimaryColor else Color.Black) },
                selected = isSelected,
                onClick = {
                    onNavigate(item.route)
                },
                modifier = Modifier.padding(vertical = 4.dp),
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = PrimaryColor.copy(alpha = 0.1f),
                    selectedIconColor = PrimaryColor,
                    selectedTextColor = PrimaryColor,
                    unselectedTextColor = Color.Black
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MenuScreenPreview() {
    // Para el Preview, podemos simular el componente MenuLateral directamente con un nombre fijo
    MenuLateral(
        userName = "Carlos Hernández",
        currentRoute = ROUTE_PROFILE,
        onNavigate = {}
    )
}