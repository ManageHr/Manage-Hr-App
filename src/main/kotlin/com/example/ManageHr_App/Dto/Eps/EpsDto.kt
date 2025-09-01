package com.example.ManageHr_App.Dto.Eps

class EpsDto(
    private var codigoEps: String,
    private var nombreEps: String
) {
    fun getCodigoEps():String{
        return codigoEps
    }
    fun getNombreEps(): String{
        return nombreEps
    }
    fun setCodigoEps(codigoEps:String){
        this.codigoEps = codigoEps
    }
    fun setNombre(nombreEps:String){
        this.nombreEps = nombreEps
    }

}