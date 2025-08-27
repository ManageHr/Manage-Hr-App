package com.example.ManageHr_App.horaextra

import com.example.ManageHr_App.horaextra.dto.CreateHorasExtraRequest
import com.example.ManageHr_App.horaextra.dto.UpdateHorasExtraRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/horas-extra")
class HorasExtraController(private val service: HorasExtraService) {

    @GetMapping
    fun list(): List<HorasExtraModel> = service.list()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): HorasExtraModel = service.get(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody body: CreateHorasExtraRequest): HorasExtraModel =
        service.create(body)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody body: UpdateHorasExtraRequest): HorasExtraModel =
        service.update(id, body)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)
}