package mx.edu.utng.oic.denunciaapp.data.model

import java.util.Date

/**
 * Clase corregida. Se añadieron valores por defecto a todos los parámetros
 * del constructor principal. Esto es esencial para que Firebase Firestore
 * pueda deserializar los documentos (convertir datos de la DB a objetos Message)
 * al leer los mensajes.
 */
data class Message(
    val id: String = "",
    val forumId: String = "",
    val userId: String = "",
    val contenido: String = "",
    val dateTime: Date = Date()
)

/**
 * Clase ChatThread (no requiere corrección, ya que si sus campos
 * tienen valores por defecto, cumple con el requisito, aunque se recomienda
 * revisar si Date es serializable/deserializable por Firebase correctamente
 * en todos los casos.
 */
data class ChatThread(
    val forumId: String = "",
    val forumTitle: String = "",
    val lastMessageContent: String = "",
    val lastMessageTime: Date = Date(),
    val unreadCount: Int = 0
)