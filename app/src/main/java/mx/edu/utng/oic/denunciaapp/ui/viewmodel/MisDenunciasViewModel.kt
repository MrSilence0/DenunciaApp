package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import mx.edu.utng.oic.denunciaapp.data.model.Denuncia
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService
import mx.edu.utng.oic.denunciaapp.data.service.UserService

class MisDenunciasViewModel(
    private val denunciaService: DenunciaService,
    private val userService: UserService
) : ViewModel() {

    private val _denuncias = mutableStateOf<List<Denuncia>>(emptyList())
    val denuncias: State<List<Denuncia>> = _denuncias

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

            try {
                val userId = userService.getOrCreateUserId()

                if (userId == null) {
                    _feedbackMessage.value = "No se pudo obtener el usuario actual."
                    _isLoading.value = false
                    return@launch
                }

                val result = denunciaService.getDenunciasByUserId(userId)
                _denuncias.value = result

            } catch (e: Exception) {
                _feedbackMessage.value = "Error cargando denuncias: ${e.localizedMessage}"

            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearFeedbackMessage() {
        _feedbackMessage.value = null
    }
}
