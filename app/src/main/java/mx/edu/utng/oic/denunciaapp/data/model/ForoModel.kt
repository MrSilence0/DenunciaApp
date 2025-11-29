package mx.edu.utng.oic.denunciaapp.data.model

import java.util.Date

/**
 * Modelo de datos para un Foro dentro de la aplicación.
 *
 * NOTA DE CORRECCIÓN: Se añaden valores por defecto a TODOS los campos
 * para asegurar la correcta deserialización desde Firestore, incluso
 * si algún campo falta o es nulo.
 *
 * @property id Identificador único del foro.
 * @property tema Título o tema principal del foro (establecido por el usuario).
 * @property username Nombre del usuario creador del foro.
 * @property creationDate Fecha y hora de creación del foro.
 * @property responseCount Número actual de respuestas (mensajes) que tiene el foro.
 * @property idUser Identificador del usuario creador (para verificación de propiedad).
 */
data class Foro(
    val id: String = "",
    val tema: String = "",
    val username: String = "",
    val creationDate: Date = Date(),
    val responseCount: Int = 0,
    val idUser: String = ""
)