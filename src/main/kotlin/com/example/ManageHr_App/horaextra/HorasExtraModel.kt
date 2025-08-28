package com.example.ManageHr_App.horaextra

import java.time.LocalDate

data class HorasExtraModel(
    val idHorasExtra: Long?,
    val descrip: String,
    val fecha: LocalDate,
    val nHorasExtra: Int,
    val tipoHorasId: Long,
    val contratoId: Long
)