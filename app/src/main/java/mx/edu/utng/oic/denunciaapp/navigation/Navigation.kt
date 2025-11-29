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

    val showBottomBar = currentRoute in listOf(
        AppScreen.HomePage.route,
        AppScreen.Denuncias.route,
        AppScreen.ForosPage.route,
        AppScreen.Agencias.route,
        AppScreen.Messages.route,
        AppScreen.Sos.route
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(
                    onNavigateTo = { route ->
                        if (route == AppScreen.Sos.route) {
                            navController.navigate(route)
                        } else {
                            navController.navigate(route) {
                                // Configuración para evitar duplicar pantallas en la pila
                                popUpTo(AppScreen.Denuncias.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            startDestination = AppScreen.Login.route
        )
    }
}