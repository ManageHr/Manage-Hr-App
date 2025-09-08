package com.example.ManageHr_App.Controller.Contratos

import com.example.ManageHr_App.Dto.Contratos.ContratoDto

import com.example.ManageHr_App.Service.Contratos.ContratosService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
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
@RequestMapping("/api/contratos")
class ContratoController {
    @Autowired
    lateinit var contratosService: ContratosService
    @GetMapping
    fun getAll(): ResponseEntity<Any>{
        var consulta= contratosService.getAll()
        return if(consulta!=null) ResponseEntity.ok(consulta)else ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
    }
    @GetMapping("/{id}")
    fun getAll(@PathVariable id:Long): ResponseEntity<Any>{
        var consulta= contratosService.getById(id)
        return try {
            if(consulta!=null) ResponseEntity.ok(consulta)else ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
        }catch (e: DataAccessException){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la conexion de la base de datos")
        }catch (e:Exception){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error eln la url")
        }
    }
    @PostMapping
    fun create(@RequestBody contratoDto: ContratoDto): ResponseEntity<Any> {
        return try {
            val resultado = contratosService.create(contratoDto)
            return if (resultado=="exito")
                ResponseEntity.ok("Contrato creado exitosamente")
            else ResponseEntity.status(500).body("No se pudo crear el contrato")
        } catch (e: IllegalStateException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message ?: "")
        }catch (ex: Exception){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message ?: "")
        }
    }
    @PutMapping("/{id}")
    fun actualizarHojadevida(@PathVariable id:Long, @RequestBody contratoDto: ContratoDto):ResponseEntity<Any>{
        return try{
            val filas = contratosService.update(id, contratoDto)
            if (filas>0){
                ResponseEntity.ok(contratoDto)
            }else{
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró contrato con ID: $id")
            }
        }catch (e: IllegalStateException){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message ?:"Error en la actualizacion")
        }catch (ex: IllegalArgumentException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message?:"Contrato no encontrada")
        }catch (em: Exception){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(em.message?:"Error desconocido")
        }
    }
    @DeleteMapping("/{id}")
    fun elieminarHojadevida(@PathVariable id:Long): ResponseEntity<String>{
        val resultado=contratosService.delete(id)
        return if(resultado=="Contrato eliminado correctamente."){
            ResponseEntity.ok(resultado)
        }else{
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado)
        }
    }
}