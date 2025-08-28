package com.example.ManageHr_App.horaextra


import com.example.ManageHr_App.horaextra.dto.CreateHorasExtraRequest
import com.example.ManageHr_App.horaextra.dto.UpdateHorasExtraRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class HorasExtraService(private val repo: HorasExtraRepository) {

    fun list(): List<HorasExtraModel> = repo.findAll()

    fun get(id: Long): HorasExtraModel =
        repo.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "No existe id=$id")

    fun create(req: CreateHorasExtraRequest): HorasExtraModel {
        val id = repo.create(req)
        return get(id)
    }

    fun update(id: Long, req: UpdateHorasExtraRequest): HorasExtraModel {
        val rows = repo.update(id, req)
        if (rows == 0) throw ResponseStatusException(HttpStatus.NOT_FOUND, "No existe id=$id")
        return get(id)
    }

    fun delete(id: Long) {
        val rows = repo.delete(id)
        if (rows == 0) throw ResponseStatusException(HttpStatus.NOT_FOUND, "No existe id=$id")
    }
}