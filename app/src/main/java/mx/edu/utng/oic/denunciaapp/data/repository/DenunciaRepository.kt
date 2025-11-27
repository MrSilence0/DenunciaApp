package mx.edu.utng.oic.denunciaapp.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import mx.edu.utng.oic.denunciaapp.data.model.*
import java.util.Date

// Constante para el nombre de la colección en Firestore
private const val DENUNCIAS_COLLECTION = "denuncias"

/**
 * Repositorio encargado de las operaciones de datos de las denuncias.
 * Actúa como la única fuente de verdad, abstrae el origen de los datos (Firestore).
 *
 * @param firestore Instancia de FirebaseFirestore inyectada.
 */
class DenunciaRepository(
    private val firestore: FirebaseFirestore
) {

    /**
     * Agrega una nueva denuncia a la colección de Firestore.
     * Utiliza el patrón de sealed class para manejar cualquier tipo de Denuncia.
     *
     * @param denuncia El objeto Denuncia a guardar (puede ser DenunciaFotografica, RoboVehiculo, etc.)
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    suspend fun addDenuncia(denuncia: Denuncia): Boolean {
        return try {
            // Firestore automáticamente serializa los data classes de Kotlin si las propiedades coinciden.
            // Usamos el id de la denuncia como ID del documento en Firestore.
            firestore.collection(DENUNCIAS_COLLECTION)
                .document(denuncia.id)
                .set(denuncia)
                .await() // Espera a que la operación se complete
            Log.d("DenunciaRepository", "Denuncia agregada con éxito. ID: ${denuncia.id}")
            true
        } catch (e: Exception) {
            Log.e("DenunciaRepository", "Error al agregar la denuncia", e)
            false
        }
    }

    // --- Funciones de Recuperación (Ejemplos) ---

    /**
     * Obtiene una lista de todas las denuncias de un tipo específico (genérico).
     * NOTA: Firestore no maneja nativamente el polimorfismo de sealed classes al leer,
     * por lo que la lectura requiere lógica adicional (como un campo 'tipo' o 'clase'
     * en cada documento) para reconstruir el objeto correcto.
     *
     * Este ejemplo simple solo obtendrá una lista genérica de 'Denuncia' si el polimorfismo
     * no es estricto en la lectura, o requerirá un mapeo manual.
     *
     * Para ser más específico, es mejor obtener un tipo de denuncia en particular.
     */
    suspend fun getDenunciasByTipo(tipo: TipoIncidente): List<Denuncia> {
        val denuncias = mutableListOf<Denuncia>()
        try {
            val querySnapshot = firestore.collection(DENUNCIAS_COLLECTION)
                .whereEqualTo("tipo", tipo.name) // Filtra por el nombre del enum
                .get()
                .await()

            for (document in querySnapshot.documents) {
                // Se necesita lógica de mapeo. Aquí un ejemplo simple (solo para el tipo DenunciaFotografica)
                // En una app real, usarías un campo como 'tipo' para decidir a qué data class mapear.
                when (tipo) {
                    TipoIncidente.DENUNCIA_FOTOGRAFICA -> {
                        document.toObject<DenunciaFotografica>()?.let {
                            denuncias.add(it)
                        }
                    }
                    TipoIncidente.PERSONA_DESAPARECIDA -> {
                        document.toObject<PersonaDesaparecida>()?.let {
                            denuncias.add(it)
                        }
                    }
                    // Implementar lógica para los demás tipos...
                    else -> {
                        // En un caso más robusto, se debería manejar el error o ignorar.
                        Log.w("DenunciaRepository", "Tipo de denuncia no mapeado para lectura: ${document.id}")
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("DenunciaRepository", "Error al obtener las denuncias por tipo: $tipo", e)
        }
        return denuncias
    }
}
