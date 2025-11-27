package mx.edu.utng.oic.denunciaapp.data.model

import java.util.Date

// Modelo para un mensaje individual dentro de un foro
data class Message(
    val id: String,
    val forumId: String,
    val userId: String,
    val contenido: String,
    val dateTime: Date
)

// Modelo para un hilo de chat en la bandeja de entrada (un foro con actividad)
data class ChatThread(
    val forumId: String,
    val forumTitle: String,
    val lastMessageContent: String,
    val lastMessageTime: Date,
    val unreadCount: Int = 0
)