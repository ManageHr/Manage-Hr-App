package com.example.ManageHr_App.Dto.HojasVida

import java.time.LocalDate

class EstudiosDto(
    private var idEstudios:Long,
    private var nomEstudio:String,
    private var nomInstitucion:String,
    private var tituloObtenido:String,
    private var anioInicio: LocalDate,
    private var anioFinalizacion: LocalDate
) {
    fun getIdEstudios(): Long { return idEstudios }
    fun setIdEstudios(value: Long) { idEstudios = value }
    fun getNomEstudio(): String { return nomEstudio }
    fun setNomEstudio(value: String) { nomEstudio = value }
    fun getNomInstitucion(): String { return nomInstitucion }
    fun setNomInstitucion(value: String) { nomInstitucion = value }
    fun getTituloObtenido(): String { return tituloObtenido }
    fun setTituloObtenido(value: String) { tituloObtenido = value }
    fun getAnioInicio(): LocalDate { return anioInicio }
    fun setAnioInicio(value: LocalDate) { anioInicio = value }
    fun getAnioFinalizacion(): LocalDate { return anioFinalizacion }
    fun setAnioFinalizacion(value: LocalDate) { anioFinalizacion = value }
}