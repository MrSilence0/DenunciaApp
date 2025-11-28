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
// Importamos el modelo oficial de la capa de datos
import mx.edu.utng.oic.denunciaapp.data.model.RoboVehiculo
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import java.util.Date
import java.util.UUID

// ----------------------------------------------------------------------
// 1. MODELO DE DATOS ESPECÍFICO PARA ROBO DE VEHÍCULO
//    (Eliminado ya que se importa desde mx.edu.utng.oic.denunciaapp.data.model)
// ----------------------------------------------------------------------


// ----------------------------------------------------------------------
// 2. VIEWMODEL
// ----------------------------------------------------------------------

/**
 * ViewModel específico para la creación y gestión del Reporte de Robo de Vehículo.
 */
class RoboVehiculoViewModel(
    private val denunciaService: DenunciaService,
    private val userService: UserService
) : ViewModel() {

    // --- ESTADOS OBSERVABLES ---
    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving.asStateFlow()

    private val _saveError = MutableStateFlow<String?>(null)
    val saveError: StateFlow<String?> = _saveError.asStateFlow()

    private val _saveSuccess = MutableStateFlow(false)
    // CORRECCIÓN: asStateStateFlow() -> asStateFlow()
    val saveSuccess: StateFlow<Boolean> = _saveSuccess.asStateFlow()

    /**
     * Crea y guarda un reporte de Robo de Vehículo.
     */
    fun submitDenuncia(
        placas: String,
        numeroSerie: String,
        marca: String,
        color: String,
        anio: String,
        nombreVehiculo: String, // Este será mapeado a 'nombreReportante'
        imageUri: String? // Se ignora al construir el modelo, pero se recibe de la UI
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
                    Log.e("RoboVehiculoVM", "Fallo al obtener o crear ID de usuario.")
                    return@launch
                }

                // 2. Conversión de datos y validación
                val anioInt = anio.toIntOrNull()
                if (anioInt == null) {
                    _saveError.value = "El año debe ser un número entero válido."
                    _isSaving.value = false
                    return@launch
                }


                // 3. Construir el objeto RoboVehiculo usando el modelo importado
                val newDenuncia = RoboVehiculo(
                    id = UUID.randomUUID().toString(), // Generación de ID
                    idUser = userId,
                    creationDate = Date(),
                    placas = placas,
                    numeroSerie = numeroSerie,
                    marca = marca,
                    color = color,
                    anio = anioInt, // Usamos el Int convertido
                    // Mapeamos 'nombreVehiculo' de la UI a 'nombreReportante' del modelo
                    nombreReportante = nombreVehiculo,
                    // Se omiten 'imageUri', 'tipo', 'denunciaClassType' porque el modelo no los requiere o los tiene por defecto
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
                Log.e("RoboVehiculoVM", "Error al enviar el reporte de robo de vehículo", e)
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
 * Factoría para crear instancias de RoboVehiculoViewModel.
 * Esta clase es referenciada por el objeto centralizado DenunciaAppViewModelFactory.
 */
class RoboVehiculoViewModelFactory(
    private val denunciaService: DenunciaService,
    private val userService: UserService
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoboVehiculoViewModel::class.java)) {
            return RoboVehiculoViewModel(denunciaService, userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}