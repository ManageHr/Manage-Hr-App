package com.example.ManageHr_App.Controller.Incapacidades

import com.example.ManageHr_App.Dto.Incapacidades.IncapacidadDto
import com.example.ManageHr_App.Service.Incapacidades.IncapacidadServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/incapacidades")
class IncapacidadesController {
    @Autowired
    lateinit var incapacidadServices: IncapacidadServices
    @GetMapping
    fun obtenerIncapacidades():List<IncapacidadDto>{
        return incapacidadServices.getAll()
    }
    @GetMapping("/{id}")
    fun obtenerIncapacidadId(@PathVariable id:Long):IncapacidadDto?{
            return incapacidadServices.getById(id)
    }
    @GetMapping("/estado/{estado}")
    fun obtenerEstado(@PathVariable estado:Int):List<IncapacidadDto>{
        return incapacidadServices.getByEstado(estado)
    }
    @PostMapping
    fun crearIncapacidad(@RequestBody incapacidadDto: IncapacidadDto):String{
        return incapacidadServices.create(incapacidadDto)
    }
    @PutMapping("/{id}")
    fun actualizarIncapacidad(@PathVariable id:Long,@RequestBody incapacidadDto: IncapacidadDto):String{
        return incapacidadServices.update(id,incapacidadDto)
    }
    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id:Long):String{
        return incapacidadServices.delete(id)
    }


}