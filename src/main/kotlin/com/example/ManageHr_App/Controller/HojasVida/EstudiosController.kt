package com.example.ManageHr_App.Controller.HojasVida

import com.example.ManageHr_App.Dto.HojasVida.EstudiosDto
import com.example.ManageHr_App.Service.HojasVida.EstudiosService
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
@RequestMapping("/api/estudios")
class EstudiosController {
    @Autowired
    lateinit var estudiosService: EstudiosService
    @GetMapping
    fun getAll():List<EstudiosDto>{
        return estudiosService.getAll()
    }
    @GetMapping("/{id}")
    fun getById(@PathVariable id:Long): ResponseEntity<Any>{
        var respuesta=estudiosService.getById(id)
        return if(respuesta==null) ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe Estudio con ese Id") else ResponseEntity.ok(respuesta)
    }
    @PostMapping
    fun create(@RequestBody estudiosDto:EstudiosDto):String{
        var consulta= estudiosService.create(estudiosDto)
        if (consulta>0){
            return "Estudio Creado con exito"
        }else{
            return "No se pudo crear el estudio"
        }
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id:Long,@RequestBody estudiosDto: EstudiosDto): ResponseEntity<Any>{
        var actualizar=estudiosService.update(id,estudiosDto)
        return if (actualizar>0) ResponseEntity.ok(estudiosDto)else ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo Actualizar")
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long): String{
        return estudiosService.delete(id)
    }

}