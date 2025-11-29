package mx.edu.utng.oic.denunciaapp.data.service

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import mx.edu.utng.oic.denunciaapp.data.model.Foro
import android.util.Log
import com.google.firebase.firestore.Query

class ForoService {

    private val db = FirebaseFirestore.getInstance()
    private val forumsCollection = db.collection("forums")

    /**
     * Obtiene la lista completa de foros, ordenados por fecha de creación descendente.
     * Incluye logging para detectar fallos en el mapeo (toObject).
     */
    suspend fun getAllForos(): List<Foro> {
        return try {
            val snapshot = forumsCollection
                .orderBy("creationDate", Query.Direction.DESCENDING)
                .get()
                .await()

            Log.d("ForoService", "Documentos obtenidos de Firestore: ${snapshot.documents.size}")

            snapshot.documents.mapNotNull { document ->
                val foro = try {
                    document.toObject(Foro::class.java)?.copy(id = document.id)
                } catch (e: Exception) {
                    // Si el mapeo falla (por campos faltantes, tipos incorrectos, etc.)
                    Log.e("ForoService", "FALLO CRÍTICO DE MAPEO en documento ID: ${document.id}", e)
                    null
                }

                if (foro == null) {
                    Log.w("ForoService", "Documento ID: ${document.id} NO PUDO SER MAPPEADO a Foro. Verifique el modelo de datos (data class Foro).")
                }
                foro
            }
        } catch (e: Exception) {
            // Error en la conexión, permisos o consulta
            Log.e("ForoService", "Error al ejecutar la consulta de foros", e)
            emptyList()
        }
    }

    /**
     * Guarda o actualiza un foro en Firestore.
     */
    suspend fun saveForo(foro: Foro) {
        try {
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


}