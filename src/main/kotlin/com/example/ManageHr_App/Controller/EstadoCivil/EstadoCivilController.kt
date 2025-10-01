package com.example.ManageHr_App.Controller.EstadoCivil

import com.example.ManageHr_App.Service.EstadoCivil.EstadoCivilService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/estadocivil")
class EstadoCivilController {

    @Autowired
    lateinit var estadoCivilService: EstadoCivilService

    @GetMapping
    fun obtenerTodos(): ResponseEntity<Any> {
        val consulta = estadoCivilService.getAll()
        return if (consulta.isNotEmpty()) {
            ResponseEntity.ok(consulta)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
        }
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Int): ResponseEntity<Any> {
        val consulta = estadoCivilService.getById(id)
        return if (consulta != null) {
            ResponseEntity.ok(consulta)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
        }
    }
}