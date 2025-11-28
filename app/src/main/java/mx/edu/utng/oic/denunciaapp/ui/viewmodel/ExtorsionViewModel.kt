package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.edu.utng.oic.denunciaapp.data.model.Extorsion
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import java.util.Date
import java.util.UUID

// ----------------------------------------------------------------------
// 2. VIEWMODEL
// ----------------------------------------------------------------------

/**
 * ViewModel específico para la creación y gestión del Reporte de Extorsión.
 */
class ExtorsionViewModel(
    private val denunciaService: DenunciaService,
    private val userService: UserService
) : ViewModel() {

    // --- ESTADOS OBSERVABLES ---
    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving.asStateFlow()

    private val _saveError = MutableStateFlow<String?>(null)
    val saveError: StateFlow<String?> = _saveError.asStateFlow()

    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess.asStateFlow()

    /**
     * Crea y guarda un reporte de Extorsión.
     */
    fun submitDenuncia(
        numeroTelefonico: String,
        descripcion: String
    ) {
        if (_isSaving.value) return

        _isSaving.value = true
        _saveError.value = null
        _saveSuccess.value = false

        viewModelScope.launch {
            try {
                // 1. Autenticación de Usuario
                val userId = withContext(Dispatchers.IO) {
                    userService.getOrCreateUserId()
                } ?: run {
                    _saveError.value = "Fallo al obtener el ID de usuario."
                    _isSaving.value = false
                    Log.e("ExtorsionVM", "Fallo al obtener o crear ID de usuario.")
                    return@launch
                }

                // 2. Validación de datos
                if (numeroTelefonico.isBlank() || descripcion.isBlank()) {
                    _saveError.value = "Los campos de número telefónico y descripción son obligatorios."
                    _isSaving.value = false
                    return@launch
                }


                // 3. Construir el objeto Extorsion
                val newDenuncia = Extorsion(
                    id = UUID.randomUUID().toString(), // Generación de ID
                    idUser = userId,
                    creationDate = Date(),
                    numeroTelefonico = numeroTelefonico,
                    descripcion = descripcion
                )

                // 4. Guardar la denuncia
                val success = withContext(Dispatchers.IO) {
                    denunciaService.saveDenuncia(newDenuncia)
                }

                if (success) {
                    _saveSuccess.value = true
                } else {
                    _saveError.value = "Error al guardar la denuncia."
                }
            } catch (e: Exception) {
                val errorMessage = e.localizedMessage ?: "Error desconocido al enviar el reporte."
                _saveError.value = errorMessage
                Log.e("ExtorsionVM", "Error al enviar el reporte de extorsión", e)
            } finally {
                _isSaving.value = false
            }
        }
    }

    /**
     * Limpia los estados de error y éxito.
     */
    fun resetStates() {
        _saveError.value = null
        _saveSuccess.value = false
    }
}


// ----------------------------------------------------------------------
// 3. FACTORÍA ESPECÍFICA
// ----------------------------------------------------------------------

/**
 * Factoría para crear instancias de ExtorsionViewModel.
 * Esta clase es referenciada por el objeto centralizado DenunciaAppViewModelFactory.
 */
class ExtorsionViewModelFactory(
    private val denunciaService: DenunciaService,
    private val userService: UserService
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExtorsionViewModel::class.java)) {
            return ExtorsionViewModel(denunciaService, userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}