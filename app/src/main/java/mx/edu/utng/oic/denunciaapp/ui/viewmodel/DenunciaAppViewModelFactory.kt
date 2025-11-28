package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import com.google.firebase.firestore.FirebaseFirestore
import mx.edu.utng.oic.denunciaapp.data.repository.DenunciaRepository
import mx.edu.utng.oic.denunciaapp.data.service.DenunciaService
import mx.edu.utng.oic.denunciaapp.data.service.UserService

/**
 * Objeto Singleton centralizado para la inyección de dependencias de ViewModels.
 * Este objeto inicializa todos los servicios (como Firestore, Repository, Services)
 * y proporciona los métodos para crear las factorías específicas de cada ViewModel.
 *
 * ¡ESTE ARCHIVO DEBE SER ÚNICO Y NO REDECLARADO!
 */
object DenunciaAppViewModelFactory {
    // Servicios de infraestructura (Singleton)
    private val userService = UserService()
    private val firestoreInstance = FirebaseFirestore.getInstance()
    private val denunciaRepository = DenunciaRepository(firestoreInstance)
    private val denunciaService = DenunciaService(denunciaRepository)


    /**
     * Crea la factoría específica para PersonaDesaparecidaViewModel.
     * @return PersonaDesaparecidaViewModelFactory
     */
    fun createPersonaDesaparecidaViewModelFactory(): PersonaDesaparecidaViewModelFactory {
        return PersonaDesaparecidaViewModelFactory(denunciaService, userService)
    }

    /**
     * Crea la factoría específica para DenunciaFotograficaViewModel.
     * @return DenunciaFotograficaViewModelFactory
     */
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
}


