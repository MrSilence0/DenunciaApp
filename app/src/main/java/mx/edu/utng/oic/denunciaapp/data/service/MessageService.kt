package mx.edu.utng.oic.denunciaapp.data.service

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import mx.edu.utng.oic.denunciaapp.data.model.Message
import java.util.Date

class MessageService {

    private val db = FirebaseFirestore.getInstance()
    private val messagesCollection = db.collection("messages")
    private val forumsCollection = db.collection("forums")

    /**
     * Guarda un nuevo mensaje (respuesta) en Firestore y actualiza el contador de respuestas
     * en el documento del Foro padre.
     */
    suspend fun sendMessage(message: Message) {
        try {
            // 1. Guardar el mensaje usando su ID como ID del documento
            messagesCollection.document(message.id)
                .set(message)
                .await()

            // 2. ⭐️ Actualizar el contador de respuestas (responseCount) en el Foro
            // Usamos una transacción para asegurar la consistencia.
            db.runTransaction { transaction ->
                val forumRef = forumsCollection.document(message.forumId)
                val forumSnapshot = transaction.get(forumRef)

                // Obtener el contador actual, o 0 si no existe/es nulo
                val currentCount = forumSnapshot.getLong("responseCount")?.toInt() ?: 0
                val newCount = currentCount + 1

                // Actualizar el contador en el documento del foro
                transaction.update(forumRef, "responseCount", newCount)
            }.await()

        } catch (e: Exception) {
            Log.e("MessageService", "Error al enviar mensaje o actualizar contador del foro ${message.forumId}", e)
            throw e
        }
    }

    /**
     * Obtiene los mensajes de un foro específico en tiempo real (Flow).
     * @param forumId El ID del foro.
     */
    fun getMessagesForForum(forumId: String): Flow<List<Message>> = callbackFlow {
        val subscription = messagesCollection
            .whereEqualTo("forumId", forumId) // Filtrar por el ID del foro
            .orderBy("dateTime", Query.Direction.ASCENDING) // Ordenar por fecha de envío
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w("MessageService", "Error al escuchar mensajes", e)
                    // Si hay un error, cerrar el flow con la excepción
                    close(e)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val messages = snapshot.documents.mapNotNull { document ->
                        document.toObject(Message::class.java)?.copy(id = document.id)
                    }
                    trySend(messages) // Enviar la nueva lista de mensajes al Flow
                }
            }

        // Esta lambda se ejecuta cuando el Flow ya no es observado (collect).
        awaitClose {
            subscription.remove()
        }
    }
}