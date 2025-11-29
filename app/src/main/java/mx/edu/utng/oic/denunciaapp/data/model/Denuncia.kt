package mx.edu.utng.oic.denunciaapp.data.model

import java.util.Date

const val DENUNCIA_TYPE_FIELD = "denunciaClassType"

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
    abstract val idUser: String
    abstract val creationDate: Date
    abstract val denunciaClassType: String
}


/**
 * 1. Denuncia Fotográfica
 */
data class DenunciaFotografica(
    override val id: String = "",
    override val idUser: String = "",
    override val creationDate: Date = Date(),
    val descripcion: String = "",
    val locationAddress: String? = null,
    val latitud: Double? = null,
    val longitud: Double? = null,
    override val tipo: TipoIncidente = TipoIncidente.DENUNCIA_FOTOGRAFICA,
    override val denunciaClassType: String = "DenunciaFotografica"
) : Denuncia()


/**
 * 2. Persona Desaparecida
 */
data class PersonaDesaparecida(
    override val id: String = "",
    override val idUser: String = "",
    override val creationDate: Date = Date(),
    val nombreDesaparecido: String = "",
    val sexo: String = "",
    val descripcionFisica: String = "",
    val vestimenta: String = "",
    val edad: Int = 0,
    override val tipo: TipoIncidente = TipoIncidente.PERSONA_DESAPARECIDA,
    override val denunciaClassType: String = "PersonaDesaparecida"
) : Denuncia()


/**
 * 3. Robo de Vehículo
 */
data class RoboVehiculo(
    override val id: String = "",
    override val idUser: String = "",
    override val creationDate: Date = Date(),
    val placas: String = "",
    val numeroSerie: String = "",
    val marca: String = "",
    val color: String = "",
    val anio: Int = 0,
    val nombreReportante: String = "",
    override val tipo: TipoIncidente = TipoIncidente.ROBO_VEHICULO,
    override val denunciaClassType: String = "RoboVehiculo"
) : Denuncia()


/**
 * 4. Extorsión
 */
data class Extorsion(
    override val id: String = "",
    override val idUser: String = "",
    override val creationDate: Date = Date(),
    val numeroTelefonico: String = "",
    val descripcion: String = "",
    override val tipo: TipoIncidente = TipoIncidente.EXTORSION,
    override val denunciaClassType: String = "Extorsion"
) : Denuncia()


/**
 * 5. Robo a Casa
 */
data class RoboCasa(
    override val id: String = "",
    override val idUser: String = "",
    override val creationDate: Date = Date(),
    val descripcion: String = "",
    val locationAddress: String? = null,
    val latitud: Double? = null,
    val longitud: Double? = null,
    val telefonoContacto: String = "",
    override val tipo: TipoIncidente = TipoIncidente.ROBO_CASA,
    override val denunciaClassType: String = "RoboCasa"
) : Denuncia()


/**
 * 6. Robo de Objeto
 */
data class RoboObjeto(
    override val id: String = "",
    override val idUser: String = "",
    override val creationDate: Date = Date(),
    val tipoObjeto: String = "",
    val marca: String = "",
    val estado: String = "",
    val color: String = "",
    val anio: Int? = null,
    val locationAddress: String? = null,
    val latitud: Double? = null,
    val longitud: Double? = null,
    override val tipo: TipoIncidente = TipoIncidente.ROBO_OBJETO,
    override val denunciaClassType: String = "RoboObjeto"
) : Denuncia()


/**
 * 7. Denuncia de Violencia (Descripción, Ubicación, Tipo de Conducta y Teléfono)
 */
data class DenunciaViolencia(
    override val id: String = "",
    override val idUser: String = "",
    override val creationDate: Date = Date(),
    val descripcion: String = "",
    val tipoConducta: String = "",
    val telefonoContacto: String = "",
    val locationAddress: String? = null,
    val latitud: Double? = null,
    val longitud: Double? = null,
    override val tipo: TipoIncidente = TipoIncidente.DENUNCIA_VIOLENCIA,
    override val denunciaClassType: String = "DenunciaViolencia"
) : Denuncia()