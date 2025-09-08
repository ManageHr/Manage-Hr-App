package com.example.ManageHr_App.Service.HojasVida


import com.example.ManageHr_App.Dto.HojasVida.HojasVidaDto
import com.example.ManageHr_App.Dto.HorasExtra.HorasExtraDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping

@Service
class HojasVidaService{

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate
    val rowMapper= RowMapper{rs, _ -> HojasVidaDto(
        idHojaDeVida = rs.getLong("idHojaDeVida"),
        claseLibretaMilitar = rs.getString("claseLibretaMilitar"),
        numeroLibretaMilitar = rs.getString("numeroLibretaMilitar"),
        usuarioNumDocumento = rs.getLong("usuarioNumDocumento")
    ) }
    fun getAll():List<HojasVidaDto>{

        val sql="SELECT * FROM hojasvida"
        return jdbcTemplate.query(sql, rowMapper)
    }
    fun getById(id:Long): HojasVidaDto?{
        val sql="SELECT * FROM hojasvida WHERE idHojaDeVida=?"
        return try{
            jdbcTemplate.queryForObject(sql,rowMapper,id)
        }catch (e: EmptyResultDataAccessException){
            null
        }
    }
    fun validarUsuario(usuarioNumDocumento: Long): Boolean {
        val sql = "SELECT COUNT(*) FROM hojasvida WHERE usuarioNumDocumento = ?"
        val count = jdbcTemplate.queryForObject(sql, Int::class.java, usuarioNumDocumento)
        return count != null && count > 0
    }
    fun create(hojasVidaDto: HojasVidaDto):Int{
        if (validarUsuario(hojasVidaDto.getUsuarioNumDocumento())){
            throw IllegalStateException("El usaurio ya tiene una CV asosiada")
        }
        val sql="INSERT INTO hojasvida(claseLibretaMilitar,numeroLibretaMilitar,usuarioNumDocumento)VALUES(?,?,?)"
        val filasAfectadas=jdbcTemplate.update(sql,hojasVidaDto.getClaseLibretaMilitar(),hojasVidaDto.getNumeroLibretaMilitar(),hojasVidaDto.getUsuarioNumDocumento())
        return filasAfectadas
    }
    fun update(id: Long, hojasVidaDto: HojasVidaDto): Int {
        val existeHojaVida = getById(id) ?: throw IllegalArgumentException("Hoja de vida no encontrada con ID: $id")

        val nuevoUsuarioNumDocumento = hojasVidaDto.getUsuarioNumDocumento()
        if (existeHojaVida.getUsuarioNumDocumento() != nuevoUsuarioNumDocumento) {

            val tieneOtraHoja = validarUsuario(nuevoUsuarioNumDocumento)
            if (tieneOtraHoja) {
                throw IllegalStateException("El usuario con número de documento $nuevoUsuarioNumDocumento ya tiene otra hoja de vida.")
            }
        }
        val sql = "UPDATE hojasvida SET claseLibretaMilitar=?, numeroLibretaMilitar=?, usuarioNumDocumento=? WHERE idHojaDeVida=?"
        val filasAfectadas = jdbcTemplate.update(
            sql,
            hojasVidaDto.getClaseLibretaMilitar(),
            hojasVidaDto.getNumeroLibretaMilitar(),
            nuevoUsuarioNumDocumento,
            id
        )
        return filasAfectadas
    }
    fun delete(id:Long):String{
        val contratos = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM contrato WHERE hojaDeVida = ?",
            Int::class.java, id
        )
        return if (contratos != null && contratos > 0) {
            "No se puede eliminar la hoja de vida porque está vinculada a un contrato."
        } else {
            val sql="DELETE FROM hojasvida WHERE idHojaDeVida = ?"
            val filasAfectadas = jdbcTemplate.update(sql, id)
            if (filasAfectadas > 0) {
                "Hoja de vida eliminada correctamente."
            } else {
                "No se encontró la hoja de vida con ese ID."
            }
        }
    }


}