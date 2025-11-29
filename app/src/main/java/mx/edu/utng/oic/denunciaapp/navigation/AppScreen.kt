package mx.edu.utng.oic.denunciaapp.navigation

/**
 * Define las rutas de navegación de la aplicación como objetos.
 * La 'route' se utiliza para navegar entre Composable.
 */
sealed class AppScreen(val route: String) {
    data object Login : AppScreen("login")
    data object Register : AppScreen("register")
    data object UserProfile : AppScreen("user_profile") // Opción del Menú Lateral

    data object HomePage : AppScreen("home")
    data object Denuncias : AppScreen("denuncias_hub")

    // ...
    data object ForosPage : AppScreen("foros_page_screen") // RUTA CORRECTA
    data object Messages : AppScreen("messages_page_screen/{forumId}")

    data object Agencias : AppScreen("agencias_screen") //
    data object DenunciaFotografica : AppScreen("denuncia_fotografica_screen") //
    data object MisDenuncias : AppScreen("mis_denuncias") // Botón en TopBar de DenunciasScreen

    data object PersonaDesaparecida : AppScreen("persona_desaparecida_screen")
    data object RoboVehiculo : AppScreen("robo_vehiculo_screen")
    data object RoboCasa : AppScreen("robo_casa_screen")
    data object RoboObjeto : AppScreen("robo_objeto_screen")
    data object Extorsion : AppScreen("extorsion_screen")
    data object DenunciaViolencia : AppScreen("denuncia_violencia_screen")

    data object Menu : AppScreen("Menu_screen")

    data object Posts : AppScreen("posts_screen")
    data object ForgotPassword : AppScreen("forgot_password_screen")

    data object EmergencyContacts : AppScreen("emergency_contacts_screen")
    data object TermsAndConditions : AppScreen("terms_and_conditions_screen")
    data object DenunciaDetail : AppScreen("denuncia_detail_screen/{denunciaId}") // Nueva ruta para detalle de denuncia

    data object CreateForum : AppScreen("create_forum") // Nueva ruta para crear foro}


}

