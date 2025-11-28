package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import mx.edu.utng.oic.denunciaapp.data.repository.DenunciaRepository
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService
import mx.edu.utng.oic.denunciaapp.data.service.UserService

/**
 * Fábrica centralizada para todos los ViewModels.
 */
object DenunciaAppViewModelFactory {

    private val userService = UserService()
    private val firestore = FirebaseFirestore.getInstance()
    private val denunciaRepository = DenunciaRepository(firestore)
    private val denunciaService = DenunciaService(denunciaRepository)

    fun createPersonaDesaparecidaViewModelFactory(): PersonaDesaparecidaViewModelFactory {
        return PersonaDesaparecidaViewModelFactory(denunciaService, userService)
    }

    fun createDenunciaFotograficaViewModelFactory(): DenunciaFotograficaViewModelFactory {
        return DenunciaFotograficaViewModelFactory(denunciaService, userService)
    }

    fun createRoboVehiculoViewModelFactory(): RoboVehiculoViewModelFactory {
        return RoboVehiculoViewModelFactory(denunciaService, userService)
    }

    fun createExtorsionViewModelFactory(): ExtorsionViewModelFactory {
        return ExtorsionViewModelFactory(denunciaService, userService)
    }

    fun createRoboCasaViewModelFactory(): RoboCasaViewModelFactory {
        return RoboCasaViewModelFactory(denunciaService, userService)
    }

    fun createRoboObjetoViewModelFactory(): ViewModelProvider.Factory {
        return RoboObjetoViewModel.Factory(denunciaService, userService)
    }

    fun createDenunciaViolenciaViewModelFactory(): DenunciaViolenciaViewModelFactory {
        return DenunciaViolenciaViewModelFactory(denunciaService, userService)
    }
}
