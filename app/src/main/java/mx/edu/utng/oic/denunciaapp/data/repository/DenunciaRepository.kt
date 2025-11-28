package mx.edu.utng.oic.denunciaapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import mx.edu.utng.oic.denunciaapp.data.model.Denuncia
import android.util.Log // Añadido para el log detallado

/**
 * Repositorio para la entidad Denuncia. Se comunica directamente con Firebase Firestore.
 */
class DenunciaRepository(
    private val firestore: FirebaseFirestore
) {
    private val DENUNCIAS_COLLECTION = "denuncias"

    /**
     * Devuelve la referencia a la colección de denuncias para permitir consultas
     * más complejas en la capa de Servicio (como la deserialización polimórfica).
     */
    fun getDenunciasCollection(): CollectionReference {
        return firestore.collection(DENUNCIAS_COLLECTION)
    }

    /**
     * Guarda cualquier tipo de Denuncia. (Implementa addDenuncia para el Service)
     * @throws Exception en caso de fallo de Firestore para que el ViewModel lo maneje.
     */
    suspend fun addDenuncia(denuncia: Denuncia): Boolean {
        try {
            // Firestore genera un ID automáticamente al usar add()
            firestore.collection(DENUNCIAS_COLLECTION)
                .add(denuncia)
                .await()
            return true
        } catch (e: Exception) {
            // Registrar el error detallado para el desarrollador
            Log.e("DenunciaRepository", "Error al guardar denuncia en Firestore: ${e.message}", e)
            // Relanzar la excepción. El ViewModel la capturará y mostrará el mensaje al usuario.
            throw e
        }
    }
}

