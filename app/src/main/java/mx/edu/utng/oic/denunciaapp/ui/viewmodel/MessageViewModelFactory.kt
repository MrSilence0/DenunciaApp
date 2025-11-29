package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.utng.oic.denunciaapp.data.service.ForoService
import mx.edu.utng.oic.denunciaapp.data.service.MessageService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import mx.edu.utng.oic.denunciaapp.ui.viewmodel.MessageViewModel

class MessageViewModelFactory(
    private val messageService: MessageService,
    private val foroService: ForoService,
    private val userService: UserService
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MessageViewModel::class.java)) {
            return MessageViewModel(messageService, foroService, userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}