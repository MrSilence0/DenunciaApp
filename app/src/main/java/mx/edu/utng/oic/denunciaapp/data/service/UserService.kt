package mx.edu.utng.oic.denunciaapp.data.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import mx.edu.utng.oic.denunciaapp.data.model.User

class UserService {

    private val auth = FirebaseAuth.getInstance()
    private val usersCollection = FirebaseFirestore.getInstance().collection("users")

    /**
     * Obtener UID actual o generar uno anónimo
     */
    suspend fun getOrCreateUserId(): String {
        val current = auth.currentUser
        if (current != null) return current.uid

        val result = auth.signInAnonymously().await()
        return result.user?.uid ?: throw Exception("Error creando usuario anónimo")
    }

    /**
     * 🔥 Necesaria para RegisterViewModel
     * Busca un usuario por correo en Firestore
     */
    suspend fun getUserByEmail(email: String): User? {
        val query = usersCollection.whereEqualTo("correoElectronico", email)
            .limit(1)
            .get()
            .await()

        if (query.isEmpty) return null

        val user = query.documents.first().toObject(User::class.java)
        val uid = query.documents.first().id

        return user?.copy(idUser = uid)
    }

    /**
     * 🔥 Nueva función: Registrar usuario usando un objeto User
     * Esta es la que RegisterViewModel NECESITA.
     */
    suspend fun registerUser(user: User) {
        // 1) Crear usuario en FirebaseAuth
        val authResult = auth.createUserWithEmailAndPassword(
            user.correoElectronico,
            user.contrasenia
        ).await()

        val uid = authResult.user?.uid ?: throw Exception("No se obtuvo UID al registrar")

        // 2) Guardar en Firestore con el UID real
        val data = user.copy(idUser = uid)

        usersCollection.document(uid)
            .set(data)
            .await()
    }

    /**
     * Login normal
     */
    suspend fun loginUser(email: String, password: String): User? {

        val result = auth.signInWithEmailAndPassword(email, password).await()
        val uid = result.user?.uid ?: return null

        val snapshot = usersCollection.document(uid).get().await()

        val user = snapshot.toObject(User::class.java)
        return user?.copy(idUser = uid)
    }

    fun logout() {
        auth.signOut()
    }

    /**
     * Login anónimo
     */
    suspend fun signInAnonymously(): String {
        val result = auth.signInAnonymously().await()
        return result.user?.uid ?: throw Exception("No UID returned")
    }
}
