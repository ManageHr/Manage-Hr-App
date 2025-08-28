package com.example.ManageHr_App.horaextra

import java.util.Date

data class HorasExtraModel(
    val idHorasExtra: Long?,
    val descripcion: String,
    val fecha: Date,
    val nHorasExtra: Int,
    val tipoHorasId: Long,
    val contratoId: Long
)