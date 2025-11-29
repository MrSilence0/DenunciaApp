package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.edu.utng.oic.denunciaapp.data.model.Foro
import mx.edu.utng.oic.denunciaapp.data.service.ForoService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import java.util.Date
import java.util.UUID

/**
 * ViewModel para gestionar las operaciones de creación, edición y eliminación de Foros.
 */
class ForoViewModel(
    private val foroService: ForoService,
    private val userService: UserService
) : ViewModel() {

    // --- ESTADOS DE LA OPERACIÓN ---
    private val _isProcessing = MutableStateFlow(false)
    val isProcessing: StateFlow<Boolean> = _isProcessing.asStateFlow()

    private val _operationError = MutableStateFlow<String?>(null)
    val operationError: StateFlow<String?> = _operationError.asStateFlow()

    private val _operationSuccess = MutableStateFlow(false)
    val operationSuccess: StateFlow<Boolean> = _operationSuccess.asStateFlow()

    private fun resetStates() {
        _isProcessing.value = false
        _operationError.value = null
        _operationSuccess.value = false
    }

    /**
     * Crea o edita un foro. Si foroId es nulo o vacío, crea uno nuevo.
     */
    fun saveForo(
        foroId: String?,
        tema: String
        // ⚠️ ELIMINADO: currentUsername
    ) {
        if (_isProcessing.value) return
        resetStates()

        if (tema.isBlank()) {
            _operationError.value = "El tema del foro no puede estar vacío."
            return
        }

        _isProcessing.value = true

        viewModelScope.launch {
            try {
                // 1. Obtener el ID del usuario (debe estar registrado)
                val userId = withContext(Dispatchers.IO) {
                    userService.getLoggedUserId()
                }

                // 2. Obtener el nombre de usuario asociado al ID
                val userProfile = withContext(Dispatchers.IO) {
                    userService.getUserById(userId)
                }

                val username = userProfile?.nombre ?: userProfile?.correoElectronico

                if (username.isNullOrBlank()) {
                    throw Exception("No se pudo obtener el nombre de usuario para crear el foro. Complete su perfil.")
                }


                // 3. Determinar si es Creación o Edición
                val foroToSave: Foro = if (foroId.isNullOrBlank()) {
                    // CREACIÓN: Nuevo foro
                    Foro(
                        id = UUID.randomUUID().toString(),
                        tema = tema,
                        username = username, // Usamos el nombre obtenido del perfil
                        creationDate = Date(),
                        idUser = userId
                    )
                } else {
                    // EDICIÓN: Obtener el foro existente para actualizar solo el tema
                    val existingForo = foroService.getForoById(foroId)
                    if (existingForo == null) {
                        throw Exception("Foro a editar no encontrado.")
                    }
                    if (existingForo.idUser != userId) {
                        throw IllegalAccessException("No tienes permiso para editar este foro.")
                    }
                    // Mantenemos el nombre original del foro, solo actualizamos el tema
                    existingForo.copy(tema = tema)
                }

                // 4. Guardar/Actualizar en el servicio
                withContext(Dispatchers.IO) {
                    foroService.saveForo(foroToSave)
                }

                _operationSuccess.value = true

            } catch (e: IllegalStateException) {
                _operationError.value = "Debes iniciar sesión para crear o editar foros."
                Log.e("ForoVM", "Usuario no autenticado", e)
            } catch (e: IllegalAccessException) {
                _operationError.value = e.message
                Log.e("ForoVM", "Intento de edición sin permiso", e)
            } catch (e: Exception) {
                _operationError.value = e.localizedMessage ?: "Error al guardar el foro."
                Log.e("ForoVM", "Error al guardar el foro", e)
            } finally {
                _isProcessing.value = false
            }
        }
    }

    /**
     * Elimina un foro existente.
     */
    fun deleteForo(foroId: String) {
        if (_isProcessing.value) return
        resetStates()

        if (foroId.isBlank()) {
            _operationError.value = "ID de foro no válido."
            return
        }

        _isProcessing.value = true

        viewModelScope.launch {
            try {
                val userId = withContext(Dispatchers.IO) {
                    userService.getLoggedUserId()
                }

                // 1. Verificar permisos antes de eliminar (IMPORTANTE)
                val existingForo = foroService.getForoById(foroId)
                if (existingForo == null) {
                    throw Exception("Foro no encontrado, no se puede eliminar.")
                }
                if (existingForo.idUser != userId) {
                    throw IllegalAccessException("Solo el creador puede eliminar este foro.")
                }

                // 2. Eliminar en el servicio
                withContext(Dispatchers.IO) {
                    foroService.deleteForo(foroId)
                }

                _operationSuccess.value = true

            } catch (e: IllegalStateException) {
                _operationError.value = "Debes iniciar sesión para eliminar foros."
            } catch (e: IllegalAccessException) {
                _operationError.value = e.message
                Log.e("ForoVM", "Intento de eliminación sin permiso", e)
            } catch (e: Exception) {
                _operationError.value = e.localizedMessage ?: "Error al eliminar el foro."
                Log.e("ForoVM", "Error al eliminar el foro", e)
            } finally {
                _isProcessing.value = false
            }
        }
    }
}