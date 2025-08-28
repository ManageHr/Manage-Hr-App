package com.example.ManageHr_App.Service.Vacantes

import com.managehr.managehr.Models.VacantesDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service
import kotlin.collections.firstOrNull
import kotlin.text.trimIndent

@Service
class VacantesService {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    private val mapper = RowMapper { rs, _ ->
        VacantesDto(
            rs.getLong("idVacantes"),
            rs.getString("nomVacante"),
            rs.getString("descripVacante"),
            rs.getDouble("salario"),
            rs.getString("expMinima"),
            rs.getString("cargoVacante"),
            rs.getLong("catVacId")
        )
    }

    fun listarVacantes(): List<VacantesDto> {
        val sql = """
            SELECT idVacantes, nomVacante, descripVacante, salario, 
                   expMinima, cargoVacante, catVacId
            FROM vacantes
        """.trimIndent()
        return jdbcTemplate.query(sql, mapper)
    }

    fun obtenerVacante(id: Long): VacantesDto? {
        val sql = """
            SELECT idVacantes, nomVacante, descripVacante, salario, 
                   expMinima, cargoVacante, catVacId
            FROM vacantes
            WHERE idVacantes = ?
        """.trimIndent()
        return jdbcTemplate.query(sql, mapper, id).firstOrNull()
    }

    fun crearVacante(dto: VacantesDto): Int {
        val sql = """
            INSERT INTO vacantes
                (nomVacante, descripVacante, salario, expMinima, cargoVacante, catVacId)
            VALUES (?, ?, ?, ?, ?, ?)
        """.trimIndent()
        return jdbcTemplate.update(
            sql,
            dto.getNomVacante(),
            dto.getDescripVacante(),
            dto.getSalario(),
            dto.getExpMinima(),
            dto.getCargoVacante(),
            dto.getCatVacId()
        )
    }

    fun actualizarVacante(id: Long, dto: VacantesDto): Int {
        val sql = """
            UPDATE vacantes
               SET nomVacante     = ?,
                   descripVacante = ?,
                   salario        = ?,
                   expMinima      = ?,
                   cargoVacante   = ?,
                   catVacId       = ?
             WHERE idVacantes     = ?
        """.trimIndent()
        return jdbcTemplate.update(
            sql,
            dto.getNomVacante(),
            dto.getDescripVacante(),
            dto.getSalario(),
            dto.getExpMinima(),
            dto.getCargoVacante(),
            dto.getCatVacId(),
            id
        )
    }

    fun eliminarVacante(id: Long): Int {
        val sql = "DELETE FROM vacantes WHERE idVacantes = ?"
        return jdbcTemplate.update(sql, id)
    }
}

