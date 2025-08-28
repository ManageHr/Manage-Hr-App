package com.example.ManageHr_App.Dto.Vacantes

import java.sql.Date

class VacantesDto(
    private var idVacantes: Long,
    private var nomVacante: String,
    private var descripVacante: String,
    private var salario: Double,
    private var expMinima: String,
    private var cargoVacante: String,
    private var catVacId: Long
) {

    fun getIdVacantes() = idVacantes
    fun setIdVacantes(value: Long) { idVacantes = value }

    fun getNomVacante() = nomVacante
    fun setNomVacante(value: String) { nomVacante = value }

    fun getDescripVacante() = descripVacante
    fun setDescripVacante(value: String) { descripVacante = value }

    fun getSalario() = salario
    fun setSalario(value: Double) { salario = value }

    fun getExpMinima() = expMinima
    fun setExpMinima(value: String) { expMinima = value }

    fun getCargoVacante() = cargoVacante
    fun setCargoVacante(value: String) { cargoVacante = value }

    fun getCatVacId() = catVacId
    fun setCatVacId(value: Long) { catVacId = value }

}