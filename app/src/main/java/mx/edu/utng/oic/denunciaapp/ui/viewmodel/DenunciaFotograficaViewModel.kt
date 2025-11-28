package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.edu.utng.oic.denunciaapp.data.model.DenunciaFotografica
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import android.util.Log
import java.util.Date
import java.util.UUID

/**
 * ViewModel encargado de gestionar la denuncia fotográfica.
 */
class DenunciaFotograficaViewModel(
    private val denunciaService: DenunciaService,
    private val userService: UserService
) : ViewModel() {

    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving.asStateFlow()

    private val _saveError = MutableStateFlow<String?>(null)
    val saveError: StateFlow<String?> = _saveError.asStateFlow()

    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess.asStateFlow()

    /**
     * Enviar una nueva denuncia fotográfica.
     */
    fun submitDenuncia(
        description: String,
        locationAddress: String,
        latitud: Double?,
        longitud: Double?,
        imageUri: String? // puedes guardarlo si decides activarlo
    ) {
        if (_isSaving.value) return

        _isSaving.value = true
        _saveError.value = null
        _saveSuccess.value = false

        viewModelScope.launch {
            try {
                val userId = withContext(Dispatchers.IO) {
                    userService.getOrCreateUserId()
                } ?: run {
                    _saveError.value = "Fallo la autenticación. Revise la conexión o las reglas de Firebase."
                    _isSaving.value = false
                    Log.e("DenunciaFotograficaVM", "No se pudo obtener ID de usuario.")
                    return@launch
                }

                val newDenuncia = DenunciaFotografica(
                    id = UUID.randomUUID().toString(),
                    idUser = userId,
                    creationDate = Date(),
                    descripcion = description,
                    locationAddress = locationAddress,
                    latitud = latitud,
                    longitud = longitud
                    // imageUri = imageUri  <-- agregalo si lo vuelves a usar
                )

                withContext(Dispatchers.IO) {
                    denunciaService.saveDenuncia(newDenuncia)
                }

                _saveSuccess.value = true

            } catch (e: Exception) {
                _saveError.value = e.localizedMessage ?: "Error desconocido al enviar la denuncia."
                Log.e("DenunciaFotograficaVM", "Error al guardar denuncia", e)
            } finally {
                _isSaving.value = false
            }
        }
    }

    fun resetStates() {
        _saveError.value = null
        _saveSuccess.value = false
    }
}

/**
 * Factory para crear DenunciaFotograficaViewModel.
 */
class DenunciaFotograficaViewModelFactory(
    private val denunciaService: DenunciaService,
    private val userService: UserService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DenunciaFotograficaViewModel::class.java)) {
            return DenunciaFotograficaViewModel(denunciaService, userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
