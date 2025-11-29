package mx.edu.utng.oic.denunciaapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.edu.utng.oic.denunciaapp.data.model.Foro
import mx.edu.utng.oic.denunciaapp.data.model.Message
import mx.edu.utng.oic.denunciaapp.data.service.ForoService
import mx.edu.utng.oic.denunciaapp.data.service.MessageService
import mx.edu.utng.oic.denunciaapp.data.service.UserService
import java.util.Date
import java.util.UUID

class MessageViewModel(
    private val messageService: MessageService,
    private val foroService: ForoService,
    private val userService: UserService
) : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()

    private val _forum = MutableStateFlow<Foro?>(null)
    val forum: StateFlow<Foro?> = _forum.asStateFlow()

    private val _currentUserId = MutableStateFlow<String?>(null)
    val currentUserId: StateFlow<String?> = _currentUserId.asStateFlow()

    private val _isSending = MutableStateFlow(false)
    val isSending: StateFlow<Boolean> = _isSending.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Variable para almacenar la referencia a la recolección de mensajes
    private var messagesCollectorJob: kotlinx.coroutines.Job? = null

    init {
        loadCurrentUserId()
    }

    private fun loadCurrentUserId() {
        viewModelScope.launch {
            try {
                _currentUserId.value = withContext(Dispatchers.IO) {
                    userService.getLoggedUserId()
                }
            } catch (e: Exception) {
                _currentUserId.value = null
            }
        }
    }

    /**
     * Inicia la recolección de mensajes para el foro especificado.
     * Si ya estaba recolectando otro foro, lo detiene.
     */
    fun startListeningForMessages(forumId: String) {
        messagesCollectorJob?.cancel() // Cancelar la escucha anterior
        _messages.value = emptyList() // Limpiar mensajes antiguos

        // 1. Cargar el foro para obtener el título y el contenido original
        loadForumDetails(forumId)

        // 2. Iniciar la escucha en tiempo real
        messagesCollectorJob = messageService.getMessagesForForum(forumId)
            .onEach { messageList ->
                _messages.value = messageList
            }
            .catch { e ->
                _error.value = "Error al cargar mensajes: ${e.message}"
                Log.e("MessageVM", "Error collecting messages", e)
            }
            .launchIn(viewModelScope)
    }

    /**
     * Carga los detalles del foro.
     */
    private fun loadForumDetails(forumId: String) {
        viewModelScope.launch {
            try {
                val loadedForum = withContext(Dispatchers.IO) {
                    foroService.getForoById(forumId)
                }
                _forum.value = loadedForum
            } catch (e: Exception) {
                _error.value = "Error al cargar detalles del foro: ${e.message}"
            }
        }
    }


    /**
     * Envía un nuevo mensaje como respuesta al foro.
     */
    fun sendMessage(forumId: String, content: String) {
        if (_isSending.value || content.isBlank()) return

        val userId = _currentUserId.value
        if (userId == null) {
            _error.value = "Debes iniciar sesión para responder."
            return
        }

        _isSending.value = true

        viewModelScope.launch {
            try {
                val newMessage = Message(
                    id = UUID.randomUUID().toString(),
                    forumId = forumId,
                    userId = userId,
                    contenido = content.trim(),
                    dateTime = Date()
                )

                withContext(Dispatchers.IO) {
                    messageService.sendMessage(newMessage)
                }
                // Los mensajes se actualizarán automáticamente gracias al Flow/Listener

            } catch (e: Exception) {
                _error.value = "Error al enviar la respuesta: ${e.message}"
            } finally {
                _isSending.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }

    fun fetchForumDetails(forumId: String) {
        viewModelScope.launch {
            try {
                // Llama a la función que debes crear en ForoService
                val foroDetails = foroService.getForoById(forumId)
                _forum.value = foroDetails
            } catch (e: Exception) {
                // Manejo de errores
                _error.value = "Error al cargar los detalles del foro: ${e.message}"
            }
        }
    }
}

