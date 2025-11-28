package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import mx.edu.utng.oic.denunciaapp.data.repository.DenunciaRepository
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService
import mx.edu.utng.oic.denunciaapp.data.service.UserService // Importar el servicio de usuario

/**
 * Fábrica personalizada para instanciar MisDenunciasViewModel.
 *
 * Esta clase se encarga de crear las dependencias (Repository, Services)
 * y pasarlas al constructor del ViewModel.
 */
class MisDenunciasViewModelFactory(
    // Las dependencias necesarias para crear el grafo de objetos
    private val firestore: FirebaseFirestore
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MisDenunciasViewModel::class.java)) {

            // 1. Crear la instancia de UserService
            // El UserService ya maneja la autenticación y la colección 'users'.
            val userService = UserService()

            // 2. Crear la instancia de DenunciaRepository (necesita Firestore)
            val denunciaRepository = DenunciaRepository(firestore)

            // 3. Crear la instancia de DenunciaService (necesita el Repository)
            val denunciaService = DenunciaService(denunciaRepository)

            // 4. Crear el MisDenunciasViewModel con los servicios
            @Suppress("UNCHECKED_CAST")
            return MisDenunciasViewModel(
                denunciaService = denunciaService,
                userService = userService
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
