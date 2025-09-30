package com.example.ManageHr_App.Dto.EstadoCivil

class EstadoCivilDto(
    private val idEstadoCivil: Int,
    private val nombreEstado: String
) {
    fun getIdEstadoCivil(): Int = idEstadoCivil
    fun getNombreEstado(): String = nombreEstado
}