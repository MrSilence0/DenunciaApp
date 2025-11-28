package mx.edu.utng.oic.denunciaapp.data.model

import java.util.Date

data class Message(
    val id: String,
    val forumId: String,
    val userId: String,
    val contenido: String,
    val dateTime: Date
)

data class ChatThread(
    val forumId: String,
    val forumTitle: String,
    val lastMessageContent: String,
    val lastMessageTime: Date,
    val unreadCount: Int = 0
)