package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import mx.edu.utng.oic.denunciaapp.data.repository.DenunciaRepository
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService
import mx.edu.utng.oic.denunciaapp.data.service.UserService

class MisDenunciasViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Crear la instancia de Firestore y pasarla al repositorio
        val firestore = FirebaseFirestore.getInstance()
        val denunciaRepository = DenunciaRepository(firestore)
        val denunciaService = DenunciaService(denunciaRepository)
        val userService = UserService()

        @Suppress("UNCHECKED_CAST")
        return MisDenunciasViewModel(denunciaService, userService) as T
    }
}
