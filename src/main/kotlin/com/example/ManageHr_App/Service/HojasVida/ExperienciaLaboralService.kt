package com.example.ManageHr_App.Service.HojasVida

import com.example.ManageHr_App.Dto.HojasVida.EstudiosDto
import com.example.ManageHr_App.Dto.HojasVida.ExperienciaLaboralDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service


@Service
class ExperienciaLaboralService {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate
    var rowMapper= RowMapper{rs, _ -> ExperienciaLaboralDto(
        idExperiencia = rs.getLong("idExperiencia"),
        nomEmpresa = rs.getString("nomEmpresa"),
        nomJefe = rs.getString("nomJefe"),
        telefono = rs.getString("telefono"),
        cargo = rs.getString("cargo"),
        actividades = rs.getString("actividades"),
        fechaInicio = rs.getDate("fechaInicio").toLocalDate(),
        fechaFinalizacion = rs.getDate("fechaFinalizacion").toLocalDate()

    )}
    fun getAll():List<ExperienciaLaboralDto>{
        val sql="SELECT * FROM experiencialaboral"
        return jdbcTemplate.query(sql,rowMapper)
    }
    fun getById(id:Long): ExperienciaLaboralDto?{
        val sql="SELECT * FROM experiencialaboral WHERE idExperiencia=?"
        return try{
            jdbcTemplate.queryForObject(sql,rowMapper,id)
        }catch (e: EmptyResultDataAccessException){
            null
        }
    }
    fun create(experienciaLaboralDto: ExperienciaLaboralDto): Int {
        val sql = "INSERT INTO experiencialaboral(nomEmpresa, nomJefe, telefono, cargo, actividades, fechaInicio, fechaFinalizacion) VALUES (?, ?, ?, ?, ?, ?, ?)"
        val filasAfectadas = jdbcTemplate.update(
            sql,
            experienciaLaboralDto.getNomEmpresa(),
            experienciaLaboralDto.getNomJefe(),
            experienciaLaboralDto.getTelefono(),
            experienciaLaboralDto.getCargo(),
            experienciaLaboralDto.getActividades(),
            experienciaLaboralDto.getFechaInicio(),
            experienciaLaboralDto.getFechaFinalizacion()
        )
        return filasAfectadas
    }
    fun update(id: Long, experienciaLaboralDto: ExperienciaLaboralDto): ExperienciaLaboralDto? {
        val sql = "UPDATE experiencialaboral SET nomEmpresa=?, nomJefe=?, telefono=?, cargo=?, actividades=?, fechaInicio=?, fechaFinalizacion=? WHERE idExperiencia=?"
        val filasAfectadas = jdbcTemplate.update(
            sql,
            experienciaLaboralDto.getNomEmpresa(),
            experienciaLaboralDto.getNomJefe(),
            experienciaLaboralDto.getTelefono(),
            experienciaLaboralDto.getCargo(),
            experienciaLaboralDto.getActividades(),
            experienciaLaboralDto.getFechaInicio(),
            experienciaLaboralDto.getFechaFinalizacion(),
            id
        )
        return if(filasAfectadas>0) experienciaLaboralDto else null
    }
    fun delete(id:Long):String {
        val mensaje:String
        val sql = "DELETE FROM experiencialaboral WHERE idExperiencia = ?"
        val filasAfectadas = jdbcTemplate.update(sql, id)
        if (filasAfectadas > 0) {
            mensaje= "Experiencia eliminada correctamente."
        } else {
            mensaje= "No se encontró una experiencia con ese ID"
        }
        return mensaje
    }
}