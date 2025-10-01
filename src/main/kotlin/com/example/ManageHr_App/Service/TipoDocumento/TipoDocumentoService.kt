package com.example.ManageHr_App.Service.TipoDocumento

import com.example.ManageHr_App.Dto.TipoDocumento.TipoDocumentoDto
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service

@Service
class TipoDocumentoService(private val jdbcTemplate: JdbcTemplate) {

    private val rowMapper = RowMapper { rs, _ ->
        TipoDocumentoDto(
            idTipoDocumento = rs.getInt("idTipoDocumento"),
            nombreTipoDocumento = rs.getString("nombreTipoDocumento"),
            abreviacionTipoDocumento = rs.getString("abreviacionTipoDocumento")
        )
    }

    fun getAll(): List<TipoDocumentoDto> {
        val sql = """
            SELECT idTipoDocumento, nombreTipoDocumento, abreviacionTipoDocumento
            FROM tipodocumento
        """.trimIndent()
        return jdbcTemplate.query(sql, rowMapper)
    }

    fun getById(id: Int): TipoDocumentoDto? {
        val sql = "SELECT idTipoDocumento, nombreTipoDocumento, abreviacionTipoDocumento FROM tipodocumento WHERE idTipoDocumento = ?"
        return try {
            jdbcTemplate.queryForObject(sql, rowMapper, id)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }
}