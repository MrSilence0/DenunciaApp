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
// Importamos la clase abstracta Denuncia
import mx.edu.utng.oic.denunciaapp.data.model.Denuncia
import mx.edu.utng.oic.denunciaapp.data.repository.DenunciaRepository
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date
import java.util.UUID

/**
 * ViewModel específico para la creación y gestión de la Denuncia Fotográfica.
 * Utiliza el UserService para obtener el ID del usuario.
 */
class DenunciaFotograficaViewModel(
    private val denunciaService: DenunciaService,
    private val userService: UserService // Inyectamos el UserService
) : ViewModel() {

    // --- ESTADOS OBSERVABLES ---
    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving.asStateFlow()

    private val _saveError = MutableStateFlow<String?>(null)
    val saveError: StateFlow<String?> = _saveError.asStateFlow()

    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess.asStateFlow()

    /**
     * Crea y guarda una DenunciaFotografica.
     */
    fun submitDenuncia(
        description: String,
        locationAddress: String,
        latitud: Double?,
        longitud: Double?,
        imageUri: String? // URI de la imagen (simulada)
    ) {
        if (_isSaving.value) return

        // 3. Llamar al servicio para guardar
        _isSaving.value = true
        _saveError.value = null
        _saveSuccess.value = false

        viewModelScope.launch {
            try {
                // CORRECCIÓN CLAVE: Usamos getOrCreateUserId() que gestiona la sesión anónima
                // y es la función suspendida correcta para obtener el ID.
                val userId = withContext(Dispatchers.IO) {
                    userService.getOrCreateUserId()
                } ?: run {
                    // Si falla la autenticación anónima, lanzamos error y salimos.
                    _saveError.value = "Fallo la autenticación. Revise su conexión o reglas de Firebase."
                    _isSaving.value = false
                    Log.e("DenunciaFotograficaVM", "Fallo al obtener o crear ID de usuario.")
                    return@launch
                }


                // 2. Construir el objeto DenunciaFotografica
                val newDenuncia = DenunciaFotografica(
                    id = UUID.randomUUID().toString(), // ID temporal
                    idUser = userId, // ID del usuario logeado/anónimo
                    creationDate = Date(),
                    descripcion = description,
                    locationAddress = locationAddress,
                    latitud = latitud,
                    longitud = longitud,
                    // Eliminamos la propiedad 'status' ya que fue removida del modelo DenunciaFotografica
                    // Se mantiene imageUri si es necesaria
                    // imageUri = imageUri
                )

                // 3. Guardar la denuncia
                withContext(Dispatchers.IO) {
                    denunciaService.saveDenuncia(newDenuncia)
                }
                _saveSuccess.value = true
            } catch (e: Exception) {
                // Captura el error de Firestore
                val errorMessage = e.localizedMessage ?: "Error desconocido al enviar la denuncia."
                _saveError.value = errorMessage
                Log.e("DenunciaFotograficaVM", "Error al enviar la denuncia", e)
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
 * Factoría para crear instancias de DenunciaFotograficaViewModel.
 */
class DenunciaFotograficaViewModelFactory(
    private val denunciaService: DenunciaService,
    private val userService: UserService
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DenunciaFotograficaViewModel::class.java)) {
            return DenunciaFotograficaViewModel(denunciaService, userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

/**
 * Factory centralizado para inyección de dependencias (Singleton para servicios).
 */
object DenunciaAppViewModelFactory {
    // Usar el UserService con Firebase Auth
    private val userService = UserService()

    private val firestoreInstance = FirebaseFirestore.getInstance()
    private val denunciaRepository = DenunciaRepository(firestoreInstance)
    private val denunciaService = DenunciaService(denunciaRepository)


    fun createDenunciaFotograficaViewModelFactory(): DenunciaFotograficaViewModelFactory {
        return DenunciaFotograficaViewModelFactory(denunciaService, userService)
    }
}