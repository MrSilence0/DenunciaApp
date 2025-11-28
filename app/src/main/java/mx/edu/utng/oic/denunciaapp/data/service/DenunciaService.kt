package mx.edu.utng.oic.denunciaapp.data.service

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import mx.edu.utng.oic.denunciaapp.data.model.*
import mx.edu.utng.oic.denunciaapp.data.repository.DenunciaRepository // Importar DenunciaRepository
import java.util.Date

/**
 * Servicio de negocio para la gestión de denuncias.
 * Utiliza DenunciaRepository para interactuar con Firestore.
 */
class DenunciaService(
    private val denunciaRepository: DenunciaRepository
) {

    /**
     * Guarda cualquier tipo de Denuncia en Firestore.
     * @param denuncia El objeto Denuncia a guardar.
     * @return true si la operación fue exitosa.
     */
    suspend fun saveDenuncia(denuncia: Denuncia): Boolean {
        return denunciaRepository.addDenuncia(denuncia)
    }

    /**
     * Obtiene todas las denuncias asociadas a un ID de usuario.
     * La lógica de mapeo polimórfico (sealed class) se realiza aquí.
     */
    suspend fun getDenunciasByUserId(userId: String): List<Denuncia> {
        val denuncias = mutableListOf<Denuncia>()
        try {
            val querySnapshot = denunciaRepository.getDenunciasCollection()
                .whereEqualTo("idUser", userId)
                .get()
                .await()

            for (document in querySnapshot.documents) {
                // Usamos el campo 'denunciaClassType' para determinar a qué subclase mapear.
                val type = document.getString(DENUNCIA_TYPE_FIELD)

                val denuncia = when (type) {
                    "DenunciaFotografica" -> document.toObject<DenunciaFotografica>()
                    "PersonaDesaparecida" -> document.toObject<PersonaDesaparecida>()
                    "RoboVehiculo" -> document.toObject<RoboVehiculo>()
                    "Extorsion" -> document.toObject<Extorsion>()
                    "RoboCasa" -> document.toObject<RoboCasa>()
                    "RoboObjeto" -> document.toObject<RoboObjeto>()
                    "DenunciaViolencia" -> document.toObject<DenunciaViolencia>()
                    else -> {
                        Log.w("DenunciaService", "Clase de denuncia no reconocida o faltante para ID: ${document.id}")
                        null
                    }
                }
                denuncia?.let { denuncias.add(it) }
            }
        } catch (e: Exception) {
            Log.e("DenunciaService", "Error al obtener las denuncias por usuario: $userId", e)
        }
        return denuncias
    }

    // Nota: La función getDenunciasByTipo se puede mover al Service/Repository si es necesaria.
}

