package com.example.ManageHr_App.Service

import com.example.ManageHr_App.Dto.Eps.EpsDto
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service

@Service
class EpsService(private val jdbcTemplate: JdbcTemplate) {

    private val rowMapper = RowMapper { rs, _ ->
        EpsDto(
            codigoEps = rs.getString("codigoEps"),
            nombreEps = rs.getString("nombreEps")
        )
    }

    fun getAll(): List<EpsDto> {
        val sql = """
            SELECT codigoEps, nombreEps
            FROM eps
        """.trimIndent()
        return jdbcTemplate.query(sql, rowMapper)
    }

    fun getById(codigo: String): EpsDto? {
        val sql = "SELECT codigoEps, nombreEps FROM eps WHERE codigoEps = ?"
        return try {
            jdbcTemplate.queryForObject(sql, rowMapper, codigo)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }
}