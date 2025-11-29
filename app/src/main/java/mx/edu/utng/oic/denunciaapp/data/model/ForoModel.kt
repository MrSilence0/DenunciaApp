package mx.edu.utng.oic.denunciaapp.data.model

import java.util.Date

/**
 * Modelo de datos para un Foro dentro de la aplicación.
 *
 * @property id Identificador único del foro.
 * @property tema Título o tema principal del foro (establecido por el usuario).
 * @property username Nombre del usuario creador del foro.
 * @property creationDate Fecha y hora de creación del foro.
 * @property responseCount Número actual de respuestas (mensajes) que tiene el foro.
 */
data class Foro(
    val id: String,
    val tema: String,
    val username: String, // Asumimos que se obtiene del usuario autenticado
    val creationDate: Date,
    val responseCount: Int = 0, // Inicialmente 0
    val idUser: String
)