package com.example.ManageHr_App.Dto.Contratos

import java.time.LocalDate

class ContratoDto(
    private var idContrato: Long? = null,
    private var tipoContratoId: Int,
    private var hojaDeVida: Long,
    private var area: Long,
    private var cargoArea: Int? = 1,
    private var fechaIngreso: LocalDate,
    private var fechaFinalizacion: LocalDate? = null,
    private var archivo: String? = null,
    private var estado: Byte
) {
    fun getIdContrato(): Long? = idContrato
    fun setIdContrato(idContrato: Long?) { this.idContrato = idContrato }

    fun getTipoContratoId(): Int = tipoContratoId
    fun setTipoContratoId(tipoContratoId: Int) { this.tipoContratoId = tipoContratoId }

    fun getHojaDeVida(): Long = hojaDeVida
    fun setHojaDeVida(hojaDeVida: Long) { this.hojaDeVida = hojaDeVida }

    fun getArea(): Long = area
    fun setArea(area: Long) { this.area = area }

    fun getCargoArea(): Int? = cargoArea
    fun setCargoArea(cargoArea: Int?) { this.cargoArea = cargoArea }

    fun getFechaIngreso(): LocalDate = fechaIngreso
    fun setFechaIngreso(fechaIngreso: LocalDate) { this.fechaIngreso = fechaIngreso }

    fun getFechaFinalizacion(): LocalDate? = fechaFinalizacion
    fun setFechaFinalizacion(fechaFinalizacion: LocalDate?) { this.fechaFinalizacion = fechaFinalizacion }

    fun getArchivo(): String? = archivo
    fun setArchivo(archivo: String?) { this.archivo = archivo }

    fun getEstado(): Byte = estado
    fun setEstado(estado: Byte) { this.estado = estado }
}