package com.example.ManageHr_App.Controller.Genero

import com.example.ManageHr_App.Service.GeneroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/genero")
class GeneroController {

    @Autowired
    lateinit var generoService: GeneroService

    @GetMapping
    fun obtenerTodos(): ResponseEntity<Any> {
        val consulta = generoService.getAll()
        return if (consulta.isNotEmpty()) {
            ResponseEntity.ok(consulta)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
        }
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Int): ResponseEntity<Any> {
        val consulta = generoService.getById(id)
        return if (consulta != null) {
            ResponseEntity.ok(consulta)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
        }
    }
}