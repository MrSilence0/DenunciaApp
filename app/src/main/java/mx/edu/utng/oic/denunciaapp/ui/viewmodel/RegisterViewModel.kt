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
 * ViewModel para la pantalla de Registro, maneja la interacción con UserService,
 * permitiendo el registro de usuarios que se almacenan en Firestore.
 */
class RegisterViewModel(
    private val userService: UserService = UserService()
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    // Mensaje de error o éxito para mostrar en la UI
    var message by mutableStateOf<String?>(null)
        private set

    // Estado para saber si el registro fue exitoso
    var isRegistrationSuccessful by mutableStateOf(false)
        private set

    /**
     * Intenta registrar un nuevo usuario con la información proporcionada.
     */
    fun register(
        nombre: String,
        email: String,
        password: String,
        confirmPassword: String,
        sexo: String,
        telefono: String,
        fechaNacimiento: String,
        descripcion: String // Usado para especificar 'Otro' en campo sexo
    ) {
        // Limpiar estados anteriores
        message = null
        isRegistrationSuccessful = false
        isLoading = true

        viewModelScope.launch {
            try {
                // 1. Validaciones de datos obligatorios
                if (nombre.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() || telefono.isBlank() || fechaNacimiento.isBlank()) {
                    throw Exception("Todos los campos obligatorios del formulario deben ser llenados.")
                }

                // 2. Validaciones de contraseña
                if (password != confirmPassword) {
                    throw Exception("Las contraseñas no coinciden.")
                }
                if (password.length < 6) {
                    throw Exception("La contraseña debe tener al menos 6 caracteres.")
                }

                // 3. Comprobar si el usuario ya existe consultando Firestore
                if (userService.getUserByEmail(email) != null) {
                    throw Exception("El correo electrónico ya está registrado. Intente iniciar sesión.")
                }

                // 4. Crear el objeto User con los datos completos
                val newUser = User(
                    nombre = nombre,
                    correoElectronico = email,
                    contrasenia = password,
                    sexo = sexo,
                    telefono = telefono,
                    fechaNacimiento = fechaNacimiento,
                    descripcion = descripcion,
                )

                // 5. Registrar el usuario en Firestore a través del UserService
                userService.registerUser(newUser)

                // Éxito
                message = "Registro exitoso. Ahora puedes iniciar sesión."
                isRegistrationSuccessful = true

            } catch (e: Exception) {
                // Fracaso: capturar el mensaje de error
                val errorMessage = e.message ?: "Error desconocido en el registro."
                message = errorMessage
            } finally {
                isLoading = false
            }
        }
    }

    /**
     * Función para limpiar el mensaje.
     */
    fun clearMessage() {
        message = null
    }
}

