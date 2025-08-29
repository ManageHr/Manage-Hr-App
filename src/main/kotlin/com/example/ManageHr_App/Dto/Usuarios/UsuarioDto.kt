package com.example.ManageHr_App.Usuarios

import java.sql.Date

class UsuarioDto(
    private var numDocumento: Long,
    private var primerNombre: String,
    private var segundoNombre: String?,
    private var primerApellido: String,
    private var segundoApellido: String?,
    private var password: String,
    private var fechaNac: Date,
    private var numHijos: Byte?,
    private var contactoEmergencia: String,
    private var numContactoEmergencia: String,
    private var email: String,
    private var direccion: String,
    private var telefono: String,
    private var nacionalidadId: Int,
    private var epsCodigo: String,
    private var generoId: Int,
    private var tipoDocumentoId: Int,
    private var estadoCivilId: Int,
    private var pensionesCodigo: String,
    private var usersId: Long
) {
    fun getNumDocumento() = numDocumento
    fun setNumDocumento(value: Long) { numDocumento = value }

    fun getPrimerNombre() = primerNombre
    fun setPrimerNombre(value: String) { primerNombre = value }

    fun getSegundoNombre() = segundoNombre
    fun setSegundoNombre(value: String?) { segundoNombre = value }

    fun getPrimerApellido() = primerApellido
    fun setPrimerApellido(value: String) { primerApellido = value }

    fun getSegundoApellido() = segundoApellido
    fun setSegundoApellido(value: String?) { segundoApellido = value }

    fun getPassword() = password
    fun setPassword(value: String) { password = value }

    fun getFechaNac() = fechaNac
    fun setFechaNac(value: Date) { fechaNac = value }

    fun getNumHijos() = numHijos
    fun setNumHijos(value: Byte?) { numHijos = value }

    fun getContactoEmergencia() = contactoEmergencia
    fun setContactoEmergencia(value: String) { contactoEmergencia = value }

    fun getNumContactoEmergencia() = numContactoEmergencia
    fun setNumContactoEmergencia(value: String) { numContactoEmergencia = value }

    fun getEmail() = email
    fun setEmail(value: String) { email = value }

    fun getDireccion() = direccion
    fun setDireccion(value: String) { direccion = value }

    fun getTelefono() = telefono
    fun setTelefono(value: String) { telefono = value }

    fun getNacionalidadId() = nacionalidadId
    fun setNacionalidadId(value: Int) { nacionalidadId = value }

    fun getEpsCodigo() = epsCodigo
    fun setEpsCodigo(value: String) { epsCodigo = value }

    fun getGeneroId() = generoId
    fun setGeneroId(value: Int) { generoId = value }

    fun getTipoDocumentoId() = tipoDocumentoId
    fun setTipoDocumentoId(value: Int) { tipoDocumentoId = value }

    fun getEstadoCivilId() = estadoCivilId
    fun setEstadoCivilId(value: Int) { estadoCivilId = value }

    fun getPensionesCodigo() = pensionesCodigo
    fun setPensionesCodigo(value: String) { pensionesCodigo = value }

    fun getUsersId() = usersId
    fun setUsersId(value: Long) { usersId = value }
}
