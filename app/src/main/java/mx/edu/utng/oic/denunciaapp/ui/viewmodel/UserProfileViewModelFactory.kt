package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.utng.oic.denunciaapp.data.service.UserService

class UserProfileViewModelFactory(
    private val userService: UserService // Dependencia: Ya se inicializa solo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserProfileViewModel(
                userService = userService
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
