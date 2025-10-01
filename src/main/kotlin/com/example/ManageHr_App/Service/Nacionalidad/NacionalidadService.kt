package com.example.ManageHr_App.Service.Nacionalidad

import com.example.ManageHr_App.Dto.Nacionalidad.NacionalidadDto
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service

@Service
class NacionalidadService(private val jdbcTemplate: JdbcTemplate)  {

    private val rowMapper = RowMapper { rs, _: Int ->
        NacionalidadDto(
            idNacionalidad   = rs.getInt("idNacionalidad"),
            codigo    = rs.getString("codigo"),
            nombre         = rs.getString("nombre")
        )
    }
    fun getAll(): List<NacionalidadDto> {
        val sql = """
            SELECT *
            FROM nacionalidad   
        """.trimIndent()
        return jdbcTemplate.query(sql, rowMapper)
    }
    fun getById(id:Int): NacionalidadDto?{
        val sql="SELECT * FROM nacionalidad WHERE idNacionalidad=?"
        return try{
            jdbcTemplate.queryForObject(sql,rowMapper,id)
        }catch (e: EmptyResultDataAccessException){
            null
        }
    }
}