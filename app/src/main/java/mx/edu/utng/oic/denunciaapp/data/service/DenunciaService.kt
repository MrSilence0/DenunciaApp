package mx.edu.utng.oic.denunciaapp.data.service

import android.util.Log
import kotlinx.coroutines.tasks.await
import mx.edu.utng.oic.denunciaapp.data.model.*
import mx.edu.utng.oic.denunciaapp.data.repository.DenunciaRepository

class DenunciaService(
    private val denunciaRepository: DenunciaRepository
) {

    /**
     * Guarda una denuncia en Firestore usando el Repository.
     */
    suspend fun saveDenuncia(denuncia: Denuncia): Boolean {
        return try {
            denunciaRepository.addDenuncia(denuncia)
        } catch (e: Exception) {
            Log.e("DenunciaService", "Error guardando denuncia: ${e.localizedMessage}", e)
            false
        }
    }

    /**
     * Obtiene todas las denuncias del usuario.
     */
    suspend fun getDenunciasByUserId(userId: String): List<Denuncia> {
        val denuncias = mutableListOf<Denuncia>()
        try {
            val snapshot = denunciaRepository
                .getDenunciasCollection()
                .whereEqualTo("idUser", userId)
                .get()
                .await()

            for (doc in snapshot.documents) {

                val type = doc.getString(DENUNCIA_TYPE_FIELD)

                val denuncia = when (type) {
                    "DenunciaFotografica" -> doc.toObject(DenunciaFotografica::class.java)
                    "PersonaDesaparecida" -> doc.toObject(PersonaDesaparecida::class.java)
                    "RoboVehiculo" -> doc.toObject(RoboVehiculo::class.java)
                    "Extorsion" -> doc.toObject(Extorsion::class.java)
                    "RoboCasa" -> doc.toObject(RoboCasa::class.java)
                    "RoboObjeto" -> doc.toObject(RoboObjeto::class.java)
                    "DenunciaViolencia" -> doc.toObject(DenunciaViolencia::class.java)
                    else -> {
                        Log.w("DenunciaService", "Tipo de denuncia desconocido en doc: ${doc.id}")
                        null
                    }
                }

                if (denuncia != null) denuncias.add(denuncia)
            }

        } catch (e: Exception) {
            Log.e("DenunciaService", "Error al obtener denuncias para $userId", e)
        }

        return denuncias
    }
}



