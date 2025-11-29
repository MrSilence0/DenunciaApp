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
import mx.edu.utng.oic.denunciaapp.data.model.PersonaDesaparecida
import mx.edu.utng.oic.denunciaapp.data.model.TipoIncidente
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import mx.edu.utng.oic.denunciaapp.ui.screens.Sexo
import java.util.Date
import java.util.UUID

/**
 * ViewModel específico para la creación y gestión del Reporte de Persona Desaparecida.
 */
class PersonaDesaparecidaViewModel(
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
     * Crea y guarda un reporte de Persona Desaparecida.
     */
    fun submitDenuncia(
        nombre: String,
        sexo: Sexo,
        edad: String,
        descripcionFisica: String,
        vestimenta: String,
        detallesHecho: String // Campo de la pantalla PersonaDesaparecida
    ) {
        if (_isSaving.value) return

        _isSaving.value = true
        _saveError.value = null
        _saveSuccess.value = false

        viewModelScope.launch {
            try {
                // 1. Obtener o crear el ID del usuario
                val userId = withContext(Dispatchers.IO) {
                    userService.getOrCreateUserId()
                } ?: run {
                    _saveError.value = "Fallo al obtener el ID de usuario. Por favor, reinicie la aplicación."
                    _isSaving.value = false
                    Log.e("PersonaDesaparecidaVM", "Fallo al obtener o crear ID de usuario.")
                    return@launch
                }

                // Asegurar que la edad sea un entero válido o 0 si no se pudo parsear
                val edadInt = edad.toIntOrNull() ?: 0

                // 2. Construir el objeto PersonaDesaparecida
                val newDenuncia = PersonaDesaparecida(
                    id = UUID.randomUUID().toString(),
                    idUser = userId,
                    creationDate = Date(),
                    nombreDesaparecido = nombre,
                    sexo = sexo.name, // Convertimos el enum Sexo a String
                    descripcionFisica = descripcionFisica,
                    vestimenta = vestimenta,
                    edad = edadInt,
                    // Usamos el tipo correcto para esta denuncia
                    tipo = TipoIncidente.PERSONA_DESAPARECIDA
                    // Nota: si el campo detallesHecho se requiere, debe agregarse en el modelo PersonaDesaparecida
                )

                // 3. Guardar la denuncia (usando el servicio genérico)
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
                Log.e("PersonaDesaparecidaVM", "Error al enviar el reporte de persona desaparecida", e)
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
 * Factoría específica para crear instancias de PersonaDesaparecidaViewModel.
 * Esta clase es referenciada por el objeto centralizado DenunciaAppViewModelFactory.
 */
class PersonaDesaparecidaViewModelFactory(
    private val denunciaService: DenunciaService,
    private val userService: UserService
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonaDesaparecidaViewModel::class.java)) {
            return PersonaDesaparecidaViewModel(denunciaService, userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
