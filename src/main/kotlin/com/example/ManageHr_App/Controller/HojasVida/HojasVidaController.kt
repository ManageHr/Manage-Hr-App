package com.example.ManageHr_App.Controller.HojasVida

import com.example.ManageHr_App.Dto.HojasVida.HojasVidaDto
import com.example.ManageHr_App.Service.HojasVida.HojasVidaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/hojas-de-vida")
class HojasVidaController {
    @Autowired
    lateinit var hojasVidaService: HojasVidaService
    @GetMapping
    fun obtenerTodas():List<HojasVidaDto>{
        return hojasVidaService.getAll()
    }
    @GetMapping("/{id}")
    fun obtenerId(@PathVariable id: Long): ResponseEntity<Any> {
        val resultado = hojasVidaService.getById(id)
        return if (resultado == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hoja de vida no encontrada")
        } else {
            ResponseEntity.ok(resultado)
        }
    }
    @PostMapping
    fun crearCV(@RequestBody hojasVidaDto: HojasVidaDto): ResponseEntity<Any>{
        var mensaje:String

        return try {

            val consulta = hojasVidaService.create(hojasVidaDto)
            mensaje = if (consulta == 1) "Hoja de vida creada con éxito" else "No se pudo crear la Hoja de vida"
            ResponseEntity.ok(mensaje)
        } catch (e: IllegalStateException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message ?: "")
        }catch (ex: Exception){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message ?: "")
        }
    }
    @PutMapping("/{id}")
    fun actualizarHojadevida(@PathVariable id:Long, @RequestBody hojasVidaDto: HojasVidaDto):ResponseEntity<Any>{
        return try{
            val filas = hojasVidaService.update(id,hojasVidaDto)
            if (filas>0){
                ResponseEntity.ok(hojasVidaDto)
            }else{
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la hoja de vida con ID: $id")
            }
        }catch (e: IllegalStateException){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message ?:"Error en la actualizacion")
        }catch (ex: IllegalArgumentException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message?:"Hoja de vida no encontrada")
        }catch (em: Exception){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(em.message?:"Error desconocido")
        }

    }
    @DeleteMapping("/{id}")
    fun elieminarHojadevida(@PathVariable id:Long): ResponseEntity<String>{
        val resultado=hojasVidaService.delete(id)
        return if(resultado=="Hoja de vida eliminada correctamente."){
            ResponseEntity.ok(resultado)
        }else{
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado)
        }
    }
}