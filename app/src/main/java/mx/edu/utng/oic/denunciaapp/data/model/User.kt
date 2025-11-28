package mx.edu.utng.oic.denunciaapp.data.model

// Importa esto para asegurar la compatibilidad con Firestore
// La anotación @DocumentId ha sido eliminada para evitar conflicto.
import java.util.UUID

// Enum para el rol de usuario, si es que lo tienes definido
enum class UserRole {
    // CORRECCIÓN: Renombrado a USUARIO para que coincida con el valor
    // que parece estar guardado en Firestore y solucionar el error de deserialización.
    USUARIO,
    USUARIO_ANONIMO
}

data class User(
    // CORRECCIÓN: Se elimina la anotación @DocumentId.
    // El ID se leerá como un campo normal dentro del documento, lo cual es compatible
    // con la forma en que tu UserService lo está guardando ("id" to uid).
    val idUser: String = UUID.randomUUID().toString(), // Valor por defecto para el ID

    val nombre: String = "",
    val correoElectronico: String = "",
    val contrasenia: String = "", // ADVERTENCIA: Solo para simulación de login.
    val sexo: String = "",
    val telefono: String = "",
    val fechaNacimiento: String = "",
    val descripcion: String = "",
    // CORRECCIÓN: Ajustado el valor por defecto al nuevo nombre del Enum.
    val rol: UserRole = UserRole.USUARIO,
    val respectPoints: Int = 0,
    val isAnonymus: Boolean = false
)
