package com.example.ManageHr_App.Service.HojasVida

import com.example.ManageHr_App.Dto.HojasVida.EstudiosDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service

@Service
class EstudiosService {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate
    val rowMapper= RowMapper{rs, _ -> EstudiosDto(
        idEstudios = rs.getLong("idEstudios"),
        nomEstudio = rs.getString("nomEstudio"),
        nomInstitucion = rs.getString("nomInstitucion"),
        tituloObtenido = rs.getString("tituloObtenido"),
        anioInicio = rs.getDate("anioInicio").toLocalDate(),
        anioFinalizacion = rs.getDate("anioFinalizacion").toLocalDate()

    ) }
    fun getAll():List<EstudiosDto>{

        val sql="SELECT * FROM estudios"
        return jdbcTemplate.query(sql, rowMapper)
    }
    fun getById(id:Long): EstudiosDto?{
        val sql="SELECT * FROM estudios WHERE idEstudios=?"
        return try{
            jdbcTemplate.queryForObject(sql,rowMapper,id)
        }catch (e: EmptyResultDataAccessException){
            null
        }
    }
    fun create(estudiosDto: EstudiosDto): Int {
        val sql = "INSERT INTO estudios(nomEstudio, nomInstitucion, tituloObtenido, anioInicio, anioFinalizacion) VALUES (?, ?, ?, ?, ?)"
        val filasAfectadas = jdbcTemplate.update(
            sql,
            estudiosDto.getNomEstudio(),
            estudiosDto.getNomInstitucion(),
            estudiosDto.getTituloObtenido(),
            estudiosDto.getAnioInicio(),
            estudiosDto.getAnioFinalizacion()
        )
        return filasAfectadas
    }
    fun update(id: Long, estudiosDto: EstudiosDto): Int {
        val sql = "UPDATE estudios SET nomEstudio=?, nomInstitucion=?, tituloObtenido=?, anioInicio=?, anioFinalizacion=? WHERE idEstudios=?"
        val filasAfectadas = jdbcTemplate.update(
            sql,
            estudiosDto.getNomEstudio(),
            estudiosDto.getNomInstitucion(),
            estudiosDto.getTituloObtenido(),
            estudiosDto.getAnioInicio(),
            estudiosDto.getAnioFinalizacion(),
            id
        )
        return filasAfectadas
    }
    fun delete(id:Long):String {
        val mensaje:String
        val sql = "DELETE FROM estudios WHERE idEstudios = ?"
        val filasAfectadas = jdbcTemplate.update(sql, id)
        if (filasAfectadas > 0) {
            mensaje= "Estudio eliminado correctamente."
        } else {
            mensaje= "No se encontró el estudio con ese ID"
        }
        return mensaje
    }
}