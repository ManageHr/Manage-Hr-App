package com.example.ManageHr_App.Dto.Pensiones

class PensionesDto(
    private val codigoPensiones: String,
    private val nombrePensiones: String
) {
    fun getCodigoPensiones(): String = codigoPensiones
    fun getNombrePensiones(): String = nombrePensiones
}