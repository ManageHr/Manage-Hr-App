package com.example.ManageHr_App.Controller.Pensiones

import com.example.ManageHr_App.Service.Pensiones.PensionesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/pensiones")
class PensionesController {

    @Autowired
    lateinit var pensionesService: PensionesService

    @GetMapping
    fun obtenerTodos(): ResponseEntity<Any> {
        val consulta = pensionesService.getAll()
        return if (consulta.isNotEmpty()) {
            ResponseEntity.ok(consulta)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
        }
    }

    @GetMapping("/{codigo}")
    fun obtenerPorCodigo(@PathVariable codigo: String): ResponseEntity<Any> {
        val consulta = pensionesService.getByCodigo(codigo)
        return if (consulta != null) {
            ResponseEntity.ok(consulta)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
        }
    }
}