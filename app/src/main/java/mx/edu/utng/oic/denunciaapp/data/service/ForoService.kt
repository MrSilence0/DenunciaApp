package mx.edu.utng.oic.denunciaapp.data.service

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import mx.edu.utng.oic.denunciaapp.data.model.Foro
import android.util.Log

class ForoService {

    private val db = FirebaseFirestore.getInstance()
    private val forumsCollection = db.collection("forums")

    /**
     * Guarda o actualiza un foro en Firestore.
     */
    suspend fun saveForo(foro: Foro) {
        try {
            // Usa el ID del foro como ID del documento
            forumsCollection.document(foro.id)
                .set(foro)
                .await()
        } catch (e: Exception) {
            Log.e("ForoService", "Error al guardar el foro con ID: ${foro.id}", e)
            throw e
        }
    }

    /**
     * Elimina un foro de Firestore por su ID.
     */
    suspend fun deleteForo(foroId: String) {
        try {
            forumsCollection.document(foroId)
                .delete()
                .await()
        } catch (e: Exception) {
            Log.e("ForoService", "Error al eliminar el foro con ID: $foroId", e)
            throw e
        }
    }

    /**
     * Obtiene un foro específico por su ID.
     */
    suspend fun getForoById(foroId: String): Foro? {
        return try {
            val snapshot = forumsCollection.document(foroId).get().await()
            snapshot.toObject(Foro::class.java)?.copy(id = snapshot.id)
        } catch (e: Exception) {
            Log.e("ForoService", "Error al obtener foro con ID: $foroId", e)
            null
        }
    }

    /**
     * Incrementa el contador de respuestas (útil al añadir un nuevo mensaje).
     * Nota: Esto requiere reglas de seguridad adecuadas en Firebase.
     */
    // Opcional: fun incrementResponseCount(foroId: String) {}

}