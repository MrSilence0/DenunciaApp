package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.utng.oic.denunciaapp.data.model.User
import mx.edu.utng.oic.denunciaapp.data.model.UserRole
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import java.util.*

/**
 * ViewModel para gestionar la vista de perfil del usuario.
 * Carga el perfil actual, gestiona su edición y maneja la persistencia.
 */
class UserProfileViewModel(
    private val userService: UserService
) : ViewModel() {

    // 1. Estado para la lógica de la UI
    var isLoading by mutableStateOf(true)
        private set

    var feedbackMessage by mutableStateOf<String?>(null)
        private set

    var isSaving by mutableStateOf(false)
        private set

    // 2. Estado observable para los datos del usuario
    // Inicializa con un objeto User vacío para evitar nullables en la UI.
    var userState by mutableStateOf(User())
        private set

    init {
        loadUserProfile()
    }

    /**
     * Carga el perfil del usuario activo (autenticado).
     */
    fun loadUserProfile() {
        viewModelScope.launch {
            isLoading = true
            feedbackMessage = null

            try {
                val uid = userService.getOrCreateUserId()
                if (uid.isEmpty()) {
                    feedbackMessage = "Error: No se pudo identificar al usuario."
                    return@launch
                }

                val loadedUser = userService.getUserById(uid)
                if (loadedUser != null) {
                    userState = loadedUser
                } else {
                    // Esto puede ocurrir si es un usuario anónimo que nunca ha completado su perfil
                    // Creamos un placeholder con el UID, o usamos el estado inicial vacío.
                    userState = User(idUser = uid, rol = UserRole.USUARIO_ANONIMO, isAnonymus = true)
                    feedbackMessage = "Cargando perfil genérico."
                }

            } catch (e: Exception) {
                Log.e("UserProfileVM", "Error cargando perfil", e)
                feedbackMessage = "Error al cargar el perfil: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }

    /**
     * Guarda los cambios del perfil del usuario en Firestore.
     */
    fun saveUserProfile() {
        viewModelScope.launch {
            isSaving = true
            feedbackMessage = null

            try {
                // Verificar campos mínimos si es necesario (ej. nombre)

                // El campo contrasenia no se guarda aquí, solo la usamos para Auth/Registro.
                userService.updateUser(userState.copy(contrasenia = ""))
                feedbackMessage = "Perfil actualizado exitosamente."

            } catch (e: Exception) {
                Log.e("UserProfileVM", "Error guardando perfil", e)
                feedbackMessage = "Error al guardar el perfil: ${e.localizedMessage}"
            } finally {
                isSaving = false
            }
        }
    }

    /**
     * Permite actualizar un campo individual del estado del usuario.
     */
    fun onUserFieldChange(updatedUser: User) {
        userState = updatedUser
    }

    fun clearFeedbackMessage() {
        feedbackMessage = null
    }

    fun logoutUser(onLogoutSuccess: () -> Unit) {
        userService.logout()
        onLogoutSuccess()
    }
}