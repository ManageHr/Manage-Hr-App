package com.example.ManageHr_App.horaextra.dto

import jakarta.validation.constraints.*
import java.time.LocalDate

data class CreateHorasExtraRequest(
    @field:NotBlank val descripcion: String,
    @field:NotNull  val fecha: LocalDate,
    @field:Min(1)  val nHorasExtra: Int,
    @field:NotNull val tipoHorasId: Long,
    @field:NotNull val contratoId: Long
)

data class UpdateHorasExtraRequest(
    @field:NotBlank val descripcion: String,
    @field:NotNull  val fecha: LocalDate,
    @field:Min(1)  val nHorasExtra: Int,
    @field:NotNull val tipoHorasId: Long,
    @field:NotNull val contratoId: Long
)