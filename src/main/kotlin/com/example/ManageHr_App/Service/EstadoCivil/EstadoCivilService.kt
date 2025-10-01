package com.example.ManageHr_App.Service.EstadoCivil

import com.example.ManageHr_App.Dto.EstadoCivil.EstadoCivilDto
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service

@Service
class EstadoCivilService(private val jdbcTemplate: JdbcTemplate) {

    private val rowMapper = RowMapper { rs, _ ->
        EstadoCivilDto(
            idEstadoCivil = rs.getInt("idEstadoCivil"),
            nombreEstado = rs.getString("nombreEstado")
        )
    }

    fun getAll(): List<EstadoCivilDto> {
        val sql = """
            SELECT idEstadoCivil, nombreEstado
            FROM estadocivil
        """.trimIndent()
        return jdbcTemplate.query(sql, rowMapper)
    }

    fun getById(id: Int): EstadoCivilDto? {
        val sql = "SELECT idEstadoCivil, nombreEstado FROM estadocivil WHERE idEstadoCivil = ?"
        return try {
            jdbcTemplate.queryForObject(sql, rowMapper, id)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }
}