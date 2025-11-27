/*package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.edu.utng.oic.denunciaapp.data.model.Denuncia
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService
import mx.edu.utng.oic.denunciaapp.data.service.UserService // Importación correcta para obtener el ID del usuario

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
            // Se realiza la operación de E/S en un hilo de Dispatchers.IO
            withContext(Dispatchers.IO) {
                // Obtener el ID del usuario activo. Si es anónimo o no existe, usamos un ID temporal.
                // Esta llamada es correcta y debería resolverse, ya que UserService.kt la define.
                val currentUserId = userService.getCurrentUserId() ?: "default_user_id"

                denuncias = denunciaService.getDenunciasByUserId(currentUserId)
            }
            isLoading = false
        }
    }

    // Usaremos esta función en otra pantalla para crear una denuncia de prueba y ver el guardado
    fun saveTestDenuncia(denuncia: Denuncia) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                denunciaService.saveDenuncia(denuncia)
                loadDenuncias() // Recargar la lista después de guardar
            }
        }
    }
}

/**
 * Factoría para crear instancias de MisDenunciasViewModel con dependencias.
 */
class MisDenunciasViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MisDenunciasViewModel::class.java)) {
            // NOTA: Asegúrate de que DenunciaService esté visible aquí (requiere Context)
            val denunciaService = DenunciaService(context)
            val userService = UserService() // Instancia del servicio de usuario
            return MisDenunciasViewModel(denunciaService, userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/