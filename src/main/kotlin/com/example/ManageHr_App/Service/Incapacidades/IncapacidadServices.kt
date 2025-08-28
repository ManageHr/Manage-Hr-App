package com.example.ManageHr_App.Service.Incapacidades

import com.example.ManageHr_App.Dto.Incapacidades.IncapacidadDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Service

@Service
class IncapacidadServices {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate
    private val rowMapper = RowMapper { rs, _: Int ->
        IncapacidadDto(
            idIncapacidad = rs.getLong("idIncapacidad"),
            archivo = rs.getString("archivo"),
            estado = rs.getInt("estado"),
            fechaInicio = rs.getDate("fechaInicio").toLocalDate(),
            fechaFinal = rs.getDate("fechaFinal").toLocalDate(),
            contratoId = rs.getLong("contratoId")
        )}
    fun getAll():List<IncapacidadDto>{
        var sql="SELECT * FROM incapacidad"
        return jdbcTemplate.query(sql,rowMapper)
    }

    fun getById(id:Long):IncapacidadDto?{
        var sql="SELECT * FROM incapacidad where idIncapacidad = ?"
        return try {
            jdbcTemplate.queryForObject(sql,rowMapper,id)
        }catch (_: Exception ){
            null
        }


    }
    fun getByEstado(id:Int):List<IncapacidadDto>{
        var sql="SELECT * FROM incapacidad where estado = ?"
        return jdbcTemplate.query(sql,rowMapper,id)
    }
    fun update(id:Long,incapacidadDto: IncapacidadDto):String{
        var sql = "UPDATE incapacidad set archivo=?,estado=?,fechaInicio=?,fechaFinal=?,contratoId=? Where idIncapacidad=?"
        val filas= jdbcTemplate.update(sql,incapacidadDto.getArchivo(),incapacidadDto.getEstado(),java.sql.Date.valueOf(incapacidadDto.getFechaInicio()),java.sql.Date.valueOf(incapacidadDto.getFechaFinal()),incapacidadDto.getContratoId(),id)
        return if (filas>0) "Incapacidad Modificada con exito" else "Error al modificar la incapacidad"
    }
    fun create(incapacidadDto: IncapacidadDto):String{
        var sql = "INSERT INTO incapacidad (archivo,estado,fechaInicio,fechaFinal,contratoId)values(?, ?, ?, ?, ?)"
        val filas= jdbcTemplate.update(sql,incapacidadDto.getArchivo(),incapacidadDto.getEstado(),java.sql.Date.valueOf(incapacidadDto.getFechaInicio()),java.sql.Date.valueOf(incapacidadDto.getFechaFinal()),incapacidadDto.getContratoId())
        return if (filas>0) "Incapacidad Creada con exito" else "Error al Crear la incapacidad"
    }
    fun delete(id:Long):String{
        var sql = "DELETE FROM incapacidad Where idIncapacidad=?"
        val filas= jdbcTemplate.update(sql,id)
        return if (filas>0) "Incapacidad Eliminada con exito" else "Error al eliminar la incapacidad"
    }
}