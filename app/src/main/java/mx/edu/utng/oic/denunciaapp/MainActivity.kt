package mx.edu.utng.oic.denunciaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import mx.edu.utng.oic.denunciaapp.navigation.AppEntryNavigation
import mx.edu.utng.oic.denunciaapp.ui.theme.DenunciaAppTheme // Se asume un tema de aplicación

/**
 * Actividad principal de la aplicación.
 * Utiliza setContent para definir la UI de la aplicación con Jetpack Compose.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Envuelve toda la aplicación con el tema definido (DenunciaAppTheme)
            DenunciaAppTheme {
                // Una superficie contenedora que utiliza el color de fondo del tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // El punto de entrada principal para el flujo de navegación.
                    // Aquí se inicializa NavController, BottomBar y AppNavHost.
                    AppEntryNavigation()
                }
            }
        }
    }
}