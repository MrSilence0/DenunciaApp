package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.utng.oic.denunciaapp.data.model.User
import mx.edu.utng.oic.denunciaapp.data.service.UserService

/**
 * ViewModel para la pantalla de Login, maneja la interacción con UserService
 * para la autenticación de usuarios registrados en Firestore.
 */
class LoginViewModel(
    private val userService: UserService = UserService()
) : ViewModel() {

    // Estado para manejar la carga (ej. spinner)
    var isLoading by mutableStateOf(false)
        private set

    // Estado para manejar mensajes de error (ej. credenciales inválidas o campos vacíos)
    var errorMessage by mutableStateOf<String?>(null)

    // Estado para almacenar el usuario autenticado
    var authenticatedUser by mutableStateOf<User?>(null)
        private set

    /**
     * Intenta iniciar sesión con el correo y la contraseña, consultando los datos en Firestore.
     */
    fun login(email: String, password: String) {
        // Limpiar estados anteriores
        errorMessage = null
        authenticatedUser = null
        isLoading = true

        viewModelScope.launch {
            try {
                // 1. Validación de campos vacíos
                if (email.isBlank() || password.isBlank()) {
                    throw Exception("Por favor, ingresa correo y contraseña.")
                }

                // Llamada al servicio que busca el usuario en Firestore y verifica la contraseña
                val user = userService.loginUser(email, password)

                // 2. Comprobar resultado del servicio
                if (user != null) {
                    authenticatedUser = user
                } else {
                    // Si el servicio no lanza una excepción, esta es una comprobación de seguridad.
                    throw Exception("Credenciales inválidas. Verifica tu correo y contraseña.")
                }

            } catch (e: Exception) {
                // Fracaso: capturar el mensaje de error del UserService (ej. Usuario no encontrado, Contraseña incorrecta)
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    /**
     * Llama al servicio para registrar un inicio de sesión anónimo.
     */
    fun loginAnonymously() {
        // En este caso, solo simularíamos el éxito de la función del servicio
        viewModelScope.launch {
            try {
                // Llamar al servicio. Usando signInAnonymously como en tu código.
                userService.signInAnonymously()
                // Al ser anónimo, no establecemos un 'authenticatedUser' específico con datos.
            } catch (e: Exception) {
                // Manejar errores si los hubiera
                errorMessage = "Error al iniciar sesión anónimamente: ${e.message}"
            }
        }
    }

    /**
     * Función para limpiar el mensaje de error una vez que se ha mostrado.
     */
    fun clearError() {
        errorMessage = null
    }
}