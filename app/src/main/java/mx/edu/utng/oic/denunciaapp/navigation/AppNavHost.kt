package mx.edu.utng.oic.denunciaapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

// Importar todas las pantallas necesarias (se asume que estas Composable existen)
import mx.edu.utng.oic.denunciaapp.ui.screens.LoginScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.RegisterScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.UserProfileScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.HomePageScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.DenunciasScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.AgenciasScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.CreateForumScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.DenunciaFotograficaScreen
//import mx.edu.utng.oic.denunciaapp.ui.screens.MisDenunciasScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.PersonaDesaparecidaScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.RoboVehiculoScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.RoboCasaScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.RoboObjetoScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.ExtorsionScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.DenunciaViolenciaScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.ForosPageScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.MessagesScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.PostsScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.ForgotPasswordScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.MenuScreen
import mx.edu.utng.oic.denunciaapp.ui.screens.MisDenunciasScreen


/**
 * Define el NavHost que gestiona las transiciones entre las diferentes pantallas
 * de la aplicación, utilizando las rutas definidas en AppScreen.
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = AppScreen.Login.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // --- 1. Rutas de Autenticación/Perfil ---

        composable(AppScreen.Login.route) {
            LoginScreen(
                onLoginSuccess = { user -> // Acepta el objeto User
                    // Aquí se maneja la navegación después de que el ViewModel confirma el éxito
                    navController.navigate(AppScreen.Denuncias.route) {
                        popUpTo(AppScreen.Login.route) { inclusive = true }
                    }
                },
                onRegisterClick = { navController.navigate(AppScreen.Register.route) },
                onForgotPasswordClick = { navController.navigate(AppScreen.ForgotPassword.route) }
            )
        }
        composable(AppScreen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    // Navega a Denuncias y limpia la pila
                    navController.navigate(AppScreen.Denuncias.route) {
                        popUpTo(AppScreen.Register.route) { inclusive = true }
                    }
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // [NUEVA RUTA DE AUTENTICACIÓN AÑADIDA]
        // Se asume que AppScreen tiene: data object ForgotPassword : AppScreen("forgot_password_screen")
        composable(AppScreen.ForgotPassword.route) {
            ForgotPasswordScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(AppScreen.UserProfile.route) {
            UserProfileScreen(onNavigateBack = { navController.popBackStack() })
        }


        // --- 2. Rutas de Nivel Superior (Bottom Bar / Menú Principal) ---

        composable(AppScreen.HomePage.route) {
            HomePageScreen(
                onOpenDrawer = { navController.navigate(AppScreen.Menu.route) },
                onNavigateTo = { route -> navController.navigate(route) }
            )
        }

        // Dentro de AppNavHost.kt, en el bloque NavHost

// Pantalla principal Denuncias Hub (Contiene el Grid de opciones)
        composable(AppScreen.Denuncias.route) {
            DenunciasScreen(
                // Navegación desde el TopBar/Íconos
                onNavigateToMisDenuncias = { navController.navigate(AppScreen.MisDenuncias.route) },
                // >>> NUEVO CALLBACK para abrir el Menú <<<
                onOpenMenu = { navController.navigate(AppScreen.Menu.route) },

                // Callbacks que se mantienen
                onNavigateToAgencias = { navController.navigate(AppScreen.Agencias.route) },
                onNavigateToPosts = { navController.navigate(AppScreen.Posts.route) },

                // Navegación a Formularios específicos
                onNavigateToDenunciaFotografica = { navController.navigate(AppScreen.DenunciaFotografica.route) },
                onNavigateToPersonaDesaparecida = { navController.navigate(AppScreen.PersonaDesaparecida.route) },
                onNavigateToRoboVehiculo = { navController.navigate(AppScreen.RoboVehiculo.route) },
                onNavigateToRoboCasa = { navController.navigate(AppScreen.RoboCasa.route) },
                onNavigateToRoboObjeto = { navController.navigate(AppScreen.RoboObjeto.route) },
                onNavigateToExtorsion = { navController.navigate(AppScreen.Extorsion.route) },
                onNavigateToDenunciaViolencia = { navController.navigate(AppScreen.DenunciaViolencia.route) },
            )
        }

        composable(AppScreen.ForosPage.route) {
            ForosPageScreen(
                // Nuevo callback para el FAB
                onNavigateToCreateForum = { navController.navigate(AppScreen.CreateForum.route) },
                onNavigateTo = { route -> navController.navigate(route) }
            )
        }

        composable(AppScreen.CreateForum.route) {
            CreateForumScreen(
                onNavigateBack = { navController.popBackStack() }

            )
        }

        composable(AppScreen.Messages.route) {
            MessagesScreen(onBack = { navController.popBackStack() },
                onOpenDrawer = { navController.navigate(AppScreen.Menu.route) }
            )
        }

        composable(AppScreen.Menu.route) {
            MenuScreen(
                onNavigateToProfile = { navController.navigate(AppScreen.UserProfile.route) }, // <<-- ¡El navController SÍ está disponible aquí!
                onNavigateToEmergency = { navController.navigate(AppScreen.EmergencyContacts.route) },
                onNavigateToTerms = { navController.navigate(AppScreen.TermsAndConditions.route) },
                onLogOut = { navController.navigate(AppScreen.Login.route) { popUpTo(0) } },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(AppScreen.Posts.route) {
            PostsScreen(onBack = { navController.popBackStack() },
                onNavigateToChat = { postId -> navController.navigate("${AppScreen.Messages.route}/$postId") }, // Navega a la pantalla de mensajes con el ID del post
                onNavigateToMenu = { navController.navigate(AppScreen.Menu.route) } // Navega al menú lateral
            )
        }


        // --- 3. Sub-rutas de Detalle y Formularios ---

        composable(AppScreen.MisDenuncias.route) {
            MisDenunciasScreen(
                // Callback para regresar a la pantalla anterior
                onNavigateBack = { navController.popBackStack() },
                onOpenDrawer = { /* Si no se usa el Drawer, se puede dejar vacío o llamar a una función */ },
                onNavigateToDenunciaDetail = { denunciaId -> navController.navigate("${AppScreen.DenunciaDetail.route}/$denunciaId") } // Navega a la vista de detalle
            )
        }

        // Agencias cercanas (generalmente con mapa)
        composable(AppScreen.Agencias.route) {
            AgenciasScreen(onNavigateBack = { navController.popBackStack() })
        }

// FORMULARIOS DE DENUNCIA

        composable(AppScreen.DenunciaFotografica.route) {
            // 1. Uso de onSuccess en lugar de onSave.
            // 2. onCancel regresa a la pila anterior.
            // 3. onSuccess también regresa a la pila anterior después de un guardado exitoso
            //    (la lógica de guardado está en el ViewModel).
            DenunciaFotograficaScreen(
                onCancel = { navController.popBackStack() },
                onSuccess = { navController.popBackStack() }
            )
        }

        composable(AppScreen.PersonaDesaparecida.route) {
            PersonaDesaparecidaScreen(
                onNavigateBack = { navController.popBackStack() } // Implementa el botón "Volver/Cancelar"
                // NOTA: Se eliminó el parámetro 'onSave' ya que la lógica de guardado
                // se maneja internamente dentro de PersonaDesaparecidaScreen a través del ViewModel.
            )
        }

        composable(AppScreen.RoboVehiculo.route) {
            RoboVehiculoScreen(
                // Al ViewModel le pasamos la acción de Volver/Cancelar
                onNavigateBack = { navController.popBackStack() }
                // Se elimina onSave = { ... } porque el ViewModel maneja la lógica de guardado
                // y llama a onNavigateBack cuando la operación tiene éxito.
            )
        }

        composable(AppScreen.RoboCasa.route) {
            RoboCasaScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(AppScreen.RoboObjeto.route) {
            RoboObjetoScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(AppScreen.Extorsion.route) {
            ExtorsionScreen(
                onNavigateBack = { navController.popBackStack() }

            )
        }

        composable(AppScreen.DenunciaViolencia.route) {
            DenunciaViolenciaScreen(
                onCancel = { navController.popBackStack() },

                onReportSaved = {

                    navController.popBackStack()
                }
            )
        }
    }
}

