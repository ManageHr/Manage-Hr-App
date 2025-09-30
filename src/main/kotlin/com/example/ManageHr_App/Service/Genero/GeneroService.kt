package com.example.ManageHr_App.Service

import com.example.ManageHr_App.Dto.Genero.GeneroDto
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service

@Service
class GeneroService(private val jdbcTemplate: JdbcTemplate) {

    private val rowMapper = RowMapper { rs, _ ->
        GeneroDto(
            idGenero = rs.getInt("idGenero"),
            nombreGenero = rs.getString("nombreGenero"),
            abreviacionGenero = rs.getString("abreviacionGenero")
        )
    }

    fun getAll(): List<GeneroDto> {
        val sql = """
            SELECT idGenero, nombreGenero, abreviacionGenero
            FROM genero
        """.trimIndent()
        return jdbcTemplate.query(sql, rowMapper)
    }

    fun getById(id: Int): GeneroDto? {
        val sql = "SELECT idGenero, nombreGenero, abreviacionGenero FROM genero WHERE idGenero = ?"
        return try {
            jdbcTemplate.queryForObject(sql, rowMapper, id)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }
}