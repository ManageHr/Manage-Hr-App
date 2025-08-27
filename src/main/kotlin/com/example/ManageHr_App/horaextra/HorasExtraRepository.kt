package com.example.ManageHr_App.horaextra


import com.example.ManageHr_App.horaextra.dto.CreateHorasExtraRequest
import com.example.ManageHr_App.horaextra.dto.UpdateHorasExtraRequest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement
import java.sql.Statement

@Repository
class HorasExtraRepository(private val jdbc: JdbcTemplate) {

    private val mapper = RowMapper { rs, _ ->
        HorasExtraModel(
            idHorasExtra = rs.getLong("idHorasExtra"),
            descrip      = rs.getString("descrip"),
            fecha        = rs.getDate("fecha").toLocalDate(),
            nHorasExtra  = rs.getInt("nHorasExtra"),
            tipoHorasId  = rs.getLong("tipoHorasid"),
            contratoId   = rs.getLong("contratoId")
        )
    }

    fun findAll(): List<HorasExtraModel> =
        jdbc.query("SELECT * FROM horasextra ORDER BY fecha DESC, idHorasExtra DESC", mapper)

    fun findById(id: Long): HorasExtraModel? =
        jdbc.query("SELECT * FROM horasextra WHERE idHorasExtra = ?", mapper, id)
            .firstOrNull()

    fun create(req: CreateHorasExtraRequest): Long {
        val sql = """
            INSERT INTO horasextra (descrip, fecha, nHorasExtra, tipoHorasid, contratoId)
            VALUES (?, ?, ?, ?, ?)
        """.trimIndent()

        val kh: KeyHolder = GeneratedKeyHolder()
        jdbc.update({ con ->
            val ps: PreparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            ps.setString(1, req.descrip)
            ps.setDate(2, java.sql.Date.valueOf(req.fecha))
            ps.setInt(3, req.nHorasExtra)
            ps.setLong(4, req.tipoHorasId)
            ps.setLong(5, req.contratoId)
            ps
        }, kh)

        return kh.keys?.get("GENERATED_KEY")?.toString()?.toLong()
            ?: kh.key?.toLong()
            ?: error("No se recibió ID generado")
    }

    fun update(id: Long, req: UpdateHorasExtraRequest): Int {
        val sql = """
            UPDATE horasextra
               SET descrip = ?, fecha = ?, nHorasExtra = ?, tipoHorasid = ?, contratoId = ?
             WHERE idHorasExtra = ?
        """.trimIndent()
        return jdbc.update(
            sql,
            req.descrip,
            java.sql.Date.valueOf(req.fecha),
            req.nHorasExtra,
            req.tipoHorasId,
            req.contratoId,
            id
        )
    }

    fun delete(id: Long): Int =
        jdbc.update("DELETE FROM horasextra WHERE idHorasExtra = ?", id)
}