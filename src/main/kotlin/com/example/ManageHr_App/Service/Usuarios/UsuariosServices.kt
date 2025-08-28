package com.example.ManageHr_App.Usuarios

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class UsuariosServices {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate


    fun obtenerUsuarios(): List<UsuarioDto> {
        val sql = "SELECT * FROM usuarios"
        return jdbcTemplate.query(sql) { rs, _ ->
            UsuarioDto(
                numDocumento = rs.getLong("numDocumento"),
                primerNombre = rs.getString("primerNombre"),
                segundoNombre = rs.getString("segundoNombre"),
                primerApellido = rs.getString("primerApellido"),
                segundoApellido = rs.getString("segundoApellido"),
                fechaNac = rs.getDate("fechaNac"),
                numHijos = rs.getObject("numHijos") as? Byte,
                contactoEmergencia = rs.getString("contactoEmergencia"),
                numContactoEmergencia = rs.getString("numContactoEmergencia"),
                email = rs.getString("email"),
                direccion = rs.getString("direccion"),
                telefono = rs.getString("telefono"),
                nacionalidadId = rs.getObject("nacionalidadId") as? Int,
                epsCodigo = rs.getString("epsCodigo"),
                generoId = rs.getObject("generoId") as? Int,
                tipoDocumentoId = rs.getObject("tipoDocumentoId") as? Int,
                estadoCivilId = rs.getObject("estadoCivilId") as? Int,
                pensionesCodigo = rs.getString("pensionesCodigo")
            )
        }
    }


    fun obtenerUsuarioPorId(id: Long): UsuarioDto? {
        val sql = "SELECT * FROM usuarios WHERE numDocumento = ?"
        return jdbcTemplate.query(sql, arrayOf(id)) { rs, _ ->
            UsuarioDto(
                numDocumento = rs.getLong("numDocumento"),
                primerNombre = rs.getString("primerNombre"),
                segundoNombre = rs.getString("segundoNombre"),
                primerApellido = rs.getString("primerApellido"),
                segundoApellido = rs.getString("segundoApellido"),
                fechaNac = rs.getDate("fechaNac"),
                numHijos = rs.getObject("numHijos") as? Byte,
                contactoEmergencia = rs.getString("contactoEmergencia"),
                numContactoEmergencia = rs.getString("numContactoEmergencia"),
                email = rs.getString("email"),
                direccion = rs.getString("direccion"),
                telefono = rs.getString("telefono"),
                nacionalidadId = rs.getObject("nacionalidadId") as? Int,
                epsCodigo = rs.getString("epsCodigo"),
                generoId = rs.getObject("generoId") as? Int,
                tipoDocumentoId = rs.getObject("tipoDocumentoId") as? Int,
                estadoCivilId = rs.getObject("estadoCivilId") as? Int,
                pensionesCodigo = rs.getString("pensionesCodigo")
            )
        }.firstOrNull()
    }


    fun crearUsuario(usuario: UsuarioDto): Int {
        val sql = """
            INSERT INTO usuarios 
            (numDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido, fechaNac, numHijos, 
            contactoEmergencia, numContactoEmergencia, email, direccion, telefono, nacionalidadId, 
            epsCodigo, generoId, tipoDocumentoId, estadoCivilId, pensionesCodigo)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """
        return jdbcTemplate.update(
            sql,
            usuario.getNumdocumento(),
            usuario.getPrimerNombre(),
            usuario.getSegundoNombre(),
            usuario.getPrimerApellido(),
            usuario.getSegundoApellido(),
            usuario.getFechaNac(),
            usuario.getNumHijos(),
            usuario.getContactoEmergencia(),
            usuario.getNumContactoEmergencia(),
            usuario.getEmail(),
            usuario.getDireccion(),
            usuario.getTelefono(),
            usuario.getNacionalidadId(),
            usuario.getEpsCodigo(),
            usuario.getGeneroId(),
            usuario.getTipoDocumentoId(),
            usuario.getEstadoCivilId(),
            usuario.getPensionesCodigo()
        )
    }


    fun actualizarUsuario(id: Long, usuario: UsuarioDto): Int {
        val sql = """
            UPDATE usuarios SET primerNombre=?, segundoNombre=?, primerApellido=?, segundoApellido=?, 
            fechaNac=?, numHijos=?, contactoEmergencia=?, numContactoEmergencia=?, email=?, direccion=?, 
            telefono=?, nacionalidadId=?, epsCodigo=?, generoId=?, tipoDocumentoId=?, estadoCivilId=?, 
            pensionesCodigo=? WHERE numDocumento=?
        """
        return jdbcTemplate.update(
            sql,
            usuario.getPrimerNombre(),
            usuario.getSegundoNombre(),
            usuario.getPrimerApellido(),
            usuario.getSegundoApellido(),
            usuario.getFechaNac(),
            usuario.getNumHijos(),
            usuario.getContactoEmergencia(),
            usuario.getNumContactoEmergencia(),
            usuario.getEmail(),
            usuario.getDireccion(),
            usuario.getTelefono(),
            usuario.getNacionalidadId(),
            usuario.getEpsCodigo(),
            usuario.getGeneroId(),
            usuario.getTipoDocumentoId(),
            usuario.getEstadoCivilId(),
            usuario.getPensionesCodigo(),
            id
        )
    }


    fun eliminarUsuario(id: Long): Int {
        val sql = "DELETE FROM usuarios WHERE numDocumento = ?"
        return jdbcTemplate.update(sql, id)
    }
}