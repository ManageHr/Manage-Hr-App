package com.example.ManageHr_App.Dto.HojasVida

import java.time.LocalDate

class ExperienciaLaboralDto (
    private var idExperiencia:Long,
    private var nomEmpresa:String,
    private var nomJefe:String,
    private var telefono:String,
    private var cargo:String,
    private var actividades:String,
    private var fechaInicio: LocalDate,
    private var fechaFinalizacion: LocalDate

){
    fun getIdExperiencia():Long{ return idExperiencia }
    fun setIdExperiencia(value:Long){ idExperiencia=value}
    fun getNomEmpresa():String{ return nomEmpresa }
    fun setNomEmpresa(value:String){ nomEmpresa=value}
    fun getNomJefe():String{ return nomJefe }
    fun setNomJefe(value:String){ nomJefe=value}
    fun getTelefono():String{ return telefono }
    fun setTelefono(value:String){ telefono=value}
    fun getCargo():String{ return cargo }
    fun setCargo(value:String){ cargo=value}
    fun getActividades():String{ return actividades }
    fun setActividades(value:String){ actividades=value}
    fun getFechaInicio(): LocalDate { return fechaInicio }
    fun setFechaInicio(value: LocalDate) { fechaInicio = value }
    fun getFechaFinalizacion(): LocalDate { return fechaFinalizacion }
    fun setFechaFinalizacion(value: LocalDate) { fechaFinalizacion = value }
}