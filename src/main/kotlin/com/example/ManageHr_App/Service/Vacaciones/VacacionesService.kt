package com.example.ManageHr_App.Service

import com.example.ManageHr_App.Dto.Vacaciones.VacacionesDto
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Service
import java.sql.Statement

@Service
class VacacionesService(private val jdbcTemplate: JdbcTemplate) {

    // Nombre correcto de la columna
    private val COLUMN_CONTRATO = "contratoId"

    private val rowMapper = RowMapper { rs, _: Int ->
        VacacionesDto(
            idVacaciones = rs.getInt("idVacaciones"),
            motivo       = rs.getString("motivo"),
            fechaInicio  = rs.getDate("fechainicio"),
            fechaFinal   = rs.getDate("fechaFinal"),
            contratoId   = rs.getLong(COLUMN_CONTRATO),
            dias         = rs.getInt("dias"),
            estado       = rs.getString("estado")
        )
    }

    fun findAll(): List<VacacionesDto> {
        val sql = """
            SELECT idVacaciones, motivo, fechainicio, fechaFinal, $COLUMN_CONTRATO, dias, estado
            FROM vacaciones
            ORDER BY fechainicio DESC, idVacaciones DESC
        """.trimIndent()
        return jdbcTemplate.query(sql, rowMapper)
    }

    fun findById(id: Int): VacacionesDto? {
        val sql = """
            SELECT idVacaciones, motivo, fechainicio, fechaFinal, $COLUMN_CONTRATO, dias, estado
            FROM vacaciones
            WHERE idVacaciones = ?
        """.trimIndent()
        return try { jdbcTemplate.queryForObject(sql, rowMapper, id) } catch (_: Exception) { null }
    }

    fun create(dto: VacacionesDto): Int {
        val sql = """
            INSERT INTO vacaciones
                (motivo, fechainicio, fechaFinal, $COLUMN_CONTRATO, dias, estado)
            VALUES (?, ?, ?, ?, ?, COALESCE(?, 'Pendiente'))
        """.trimIndent()

        val keyHolder = GeneratedKeyHolder()

        jdbcTemplate.update({ conn ->
            val ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            ps.setString(1, dto.getMotivo())
            ps.setDate(2, java.sql.Date(dto.getFechaInicio().time))
            ps.setDate(3, java.sql.Date(dto.getFechaFinal().time))
            ps.setLong(4, dto.getContratoId())
            ps.setInt(5, dto.getDias())
            ps.setString(6, dto.getEstado())
            ps
        }, keyHolder)

        val id = keyHolder.key?.toInt() ?: 0
        if (id != 0) dto.setIdVacaciones(id)
        return id
    }

    fun update(id: Int, dto: VacacionesDto): Int {
        val sql = """
            UPDATE vacaciones
            SET motivo = ?,
                fechainicio = ?,
                fechaFinal = ?,
                $COLUMN_CONTRATO = ?,
                dias = ?,
                estado = ?
            WHERE idVacaciones = ?
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            dto.getMotivo(),
            java.sql.Date(dto.getFechaInicio().time),
            java.sql.Date(dto.getFechaFinal().time),
            dto.getContratoId(),
            dto.getDias(),
            dto.getEstado(),
            id
        )
    }

    fun delete(id: Int): Int {
        val sql = "DELETE FROM vacaciones WHERE idVacaciones = ?"
        return jdbcTemplate.update(sql, id)
    }
}
