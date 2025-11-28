package mx.edu.utng.oic.denunciaapp.data.repository

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import mx.edu.utng.oic.denunciaapp.data.model.Denuncia

class DenunciaRepository(
    private val firestore: FirebaseFirestore
) {

    private val COLLECTION_NAME = "denuncias"

    fun getDenunciasCollection(): CollectionReference =
        firestore.collection(COLLECTION_NAME)

    /**
     * Guarda SIEMPRE usando el ID del objeto Denuncia.
     * Esto asegura que se guarden correctamente los campos heredados
     * como idUser, creationDate, etc.
     */
    suspend fun addDenuncia(denuncia: Denuncia): Boolean {
        return try {
            val collection = firestore.collection(COLLECTION_NAME)

            // Usa SIEMPRE el ID del objeto
            collection.document(denuncia.id).set(denuncia).await()

            Log.i("DenunciaRepository", "Denuncia guardada con ID: ${denuncia.id}")

            true
        } catch (e: Exception) {
            Log.e(
                "DenunciaRepository",
                "Error al guardar denuncia: ${e.message}",
                e
            )
            throw e
        }
    }
}
