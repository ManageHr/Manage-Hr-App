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
}