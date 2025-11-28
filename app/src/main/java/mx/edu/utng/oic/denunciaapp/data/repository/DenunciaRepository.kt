// DenunciaRepository.kt

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
     */
    suspend fun addDenuncia(denuncia: Denuncia): Boolean {
        return try {
            firestore.collection(COLLECTION_NAME)
                .document(denuncia.id)
                .set(denuncia)
                .await()

            Log.i("DenunciaRepository", "Denuncia guardada con ID correcto: ${denuncia.id}")

            true
        } catch (e: Exception) {
            Log.e("DenunciaRepository", "ERROR al guardar denuncia: ${e.message}", e)
            false
        }
    }

}
