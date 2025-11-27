package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.edu.utng.oic.denunciaapp.data.model.Denuncia
import mx.edu.utng.oic.denunciaapp.data.repository.DenunciaRepository

/**
 * ViewModel para manejar la lógica de la creación de una nueva denuncia.
 *
 * @param repository La instancia de DenunciaRepository inyectada para acceder a los datos.
 */
class NuevaDenunciaViewModel(
    private val repository: DenunciaRepository
) : ViewModel() {

    // 🌟 Estado que la UI observará
    private val _uiState = MutableStateFlow<DenunciaState>(DenunciaState.Idle)
    val uiState: StateFlow<DenunciaState> = _uiState

    /**
     * Función principal para registrar cualquier tipo de denuncia.
     *
     * @param nuevaDenuncia El objeto Denuncia (puede ser DenunciaFotografica, RoboVehiculo, etc.).
     */
    fun registrarDenuncia(nuevaDenuncia: Denuncia) {
        // 1. Indicar a la UI que la operación ha comenzado
        _uiState.value = DenunciaState.Loading

        // 2. Usar viewModelScope para lanzar la operación en un hilo seguro (Coroutines)
        viewModelScope.launch {
            val result = repository.addDenuncia(nuevaDenuncia)

            // 3. Actualizar el estado con el resultado de la operación
            _uiState.value = if (result) {
                DenunciaState.Success("Denuncia ${nuevaDenuncia.tipo.name} registrada con éxito.")
            } else {
                DenunciaState.Error("Error al registrar la denuncia. Intente de nuevo.")
            }
        }
    }

    /**
     * Restablece el estado de la UI a 'Idle' después de una operación (para evitar re-disparos).
     */
    fun resetState() {
        _uiState.value = DenunciaState.Idle
    }
}

/**
 * Sealed class para representar los posibles estados de la UI durante el registro.
 */
sealed class DenunciaState {
    object Idle : DenunciaState() // Estado inicial o después de un restablecimiento
    object Loading : DenunciaState() // Estado mientras se procesa la solicitud
    data class Success(val message: String) : DenunciaState() // Estado de éxito con mensaje
    data class Error(val message: String) : DenunciaState() // Estado de error con mensaje
}