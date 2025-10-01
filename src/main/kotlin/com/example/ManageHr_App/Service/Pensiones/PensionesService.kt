package com.example.ManageHr_App.Service.Pensiones

import com.example.ManageHr_App.Dto.Pensiones.PensionesDto
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service

@Service
class PensionesService(private val jdbcTemplate: JdbcTemplate) {

    private val rowMapper = RowMapper { rs, _ ->
        PensionesDto(
            codigoPensiones = rs.getString("codigoPensiones"),
            nombrePensiones = rs.getString("nombrePensiones")
        )
    }

    fun getAll(): List<PensionesDto> {
        val sql = """
            SELECT codigoPensiones, nombrePensiones
            FROM pensiones
        """.trimIndent()
        return jdbcTemplate.query(sql, rowMapper)
    }

    fun getByCodigo(codigo: String): PensionesDto? {
        val sql = "SELECT codigoPensiones, nombrePensiones FROM pensiones WHERE codigoPensiones = ?"
        return try {
            jdbcTemplate.queryForObject(sql, rowMapper, codigo)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }
}