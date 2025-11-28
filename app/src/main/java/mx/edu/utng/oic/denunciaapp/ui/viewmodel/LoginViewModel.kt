package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.utng.oic.denunciaapp.data.model.User
import mx.edu.utng.oic.denunciaapp.data.service.UserService

class LoginViewModel(
    private val userService: UserService = UserService()
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)

    var authenticatedUser by mutableStateOf<User?>(null)
        private set

    var currentUserId by mutableStateOf<String?>(null)
        private set


    fun login(email: String, password: String) {
        errorMessage = null
        isLoading = true

        viewModelScope.launch {
            try {
                if (email.isBlank() || password.isBlank()) {
                    throw Exception("Por favor, ingresa correo y contraseña.")
                }

                // 🔥 loginUser DEBE devolver un User
                val user: User = userService.loginUser(email, password)
                    ?: throw Exception("Credenciales inválidas.")

                authenticatedUser = user
                currentUserId = user.idUser

            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }


    fun loginAnonymously() {
        errorMessage = null
        isLoading = true

        viewModelScope.launch {
            try {
                // 🔥 signInAnonymously DEBE regresar un userId: String
                val uid: String = userService.signInAnonymously()

                currentUserId = uid
                authenticatedUser = null

            } catch (e: Exception) {
                errorMessage = "Error al iniciar sesión anónimamente: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun clearError() {
        errorMessage = null
    }
}
