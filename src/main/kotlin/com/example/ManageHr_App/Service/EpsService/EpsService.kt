package com.example.ManageHr_App.Service.EpsService

import com.example.ManageHr_App.Dto.Eps.EpsDto
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Service
import java.sql.Statement

@Service
class EpsService(private val jdbcTemplate : JdbcTemplate) {

    private val rowMapper = RowMapper {rs, _: Int ->
        EpsDto(
            codigoEps = rs.getString("codigoEps"),
            nombreEps = rs.getString("nombreEps")
        )
    }
    fun finAll():List<EpsDto>{
        val sql = """
               SELECT codigoEps, nombreEps
               FROM `eps`
               """.trimIndent()
        return jdbcTemplate.query(sql, rowMapper)
    }

    fun finById(id:String): EpsDto? {
        val sql = """
            SELECT codigoEps, nombreEps
            FROM `eps`
            WHERE id = ?
        """.trimIndent()
        return try {
            jdbcTemplate.queryForObject(sql, rowMapper, id)

        }catch(_: Exception) {
            null
        }
    }
    fun create(dto: EpsDto):Int{
        val sql ="""
           INSERT INTO `eps`
                (codigoEps,nombreEps)
           VALUES(?,?)
        """.trimIndent()
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update({ conn ->
            val ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)
            ps.setString(1,dto.getCodigoEps())
            ps.setString(2,dto.getNombreEps())
            ps
        }, keyHolder )
        val id = keyHolder.key?.toInt()?: 0
        return id
    }

    fun update(id:String, dto: EpsDto): Int {
        val sql = """
            UPDATE `eps`
            SET nombreEps = ?,
            WHERE codigoEps = ?
        """.trimIndent()
        return jdbcTemplate.update (
            sql,
                dto.getNombreEps(),
            id
        )
    }

    fun delete(id:Int): Int {
        val sql = """
            DELETE FORM `eps`
            WHERE id = ?
        """.trimIndent()
        return jdbcTemplate.update(sql, id)
    }

}