package com.example.ManageHr_App.Controller.HojasVida

import com.example.ManageHr_App.Dto.HojasVida.ExperienciaLaboralDto
import com.example.ManageHr_App.Service.HojasVida.ExperienciaLaboralService
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
@RequestMapping("/api/experiencia")
class ExperienciaController {
    @Autowired
    lateinit var experienciaLaboralService: ExperienciaLaboralService
    @GetMapping
    fun obtenerTodos(): ResponseEntity<Any>{
        var consulta=experienciaLaboralService.getAll()
        return if(!consulta.isEmpty()) ResponseEntity.ok(consulta) else ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
    }
    @GetMapping("/{id}")
    fun obtenerId(@PathVariable id:Long): ResponseEntity<Any>{
        var consulta=experienciaLaboralService.getById(id)
        return if(consulta!=null) ResponseEntity.ok(consulta) else ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
    }
    @PostMapping
    fun crear(@RequestBody experienciaLaboralDto: ExperienciaLaboralDto): ResponseEntity<Any>{
        var consulta=experienciaLaboralService.create(experienciaLaboralDto)
        return if (consulta>0) ResponseEntity.ok("Experiencia creada con exito") else ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo crear la experiencia")
    }
    @PutMapping("/{id}")
    fun actualizar(@PathVariable id:Long,@RequestBody experienciaLaboralDto: ExperienciaLaboralDto): ResponseEntity<Any>{
        var consulta=experienciaLaboralService.update(id,experienciaLaboralDto)
        return if (consulta!=null){
            ResponseEntity.ok(consulta)
        }else{
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al actualizar ya que el id no existe")
        }
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long): ResponseEntity<Any>{
        var consulta=experienciaLaboralService.delete(id)
        return ResponseEntity.ok(consulta)
    }
}