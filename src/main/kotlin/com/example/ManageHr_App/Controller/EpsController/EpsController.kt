package com.example.ManageHr_App.Controller.EpsController

import com.example.ManageHr_App.Dto.Eps.EpsDto
import com.example.ManageHr_App.Service.EpsService.EpsService
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
@RequestMapping("/api/eps")
class EpsController(private val service: EpsService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<EpsDto>> {
        val result = service.finAll()
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<EpsDto> {
        val eps = service.finById(id)
        return if (eps != null) ResponseEntity.ok(eps)
        else ResponseEntity.notFound().build()
    }

    // Crear
    @PostMapping
    fun create(@RequestBody dto: EpsDto): ResponseEntity<EpsDto> {
        val id = service.create(dto)
        return if (id > 0) ResponseEntity.ok(dto)
        else ResponseEntity.badRequest().build()
    }

    // Actualizar
    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody dto: EpsDto): ResponseEntity<String> {
        val rows = service.update(id, dto)
        return if (rows > 0) ResponseEntity.ok("Registro actualizado correctamente")
        else ResponseEntity.notFound().build()
    }

    // Eliminar
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<String> {
        val rows = service.delete(id)
        return if (rows > 0) ResponseEntity.ok("Registro eliminado correctamente")
        else ResponseEntity.notFound().build()
    }
}