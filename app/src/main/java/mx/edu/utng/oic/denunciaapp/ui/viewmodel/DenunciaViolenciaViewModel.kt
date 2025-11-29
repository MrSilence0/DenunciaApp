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
import mx.edu.utng.oic.denunciaapp.data.model.DenunciaViolencia
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import java.util.Date
import java.util.UUID

/**
 * ViewModel específico para la creación y gestión del Reporte de Denuncia de Violencia.
 */
class DenunciaViolenciaViewModel(
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
     * Crea y guarda un reporte de Denuncia de Violencia.
     */
    fun submitDenuncia(
        descripcionHecho: String,
        ubicacionText: String?, // Dirección legible
        descripcionConducta: String,
        telefono: String,
        confirmarTelefono: String,
        // Los datos de ubicación (lat/lng) se deben obtener del estado del mapa,
        // pero por simplicidad en esta función, se asume que la pantalla los provee
        // o que ya están disponibles a través de otros estados.
        latitud: Double?,
        longitud: Double?,
        imageUri: String? // Campo no usado en el modelo de datos DenunciaViolencia, pero útil para la UI
    ) {
        if (_isSaving.value) return

        _isSaving.value = true
        _saveError.value = null
        _saveSuccess.value = false

        viewModelScope.launch {
            try {
                // --- 1. Autenticación de Usuario (Asíncrona y robusta) ---
                val userId = withContext(Dispatchers.IO) {
                    userService.getOrCreateUserId()
                } ?: run {
                    _saveError.value = "Fallo al obtener el ID de usuario. Por favor, intente de nuevo."
                    _isSaving.value = false
                    Log.e("DenunciaViolenciaVM", "Fallo al obtener o crear ID de usuario.")
                    return@launch
                }

                // --- 2. Validación de datos ---
                if (descripcionHecho.isBlank() || descripcionConducta.isBlank() || telefono.isBlank()) {
                    _saveError.value = "Los campos de descripción, conducta y teléfono son obligatorios."
                    _isSaving.value = false
                    return@launch
                }

                if (telefono != confirmarTelefono) {
                    _saveError.value = "El teléfono de contacto y su confirmación no coinciden."
                    _isSaving.value = false
                    return@launch
                }

                if (ubicacionText.isNullOrBlank() && (latitud == null || longitud == null)) {
                    _saveError.value = "La ubicación es obligatoria. Por favor, seleccione un punto en el mapa."
                    _isSaving.value = false
                    return@launch
                }

                // --- 3. Construir el objeto DenunciaViolencia ---
                val newDenuncia = DenunciaViolencia(
                    id = UUID.randomUUID().toString(), // Generación de ID
                    idUser = userId,
                    creationDate = Date(),
                    descripcion = descripcionHecho,
                    tipoConducta = descripcionConducta, // Mapeado a tipoConducta
                    telefonoContacto = telefono,
                    locationAddress = ubicacionText,
                    latitud = latitud,
                    longitud = longitud
                )

                // --- 4. Guardar la denuncia ---
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
                Log.e("DenunciaViolenciaVM", "Error al enviar el reporte de violencia", e)
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

/**
 * Factoría para crear instancias de DenunciaViolenciaViewModel.
 */
class DenunciaViolenciaViewModelFactory(
    private val denunciaService: DenunciaService,
    private val userService: UserService
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DenunciaViolenciaViewModel::class.java)) {
            return DenunciaViolenciaViewModel(denunciaService, userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

