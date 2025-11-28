package mx.edu.utng.oic.denunciaapp.data.model

import java.util.UUID

enum class UserRole {
    USUARIO,
    USUARIO_ANONIMO
}

data class User(
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
