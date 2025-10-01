package com.example.ManageHr_App.Controller.Eps

import com.example.ManageHr_App.Service.EpsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/eps")
class EpsController {

    @Autowired
    lateinit var epsService: EpsService

    @GetMapping
    fun obtenerTodos(): ResponseEntity<Any> {
        val consulta = epsService.getAll()
        return if (consulta.isNotEmpty()) {
            ResponseEntity.ok(consulta)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
        }
    }

    @GetMapping("/{codigo}")
    fun obtenerPorCodigo(@PathVariable codigo: String): ResponseEntity<Any> {
        val consulta = epsService.getById(codigo)
        return if (consulta != null) {
            ResponseEntity.ok(consulta)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
        }
    }
}