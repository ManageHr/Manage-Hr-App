package com.example.ManageHr_App.Dto.Nacionalidad



class NacionalidadDto (
    private var idNacionalidad:Int,
    private var codigo:String,
    private var nombre:String
) {
    fun getIdNacionalidad(): Int { return idNacionalidad }
    fun getCodigo(): String { return codigo }
    fun getNombre():String { return nombre }
}