package com.example.ManageHr_App.Controller.Vacantes


import com.example.ManageHr_App.Dto.Vacantes.VacantesDto
import com.example.ManageHr_App.Service.Vacantes.VacantesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import kotlin.to

@RestController
@RequestMapping("/vacantes")
class VacantesController {

    @Autowired
    lateinit var service: VacantesService

    @GetMapping
    fun listar() = service.listarVacantes()

    @GetMapping("/{id}")
    fun obtener(@PathVariable id: Long) =
        service.obtenerVacante(id) ?: mapOf("mensaje" to "No existe con id $id")

    @PostMapping
    fun crear(@RequestBody dto: VacantesDto) =
        if (service.crearVacante(dto) > 0) mapOf("mensaje" to "Creada")
        else mapOf("mensaje" to "Error al crear")

    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Long, @RequestBody dto: VacantesDto) =
        if (service.actualizarVacante(id, dto) > 0) mapOf("mensaje" to "Actualizada")
        else mapOf("mensaje" to "No se actualizó")

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long) =
        if (service.eliminarVacante(id) > 0) mapOf("mensaje" to "Eliminada")
        else mapOf("mensaje" to "No se eliminó")
}
