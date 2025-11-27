package mx.edu.utng.oic.denunciaapp.data.service

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mx.edu.utng.oic.denunciaapp.data.model.* // Importa todas las clases Denuncia
import java.lang.reflect.Type

/**
 * Servicio encargado de gestionar el almacenamiento local de las denuncias
 * usando SharedPreferences y serialización/deserialización con Gson.
 *
 * NOTA: Asume que se ha añadido la dependencia de Gson en build.gradle.kts.
 */
class DenunciaService(context: Context) {

    private val gson = Gson()
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "DenunciaAppPrefs"
        private const val KEY_DENUNCIAS = "denuncias_list"
    }

    /**
     * Carga todas las denuncias almacenadas localmente.
     * @return Lista de Denuncia (incluyendo subclases específicas).
     */
    fun getLocalDenuncias(): List<Denuncia> {
        val json = prefs.getString(KEY_DENUNCIAS, null)
        if (json == null) {
            return emptyList()
        }

        // Gson no maneja bien la deserialización de listas de clases selladas (sealed) directamente.
        // Se deserializa como una lista de Maps (objetos genéricos) y luego reconstruimos el objeto Denuncia.
        val type: Type = object : TypeToken<List<Map<String, Any>>>() {}.type
        val jsonList: List<Map<String, Any>> = gson.fromJson(json, type)

        return jsonList.mapNotNull { map ->
            val jsonElement = gson.toJsonTree(map).asJsonObject
            val classType = jsonElement.get(DENUNCIA_TYPE_FIELD)?.asString ?: return@mapNotNull null

            // Usar el discriminador para saber qué clase deserializar
            when (classType) {
                "DenunciaFotografica" -> gson.fromJson(jsonElement, DenunciaFotografica::class.java)
                "PersonaDesaparecida" -> gson.fromJson(jsonElement, PersonaDesaparecida::class.java)
                "RoboVehiculo" -> gson.fromJson(jsonElement, RoboVehiculo::class.java)
                "Extorsion" -> gson.fromJson(jsonElement, Extorsion::class.java)
                "RoboCasa" -> gson.fromJson(jsonElement, RoboCasa::class.java)
                "RoboObjeto" -> gson.fromJson(jsonElement, RoboObjeto::class.java)
                "DenunciaViolencia" -> gson.fromJson(jsonElement, DenunciaViolencia::class.java)
                else -> null
            }
        }
    }

    /**
     * Carga las denuncias asociadas a un usuario específico.
     */
    fun getDenunciasByUserId(userId: String): List<Denuncia> {
        return getLocalDenuncias().filter { it.idUser == userId }
    }

    /**
     * Guarda una nueva denuncia en la lista local y actualiza SharedPreferences.
     * @param denuncia La instancia de Denuncia (cualquiera de sus subclases).
     */
    fun saveDenuncia(denuncia: Denuncia) {
        val currentList = getLocalDenuncias().toMutableList()
        currentList.add(denuncia)
        saveList(currentList)
    }

    private fun saveList(list: List<Denuncia>) {
        val json = gson.toJson(list)
        prefs.edit().putString(KEY_DENUNCIAS, json).apply()
    }
}
