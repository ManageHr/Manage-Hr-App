package com.example.ManageHr_App.Controller

import com.example.ManageHr_App.Dto.Vacaciones.VacacionesDto
import com.example.ManageHr_App.Service.VacacionesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/vacaciones")
class VacacionesController(private val service: VacacionesService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<VacacionesDto>> =
        ResponseEntity.ok(service.findAll())

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): ResponseEntity<VacacionesDto> {
        val dto = service.findById(id)
        return if (dto != null) ResponseEntity.ok(dto) else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody dto: VacacionesDto): ResponseEntity<VacacionesDto> {
        val id = service.create(dto)
        return if (id > 0) ResponseEntity.ok(dto) else ResponseEntity.badRequest().build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody dto: VacacionesDto): ResponseEntity<String> {
        val rows = service.update(id, dto)
        return if (rows > 0) ResponseEntity.ok("Registro actualizado correctamente")
        else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<String> {
        val rows = service.delete(id)
        return if (rows > 0) ResponseEntity.ok("Registro eliminado correctamente")
        else ResponseEntity.notFound().build()
    }
}
