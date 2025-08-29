package com.example.ManageHr_App.Usuarios

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.sql.ResultSet

@Service
class UsuariosServices {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate


    private fun mapRow(rs: ResultSet): UsuarioDto {
        return UsuarioDto(
            numDocumento = rs.getLong("numDocumento"),
            primerNombre = rs.getString("primerNombre"),
            segundoNombre = rs.getString("segundoNombre"),
            primerApellido = rs.getString("primerApellido"),
            segundoApellido = rs.getString("segundoApellido"),
            password = rs.getString("password"),
            fechaNac = rs.getDate("fechaNac"),
            numHijos = rs.getObject("numHijos")?.toString()?.toByte(),
            contactoEmergencia = rs.getString("contactoEmergencia"),
            numContactoEmergencia = rs.getString("numContactoEmergencia"),
            email = rs.getString("email"),
            direccion = rs.getString("direccion"),
            telefono = rs.getString("telefono"),
            nacionalidadId = rs.getInt("nacionalidadId"),
            epsCodigo = rs.getString("epsCodigo"),
            generoId = rs.getInt("generoId"),
            tipoDocumentoId = rs.getInt("tipoDocumentoId"),
            estadoCivilId = rs.getInt("estadoCivilId"),
            pensionesCodigo = rs.getString("pensionesCodigo"),
            usersId = rs.getLong("usersId")
        )
    }

    fun obtenerUsuarios(): List<UsuarioDto> {
        val sql = "SELECT * FROM usuarios"
        return jdbcTemplate.query(sql) { rs, _ -> mapRow(rs) }
    }

    fun obtenerUsuarioPorId(id: Long): UsuarioDto? {
        val sql = "SELECT * FROM usuarios WHERE numDocumento = ?"
        return jdbcTemplate.query(sql, arrayOf(id)) { rs, _ -> mapRow(rs) }
            .firstOrNull()
    }

    fun crearUsuario(usuario: UsuarioDto): Int {
        val sql = """
            INSERT INTO usuarios 
            (numDocumento, primerNombre, segundoNombre, primerApellido, segundoApellido, password, fechaNac, numHijos,
            contactoEmergencia, numContactoEmergencia, email, direccion, telefono, nacionalidadId,
            epsCodigo, generoId, tipoDocumentoId, estadoCivilId, pensionesCodigo, usersId)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """
        return jdbcTemplate.update(
            sql,
            usuario.getNumDocumento(),
            usuario.getPrimerNombre(),
            usuario.getSegundoNombre(),
            usuario.getPrimerApellido(),
            usuario.getSegundoApellido(),
            usuario.getPassword(),
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
            usuario.getUsersId()
        )
    }

    fun actualizarUsuario(id: Long, usuario: UsuarioDto): Int {
        val sql = """
            UPDATE usuarios SET primerNombre=?, segundoNombre=?, primerApellido=?, segundoApellido=?,
            password=?, fechaNac=?, numHijos=?, contactoEmergencia=?, numContactoEmergencia=?, email=?,
            direccion=?, telefono=?, nacionalidadId=?, epsCodigo=?, generoId=?, tipoDocumentoId=?,
            estadoCivilId=?, pensionesCodigo=?, usersId=? WHERE numDocumento=?
        """
        return jdbcTemplate.update(
            sql,
            usuario.getPrimerNombre(),
            usuario.getSegundoNombre(),
            usuario.getPrimerApellido(),
            usuario.getSegundoApellido(),
            usuario.getPassword(),
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
            usuario.getUsersId(),
            id
        )
    }

    fun eliminarUsuario(id: Long): Int {
        val sql = "DELETE FROM usuarios WHERE numDocumento = ?"
        return jdbcTemplate.update(sql, id)
    }
}
