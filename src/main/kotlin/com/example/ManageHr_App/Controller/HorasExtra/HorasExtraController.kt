package com.example.ManageHr_App.Controller

import com.example.ManageHr_App.Dto.HorasExtra.HorasExtraDto
import com.example.ManageHr_App.Service.HorasExtraService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/horasextra")
class HorasExtraController(private val service: HorasExtraService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<HorasExtraDto>> {
        val result = service.findAll()
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): ResponseEntity<HorasExtraDto> {
        val horasExtra = service.findById(id)
        return if (horasExtra != null) ResponseEntity.ok(horasExtra)
        else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody dto: HorasExtraDto): ResponseEntity<HorasExtraDto> {
        val id = service.create(dto)
        return if (id > 0) ResponseEntity.ok(dto)
        else ResponseEntity.badRequest().build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody dto: HorasExtraDto): ResponseEntity<String> {
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
