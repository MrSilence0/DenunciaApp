package mx.edu.utng.oic.denunciaapp.data.model

import java.util.Date

enum class TipoIncidente(val id: Int) {
    DENUNCIA_FOTOGRAFICA(1),
    PERSONA_DESAPARECIDA(2),
    ROBO_VEHICULO(3),
    EXTORSION(4),
    ROBO_CASA(5),
    ROBO_OBJETO(6),
    DENUNCIA_VIOLENCIA(7)
}


sealed class Denuncia {
    abstract val id: String
    abstract val tipo: TipoIncidente
    abstract val status: String
    abstract val idUser: String
    abstract val creationDate: Date
}


/**
 * 1. Denuncia Fotográfica (Descripción y Ubicación)
 */
data class DenunciaFotografica(
    override val id: String,
    override val status: String,
    override val idUser: String,
    override val creationDate: Date,
    val descripcion: String,
    val locationAddress: String? = null,
    val latitud: Double? = null,
    val longitud: Double? = null,
    override val tipo: TipoIncidente = TipoIncidente.DENUNCIA_FOTOGRAFICA
) : Denuncia()


/**
 * 2. Persona Desaparecida (Datos personales, sin localización)
 */
data class PersonaDesaparecida(
    override val id: String,
    override val status: String,
    override val idUser: String,
    override val creationDate: Date,
    val nombreDesaparecido: String,
    val sexo: String,
    val descripcionFisica: String,
    val vestimenta: String,
    val edad: Int,
    override val tipo: TipoIncidente = TipoIncidente.PERSONA_DESAPARECIDA
) : Denuncia()


/**
 * 3. Robo de Vehículo (Datos del vehículo, sin descripción ni localización general)
 */
data class RoboVehiculo(
    override val id: String,
    override val status: String,
    override val idUser: String,
    override val creationDate: Date,
    val placas: String,
    val numeroSerie: String,
    val marca: String,
    val color: String,
    val anio: Int,
    val nombreReportante: String,
    override val tipo: TipoIncidente = TipoIncidente.ROBO_VEHICULO
) : Denuncia()


/**
 * 4. Extorsión (Número telefónico y Descripción)
 */
data class Extorsion(
    override val id: String,
    override val status: String,
    override val idUser: String,
    override val creationDate: Date,
    val numeroTelefonico: String,
    val descripcion: String,
    override val tipo: TipoIncidente = TipoIncidente.EXTORSION
) : Denuncia()


/**
 * 5. Robo a Casa (Descripción, Ubicación y Teléfono de contacto)
 */
data class RoboCasa(
    override val id: String,
    override val status: String,
    override val idUser: String,
    override val creationDate: Date,
    val descripcion: String,
    val locationAddress: String? = null,
    val latitud: Double? = null,
    val longitud: Double? = null,
    val telefonoContacto: String,
    override val tipo: TipoIncidente = TipoIncidente.ROBO_CASA
) : Denuncia()


/**
 * 6. Robo de Objeto (Detalles del objeto y Localización)
 */
data class RoboObjeto(
    override val id: String,
    override val status: String,
    override val idUser: String,
    override val creationDate: Date,
    val tipoObjeto: String,
    val marca: String,
    val estado: String, // Ej: "Nuevo", "Usado", "Dañado"
    val color: String,
    val anio: Int? = null, // Año opcional del objeto
    val locationAddress: String? = null,
    val latitud: Double? = null,
    val longitud: Double? = null,
    override val tipo: TipoIncidente = TipoIncidente.ROBO_OBJETO
) : Denuncia()


/**
 * 7. Denuncia de Violencia (Descripción, Ubicación, Tipo de Conducta y Teléfono)
 */
data class DenunciaViolencia(
    override val id: String,
    override val status: String,
    override val idUser: String,
    override val creationDate: Date,
    val descripcion: String,
    val tipoConducta: String,
    val telefonoContacto: String,
    val locationAddress: String? = null,
    val latitud: Double? = null,
    val longitud: Double? = null,
    override val tipo: TipoIncidente = TipoIncidente.DENUNCIA_VIOLENCIA
) : Denuncia()
