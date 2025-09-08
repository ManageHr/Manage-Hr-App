package com.example.ManageHr_App.Controller.CategoriaVacantes

import com.example.ManageHr_App.Dto.CategoriasVacantes.CategoriaVacanteDto
import com.example.ManageHr_App.Service.CategoriaVacantes.CategoriaVacantesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categorias-vacantes")
class CategoriaVacantesController {

    @Autowired
    lateinit var categoriaVacantesService: CategoriaVacantesService

    @GetMapping
    fun listar(@RequestParam(required = false) nombre: String?): List<CategoriaVacanteDto> =
        categoriaVacantesService.listarTodos(nombre)

    @GetMapping("/{id}")
    fun obtener(@PathVariable id: Long): ResponseEntity<Any> {
        val cat = categoriaVacantesService.obtener(id) ?: return ResponseEntity.status(404).body(mapOf("mensaje" to "No existe id $id"))
        return ResponseEntity.ok(cat)
    }

    @PostMapping
    fun crear(@RequestBody dto: CategoriaVacanteDto): ResponseEntity<Any> {
        val res = categoriaVacantesService.crear(dto)
        if (res == -1) return ResponseEntity.badRequest().body(mapOf("mensaje" to "Nombre duplicado"))
        return if (res > 0) ResponseEntity.status(201).body(mapOf("mensaje" to "Creada"))
        else ResponseEntity.badRequest().body(mapOf("mensaje" to "No se pudo crear"))
    }

    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Long, @RequestBody dto: CategoriaVacanteDto): ResponseEntity<Any> {
        val res = categoriaVacantesService.actualizar(id, dto)
        return when {
            res == -1 -> ResponseEntity.badRequest().body(mapOf("mensaje" to "Nombre duplicado"))
            res == 0  -> ResponseEntity.status(404).body(mapOf("mensaje" to "No existe id $id"))
            else      -> ResponseEntity.ok(mapOf("mensaje" to "Actualizada"))
        }
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<Any> {
        val res = categoriaVacantesService.eliminar(id)
        return if (res > 0) ResponseEntity.ok(mapOf("mensaje" to "Eliminada"))
        else ResponseEntity.status(404).body(mapOf("mensaje" to "No existe id $id"))
    }
}
