package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers // Añadido para withContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext // Añadido para operaciones de E/S
import mx.edu.utng.oic.denunciaapp.data.model.RoboObjeto
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import java.util.Date
import java.util.UUID

/**
 * ViewModel para gestionar el reporte de Robo de Objeto.
 * Maneja la lógica de validación del formulario y la persistencia de datos.
 */
class RoboObjetoViewModel(
    private val denunciaService: DenunciaService,
    private val userService: UserService
) : ViewModel() {

    // --- Estados de la UI ---
    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving

    private val _saveError = MutableStateFlow<String?>(null)
    val saveError: StateFlow<String?> = _saveError

    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess

    /**
     * Valida los campos y envía el reporte de robo de objeto.
     */
    fun submitDenuncia(
        tipoObjeto: String,
        marca: String,
        estado: String,
        color: String,
        anio: String, // Recibido como String para fácil manejo de entrada
        locationAddress: String?,
        latitud: Double?,
        longitud: Double?
    ) {
        if (_isSaving.value) return // Evitar envíos múltiples
        resetStates() // Limpiar estados de error/éxito anteriores

        // --- 1. Validación Básica (No Suspending) ---
        if (tipoObjeto.isBlank() || marca.isBlank() || estado.isBlank() || color.isBlank()) {
            _saveError.value = "Por favor, complete todos los campos obligatorios del objeto (Tipo, Marca, Estado, Color)."
            return
        }

        if (locationAddress.isNullOrBlank() && (latitud == null || longitud == null)) {
            _saveError.value = "La ubicación es obligatoria. Por favor, seleccione un punto en el mapa."
            return
        }

        // --- 2. Conversión y Validación de Año (No Suspending) ---
        val anioInt = try {
            if (anio.isNotBlank()) anio.toInt() else null
        } catch (e: NumberFormatException) {
            _saveError.value = "El campo Año debe ser un número válido."
            return
        }

        // Si la validación preliminar es exitosa, iniciar el proceso asíncrono
        _isSaving.value = true

        viewModelScope.launch {
            try {
                // --- 3. OBTENCIÓN DEL ID DE USUARIO (CORREGIDO: Asíncrono en hilo de I/O) ---
                val userId = withContext(Dispatchers.IO) {
                    // Usamos getOrCreateUserId() como en ExtorsionViewModel para mayor robustez
                    userService.getOrCreateUserId()
                } ?: run {
                    _saveError.value = "Error de autenticación: ID de usuario no disponible."
                    _isSaving.value = false // Detener el estado de carga si falla la autenticación
                    return@launch
                }

                // --- 4. Creación y Guardado de Denuncia ---
                val denuncia = RoboObjeto(
                    id = UUID.randomUUID().toString(),
                    idUser = userId, // ID asegurado
                    creationDate = Date(),
                    tipoObjeto = tipoObjeto,
                    marca = marca,
                    estado = estado,
                    color = color,
                    anio = anioInt,
                    locationAddress = locationAddress,
                    latitud = latitud,
                    longitud = longitud
                )

                val result = denunciaService.saveDenuncia(denuncia)
                if (result) {
                    _saveSuccess.value = true
                } else {
                    _saveError.value = "No se pudo guardar la denuncia. Intente de nuevo."
                }
            } catch (e: Exception) {
                _saveError.value = "Ocurrió un error al guardar la denuncia: ${e.message}"
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

    // --- INTEGRACIÓN DE LA FACTORÍA ---
    companion object {
        fun Factory(
            denunciaService: DenunciaService,
            userService: UserService
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(RoboObjetoViewModel::class.java)) {
                    return RoboObjetoViewModel(denunciaService, userService) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}