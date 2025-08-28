package com.example.ManageHr_App.Dto.HorasExtra

import java.util.Date

class HorasExtraDto(
    private var idHorasExtra: Int,
    private var descripcion: String,
    private var fecha: Date = Date(),
    private var nHorasExtra: Int,
    private var tipoHorasId: Long,
    private var contratoId: Long) {

    fun getidHorasExtra(): Int{
        return idHorasExtra
    }
    fun getDescripcion(): String{
        return descripcion
    }
    fun getFecha(): Date{
        return fecha
    }
    fun getNHorasExtra(): Int{
        return nHorasExtra
    }
    fun getTipoHorasId(): Long{
        return tipoHorasId
    }
    fun getContratoId(): Long{
        return contratoId
    }
    fun setIdHorasExtra(idHorasExtra: Int){
        this.idHorasExtra = idHorasExtra
    }
    fun setDescripcion(descripcion: String){
        this.descripcion = descripcion
    }
    fun setFecha(fecha: Date){
        this.fecha = fecha
    }
    fun setNHorasExtra(nHorasExtra: Int){
        this.nHorasExtra = nHorasExtra
    }
    fun setTipoHorasExtra(tipoHorasId: Long){
        this.tipoHorasId = tipoHorasId
    }
    fun setContratoId(contratoId: Long){
        this.contratoId = contratoId
    }
}