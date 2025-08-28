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

    // Listar todo
    fun findAll(): List<HorasExtraDto> {
        val sql = """
            SELECT idHorasExtra, descripcion, fecha, nHorasExtra, tipoHorasid, contratoId
            FROM `horasextra`   
            ORDER BY fecha DESC
        """.trimIndent()
        return jdbcTemplate.query(sql, rowMapper)
    }

    // Buscar por id (null si no existe)
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

    // Crear (retorna el id generado y lo setea en el DTO)
    fun create(dto: HorasExtraDto): Int {
        val sql = """
            INSERT INTO `horasextra`
                (descripcion, fecha, nHorasExtra, tipoHorasid, contratoId)
            VALUES (?, ?, ?, ?, ?)
        """.trimIndent()

        val keyHolder = GeneratedKeyHolder()

        jdbcTemplate.update({ conn ->
            val ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            ps.setString(1, dto.getDescripcion())
            ps.setTimestamp(2, Timestamp(dto.getFecha().time))
            ps.setInt(3, dto.getNHorasExtra())
            ps.setLong(4, dto.getTipoHorasId())
            ps.setLong(5, dto.getContratoId())
            ps
        }, keyHolder)

        val id = keyHolder.key?.toInt() ?: 0
        if (id != 0) dto.setIdHorasExtra(id)
        return id
    }

    // Actualizar por id (retorna filas afectadas)
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

    // Borrar por id (retorna filas afectadas)
    fun delete(id: Int): Int {
        val sql = "DELETE FROM `horasextra` WHERE idHorasExtra = ?"
        return jdbcTemplate.update(sql, id)
    }
}
