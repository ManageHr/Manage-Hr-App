package com.example.ManageHr_App.Service

import com.example.ManageHr_App.Dto.HorasExtra.HorasExtraDto
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Service
import java.sql.Statement
import java.sql.Timestamp

@Service
class HorasExtraService(private val jdbcTemplate: JdbcTemplate) {

    private val rowMapper = RowMapper { rs, _: Int ->
        HorasExtraDto(
            idHorasExtra   = rs.getInt("idHorasExtra"),
            descripcion    = rs.getString("descripcion"),
            fecha          = rs.getTimestamp("fecha"),
            nHorasExtra    = rs.getInt("nHorasExtra"),
            tipoHorasId = rs.getLong("tipoHorasId"),
            contratoId     = rs.getLong("contratoId")
        )
    }

    fun findAll(): List<HorasExtraDto> {
        val sql = """
            SELECT idHorasExtra, descripcion, fecha, nHorasExtra, tipoHorasid, contratoId
            FROM `horasextra`   
            ORDER BY fecha DESC
        """.trimIndent()
        return jdbcTemplate.query(sql, rowMapper)
    }

    fun findById(id: Int): HorasExtraDto? {
        val sql = """
            SELECT idHorasExtra, descripcion, fecha, nHorasExtra, tipoHorasid, contratoId
            FROM `horasextra`
            WHERE idHorasExtra = ?
        """.trimIndent()
        return try {
            jdbcTemplate.queryForObject(sql, rowMapper, id)
        } catch (_: Exception) {
            null
        }
    }

    fun create(dto: HorasExtraDto): Int {
        val sql = """
        INSERT INTO horasextra (descripcion, fecha, nHorasExtra, tipoHorasid, contratoId)
        VALUES (?, ?, ?, ?, ?)
    """.trimIndent()

        return jdbcTemplate.update(sql,
            dto.getDescripcion(),
            Timestamp(dto.getFecha().time),
            dto.getNHorasExtra(),
            dto.getTipoHorasId(),
            dto.getContratoId()
        )
    }
    fun update(id: Int, dto: HorasExtraDto): Int {
        val sql = """
            UPDATE `horasextra`
            SET descripcion = ?,
                fecha = ?,
                nHorasExtra = ?,
                tipoHorasid = ?,
                contratoId = ?
            WHERE idHorasExtra = ?
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            dto.getDescripcion(),
            Timestamp(dto.getFecha().time),
            dto.getNHorasExtra(),
            dto.getTipoHorasId(),
            dto.getContratoId(),
            id
        )
    }

    fun delete(id: Int): Int {
        val sql = "DELETE FROM `horasextra` WHERE idHorasExtra = ?"
        return jdbcTemplate.update(sql, id)
    }
}
