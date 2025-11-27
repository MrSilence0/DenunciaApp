package mx.edu.utng.oic.denunciaapp.data.service

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import mx.edu.utng.oic.denunciaapp.data.model.User
import kotlinx.coroutines.tasks.await
import java.util.UUID

/**
 * Servicio encargado de la lógica de autenticación (SIMULADA) y persistencia de datos de usuario
 * en Firebase Firestore.
 *
 * NOTA: La autenticación de FirebaseAuth ha sido reemplazada por una simulación debido a
 * problemas de resolución. Se genera un ID temporal único para cada usuario.
 */
class UserService {

    // Se mantiene Firestore para la persistencia de datos
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    private val usersCollection = db.collection("users")

    /**
     * Simula el registro de un nuevo usuario.
     * Genera un UID temporal y guarda los datos adicionales en Firestore, incluyendo la contraseña
     * (solo para fines de simulación de login).
     *
     * @param user El objeto User con todos los datos necesarios.
     * @throws Exception si el guardado en Firestore falla.
     */
    suspend fun registerUser(user: User) {
        // 1. Simular la creación de usuario y obtener un UID
        val uid = UUID.randomUUID().toString()

        // 2. Preparar el mapa de datos para guardar en Firestore
        // Para SIMULAR el login, debemos guardar la contraseña. En un entorno real, solo se
        // guardaría un hash seguro de la contraseña.
        val userMap = hashMapOf(
            "id" to uid,
            "nombre" to user.nombre,
            "sexo" to user.sexo,
            "telefono" to user.telefono,
            "fechaNacimiento" to user.fechaNacimiento,
            "correoElectronico" to user.correoElectronico,
            "descripcion" to user.descripcion,
            "rol" to user.rol.name,
            "respectPoints" to user.respectPoints,
            "isAnonymus" to user.isAnonymus,
            "contrasenia" to user.contrasenia // Necesaria para la simulación de login
        )

        // 3. Guardar los datos del usuario en Firestore usando el UID como ID del documento
        usersCollection.document(uid)
            .set(userMap)
            .await()
    }

    /**
     * Simula el inicio de sesión de un usuario.
     * Busca el usuario por correo electrónico en Firestore y verifica la contraseña.
     *
     * @param email Correo electrónico del usuario.
     * @param password Contraseña proporcionada.
     * @return El objeto User si la autenticación es exitosa.
     * @throws Exception si el usuario no existe o la contraseña es incorrecta.
     */
    suspend fun loginUser(email: String, password: String): User {
        val user = getUserByEmail(email)

        if (user == null) {
            throw Exception("Usuario no encontrado.")
        }

        // SIMULACIÓN DE VERIFICACIÓN: Compara la contraseña sin encriptación.
        // ADVERTENCIA: En producción, ESTO NUNCA DEBE HACERSE. Se usaría Bcrypt o similar.
        if (user.contrasenia != password) {
            throw Exception("Contraseña incorrecta.")
        }

        return user
    }

    /**
     * Función auxiliar para buscar un usuario en Firestore por correo electrónico.
     */
    private suspend fun getUserByEmail(email: String): User? {
        // Realiza la consulta a la colección 'users' donde 'correoElectronico' sea igual al email
        val querySnapshot = usersCollection
            .whereEqualTo("correoElectronico", email)
            .limit(1) // Solo necesitamos el primer resultado
            .get()
            .await()

        // Si la consulta devuelve documentos, mapea el primero al objeto User
        return if (!querySnapshot.isEmpty) {
            querySnapshot.documents.first().toObject<User>()
        } else {
            null
        }
    }

    /**
     * Lógica simplificada para el inicio de sesión anónimo.
     * Simula un inicio de sesión anónimo devolviendo un ID temporal que puede ser
     * utilizado para identificar la sesión actual.
     *
     * @return String que simula el UID de la sesión anónima.
     */
    suspend fun signInAnonymously(): String {
        // En una implementación real con Firebase Auth, esto iniciaría una sesión.
        // Aquí devolvemos un UID temporal para simular que hay una sesión activa.
        return "anonymous_user_${UUID.randomUUID().toString().substring(0, 8)}"
    }
}