package mx.edu.utng.oic.denunciaapp.data.model


data class User(
    val id: String? = null,
    val nombre: String,
    val sexo: String,
    val telefono: String,
    val fechaNacimiento: String,
    val correoElectronico: String,
    val descripcion: String,
    val rol: UserRole = UserRole.USUARIO,
    val respectPoints: Int = 0 ,
    val isAnonymus: Boolean = false,

    val contrasenia: String
)


enum class UserRole {
    ADMIN,
    MODERADOR,
    USUARIO
}