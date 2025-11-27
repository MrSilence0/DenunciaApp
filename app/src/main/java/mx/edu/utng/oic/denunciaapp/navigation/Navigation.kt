package mx.edu.utng.oic.denunciaapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mx.edu.utng.oic.denunciaapp.ui.screens.BottomNavigationBar

@Composable
fun AppEntryNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Define qué rutas deben mostrar la barra de navegación inferior
    val showBottomBar = currentRoute in listOf(
        AppScreen.HomePage.route,
        AppScreen.Denuncias.route,
        AppScreen.ForosPage.route,
        AppScreen.MessagesPage.route,
        AppScreen.Agencias.route,
        AppScreen.Messages.route
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(
                    onNavigateTo = { route ->
                        navController.navigate(route) {
                            // Configuración para evitar duplicar pantallas en la pila
                            popUpTo(AppScreen.Denuncias.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        // Contenedor principal de la navegación
        // AppNavHost debe contener la lógica para mapear rutas a Composable Screens
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            startDestination = AppScreen.Login.route
        )
    }
}
