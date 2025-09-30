package com.example.ManageHr_App.Dto.Vacaciones

import java.util.Date

class VacacionesDto(
    private var idVacaciones: Int,
    private var motivo: String?,                 // puede ser NULL
    private var fechaInicio: Date,               // mapea a DATE
    private var fechaFinal: Date,                // mapea a DATE
    private var contratoId: Long,                // columna contratold en DB
    private var dias: Int,
    private var estado: String = "Pendiente"     // ENUM: Pendiente/Aprobado/Rechazado
) {
    fun getIdVacaciones(): Int = idVacaciones
    fun getMotivo(): String? = motivo
    fun getFechaInicio(): Date = fechaInicio
    fun getFechaFinal(): Date = fechaFinal
    fun getContratoId(): Long = contratoId
    fun getDias(): Int = dias
    fun getEstado(): String = estado

    fun setIdVacaciones(id: Int) { this.idVacaciones = id }
    fun setMotivo(m: String?) { this.motivo = m }
    fun setFechaInicio(f: Date) { this.fechaInicio = f }
    fun setFechaFinal(f: Date) { this.fechaFinal = f }
    fun setContratoId(c: Long) { this.contratoId = c }
    fun setDias(d: Int) { this.dias = d }
    fun setEstado(e: String) { this.estado = e }
}
