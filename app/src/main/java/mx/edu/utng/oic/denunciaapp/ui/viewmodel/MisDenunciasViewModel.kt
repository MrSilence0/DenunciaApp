package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import mx.edu.utng.oic.denunciaapp.data.model.Denuncia
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService
import mx.edu.utng.oic.denunciaapp.data.service.UserService // NECESARIO para obtener el ID del usuario

/**
 * ViewModel para la pantalla de "Mis Denuncias".
 * Se encarga de obtener el ID del usuario actual y cargar
 * las denuncias asociadas a ese ID desde el DenunciaService.
 *
 * @param denunciaService Servicio para interactuar con la colección de denuncias en Firestore.
 * @param userService Servicio para obtener el ID del usuario activo.
 */
class MisDenunciasViewModel(
    private val denunciaService: DenunciaService,
    private val userService: UserService // Dependencia para obtener el ID de usuario
) : ViewModel() {

    // Estado que contiene la lista de denuncias para la UI
    private val _denuncias = mutableStateOf<List<Denuncia>>(emptyList())
    val denuncias: State<List<Denuncia>> = _denuncias

    // Estado que indica si hay una operación de carga activa
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _feedbackMessage = mutableStateOf<String?>(null)
    val feedbackMessage: State<String?> = _feedbackMessage

    init {
        loadDenuncias()
    }

    fun loadDenuncias() {
        viewModelScope.launch {
            _isLoading.value = true
            _feedbackMessage.value = null // Limpiar mensajes anteriores

            try {
                // 1. Obtener el ID del usuario activo (ej. autenticado o anónimo)
                // Usamos getOrCreateUserId() para asegurar que siempre haya un ID disponible.
                val idUser = userService.getOrCreateUserId()

                if (idUser.isEmpty()) {
                    _feedbackMessage.value = "No se pudo obtener el usuario actual."
                    return@launch
                }

                // 2. Llamar al servicio para obtener las denuncias filtradas por idUser
                val result = denunciaService.getDenunciasByUserId(idUser)

                // 3. Actualizar el estado con los resultados
                _denuncias.value = result

                if (result.isEmpty()) {
                    _feedbackMessage.value = "Aún no has realizado ninguna denuncia."
                }

            } catch (e: Exception) {
                // Manejo de errores durante la carga
                _feedbackMessage.value = "Error cargando denuncias: ${e.localizedMessage}"

            } finally {
                // 4. Finalizar la carga
                _isLoading.value = false
            }
        }
    }

    fun clearFeedbackMessage() {
        _feedbackMessage.value = null
    }
}

