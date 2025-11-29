package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.edu.utng.oic.denunciaapp.data.service.UserService

class MenuViewModel(private val userService: UserService) : ViewModel() {

    // Estado que contendrá el nombre del usuario
    private val _userName = MutableStateFlow("Cargando...")
    val userName: StateFlow<String> = _userName

    init {
        fetchUserName()
    }

    private fun fetchUserName() {
        viewModelScope.launch {
            try {
                val uid = userService.getLoggedUserId()
                val user = userService.getUserById(uid)
                // Usamos el campo 'nombre' del modelo User
                _userName.value = user?.nombre ?: "Usuario Desconocido"
            } catch (e: IllegalStateException) {
                // Esto maneja el caso de que no haya usuario logeado (aunque no debería pasar si se navega desde Login)
                _userName.value = "Sesión Caducada"
            } catch (e: Exception) {
                _userName.value = "Error de carga"
            }
        }
    }
}

class MenuViewModelFactory(private val userService: UserService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MenuViewModel(userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}