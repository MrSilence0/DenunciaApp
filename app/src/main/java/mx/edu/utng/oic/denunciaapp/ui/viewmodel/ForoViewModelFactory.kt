package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.utng.oic.denunciaapp.data.service.ForoService
import mx.edu.utng.oic.denunciaapp.data.service.UserService

/**
 * Factoría para crear instancias de ForoViewModel.
 */
class ForoViewModelFactory(
    private val foroService: ForoService,
    private val userService: UserService
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForoViewModel::class.java)) {
            return ForoViewModel(foroService, userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

