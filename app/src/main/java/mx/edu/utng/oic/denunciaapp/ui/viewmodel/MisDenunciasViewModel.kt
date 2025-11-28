package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.edu.utng.oic.denunciaapp.data.model.Denuncia
import mx.edu.utng.oic.denunciaapp.data.repository.DenunciaRepository // Importación de Repository
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService // Importación de Service
import mx.edu.utng.oic.denunciaapp.data.service.UserService // Importación de UserService
import android.util.Log // <--- Importación añadida para resolver 'Log.e'

/**
 * ViewModel para gestionar el listado de denuncias del usuario activo.
 */
class MisDenunciasViewModel(
    private val denunciaService: DenunciaService,
    private val userService: UserService
) : ViewModel() {

    var denuncias by mutableStateOf(emptyList<Denuncia>())
        private set

    var isLoading by mutableStateOf(false)
        private set

    // Mensaje para feedback al usuario
    var feedbackMessage by mutableStateOf<String?>(null)
        private set


    init {
        // Cargar las denuncias al inicializar el ViewModel
        loadDenuncias()
    }

    /**
     * Carga las denuncias del usuario actualmente logueado.
     */
    fun loadDenuncias() {
        isLoading = true
        viewModelScope.launch {
            try {
                // Se realiza la operación de E/S en un hilo de Dispatchers.IO
                withContext(Dispatchers.IO) {
                    // Obtener el ID del usuario activo. Implementación asumida en UserService.kt
                    // Si el usuario no está autenticado, usa un ID por defecto para evitar fallos.
                    val currentUserId = userService.getCurrentUserId() ?: "default_user_id"

                    denuncias = denunciaService.getDenunciasByUserId(currentUserId)
                }
            } catch (e: Exception) {
                feedbackMessage = "Error al cargar denuncias: ${e.localizedMessage}"
                Log.e("MisDenunciasVM", "Error al cargar denuncias", e)
            } finally {
                isLoading = false
            }
        }
    }

    /**
     * Guarda una denuncia y recarga la lista.
     */
    fun saveDenuncia(denuncia: Denuncia) {
        viewModelScope.launch {
            isLoading = true
            try {
                val success = withContext(Dispatchers.IO) {
                    denunciaService.saveDenuncia(denuncia)
                }
                if (success) {
                    feedbackMessage = "Denuncia guardada exitosamente."
                    loadDenuncias() // Recargar la lista después de guardar
                } else {
                    feedbackMessage = "Fallo al guardar la denuncia."
                }
            } catch (e: Exception) {
                feedbackMessage = "Error al guardar: ${e.localizedMessage}"
                Log.e("MisDenunciasVM", "Error al guardar denuncia", e)
            } finally {
                isLoading = false
            }
        }
    }

    fun clearFeedbackMessage() {
        feedbackMessage = null
    }
}

/**
 * Factoría para crear instancias de MisDenunciasViewModel con dependencias.
 */
class MisDenunciasViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MisDenunciasViewModel::class.java)) {
            // Inicialización de Firebase (o inyección si usas Dagger/Hilt)
            val firestore = FirebaseFirestore.getInstance()

            // Repositorio
            val denunciaRepository = DenunciaRepository(firestore)

            // Servicios
            val denunciaService = DenunciaService(denunciaRepository)
            val userService = UserService() // Asumiendo que UserService no necesita inyección compleja aquí

            return MisDenunciasViewModel(denunciaService, userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}