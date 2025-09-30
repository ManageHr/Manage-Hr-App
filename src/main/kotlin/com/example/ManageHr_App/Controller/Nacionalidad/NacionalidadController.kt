package com.example.ManageHr_App.Controller.Nacionalidad

import com.example.ManageHr_App.Service.Nacionalidad.NacionalidadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/nacionalidad")
class NacionalidadController {
    @Autowired
    lateinit var nacionalidadService: NacionalidadService
    @GetMapping
    fun obtenerTodos(): ResponseEntity<Any> {
        var consulta=nacionalidadService.getAll()
        return if(!consulta.isEmpty()) ResponseEntity.ok(consulta) else ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
    }
    @GetMapping("/{id}")
    fun obtenerId(@PathVariable id:Int): ResponseEntity<Any> {
        var consulta=nacionalidadService.getById(id)
        return if(consulta!=null) ResponseEntity.ok(consulta) else ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
    }
}