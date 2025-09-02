package com.example.ManageHr_App.Service.Contratos

import com.example.ManageHr_App.Dto.Contratos.ContratoDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service

@Service
class ContratosService {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate
    var rowMapper= RowMapper{rs, _ -> ContratoDto(
        idContrato = rs.getLong("idContrato"),
        tipoContratoId = rs.getInt("tipoContratoId"),
        hojaDeVida = rs.getLong("hojaDeVida"),
        area = rs.getLong("area"),
        cargoArea = rs.getInt("cargoArea"),
        fechaIngreso = rs.getDate("fechaIngreso").toLocalDate(),
        fechaFinalizacion = rs.getDate("fechaFinalizacion").toLocalDate(),
        archivo = rs.getString("archivo"),
        estado = rs.getByte("estado")
    ) }
    fun getAll():List<ContratoDto>{
        var sql="SELECT * FROM contrato"
        return jdbcTemplate.query(sql,rowMapper)
    }
    fun getById(id:Long):ContratoDto?{
        var sql="SELECT * FROM contrato Where idContrato=?"
        try {
            val consulta=jdbcTemplate.queryForObject(sql,rowMapper,id)
            return consulta
        }catch (e: EmptyResultDataAccessException){
            return null
        }catch (ex: DataAccessException){
            throw ex
        }catch (em: Exception){
            throw em
        }

    }
    fun create(contratoDto: ContratoDto): String {
        if (getHojadevida(contratoDto.getHojaDeVida())){
            throw IllegalStateException("El usaurio ya tiene un Contrato asosiado")
        }
        val sql = "INSERT INTO contrato(tipoContratoId, hojaDeVida, area, cargoArea, fechaIngreso, fechaFinalizacion, archivo, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
        val filas = jdbcTemplate.update(
            sql,
            contratoDto.getTipoContratoId(),
            contratoDto.getHojaDeVida(),
            contratoDto.getArea(),
            contratoDto.getCargoArea(),
            contratoDto.getFechaIngreso(),
            contratoDto.getFechaFinalizacion(),
            contratoDto.getArchivo(),
            contratoDto.getEstado()
        )

        return if (filas > 0) "exito" else "error"
    }
    fun getHojadevida(id:Long): Boolean{
        var sql="SELECT COUNT(*) FROM contrato WHERE hojaDeVida=?"
        var count= jdbcTemplate.queryForObject(sql, Int::class.java, id)
        return count != null && count > 0
    }
    fun update(id: Long, contratoDto: ContratoDto): Int {
        val existeContrato = getById(id) ?: throw IllegalArgumentException("Hoja de vida no encontrada con ID: $id")

        val nuevoCV= contratoDto.getHojaDeVida()
        if (existeContrato.getHojaDeVida() != nuevoCV) {

            val tieneOtraHoja = getHojadevida(nuevoCV)
            if (tieneOtraHoja) {
                throw IllegalStateException("El usuario ya cuenta con un contrato.")
            }
        }
        val sql = "UPDATE contrato SET tipoContratoId=?, hojaDeVida=?, area=?, cargoArea=?, fechaIngreso=?, fechaFinalizacion=?, archivo=?, estado=?  WHERE idContrato=?"
        val filasAfectadas = jdbcTemplate.update(
            sql,
            contratoDto.getTipoContratoId(),
            contratoDto.getHojaDeVida(),
            contratoDto.getArea(),
            contratoDto.getCargoArea(),
            contratoDto.getFechaIngreso(),
            contratoDto.getFechaFinalizacion(),
            contratoDto.getArchivo(),
            contratoDto.getEstado(),
            id
        )
        return filasAfectadas
    }
    fun delete(id:Long):String{
        val horasextra = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM horasextra WHERE contratoId = ?",
            Int::class.java, id
        )
        val incapacidad = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM incapacidad WHERE contratoId = ?",
            Int::class.java, id
        )
        val pazysalvo = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM pazysalvo WHERE contratoId = ?",
            Int::class.java, id
        )
        val permiso = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM permisos WHERE contratoId = ?",
            Int::class.java, id
        )
        val vacaciones = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM vacaciones WHERE contratoId = ?",
            Int::class.java, id
        )

        return if ((horasextra != null && horasextra > 0)||(incapacidad != null && incapacidad > 0)||(pazysalvo != null && pazysalvo > 0)||(permiso != null && permiso > 0)||(vacaciones != null && vacaciones > 0)) {
            "No se puede eliminar el contrato porque está vinculado con otra tabla"
        } else {
            val sql="DELETE FROM contrato WHERE idContrato = ?"
            val filasAfectadas = jdbcTemplate.update(sql, id)
            if (filasAfectadas > 0) {
                "Contrato eliminado correctamente."
            } else {
                "No se encontró un contrato con ese ID."
            }
        }
    }
}