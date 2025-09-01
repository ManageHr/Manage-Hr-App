package com.example.ManageHr_App.Dto.HojasVida

import javax.validation.constraints.NotBlank


class HojasVidaDto(
    private var idHojaDeVida:Long,
    @field:NotBlank(message = "La clase de la libreta es obligatorio")
    private var claseLibretaMilitar:String,
    @field:NotBlank(message = "El número de la libreta es obligatorio")
    private var numeroLibretaMilitar:String,
    @field:NotBlank(message = "El número de documento es obligatorio")
    private var usuarioNumDocumento:Long
) {
    fun getIdHojaDeVida():Long{return idHojaDeVida}
    fun setIdHojaDeVida(value:Long){idHojaDeVida=value}
    fun getClaseLibretaMilitar():String{return claseLibretaMilitar}
    fun setClaseLibretaMilitar(value:String){claseLibretaMilitar=value}
    fun getNumeroLibretaMilitar():String{return numeroLibretaMilitar}
    fun setNumeroLibretaMilitar(value:String){numeroLibretaMilitar=value}
    fun getUsuarioNumDocumento():Long{return usuarioNumDocumento}
    fun setUsuarioNumDocumento(value:Long){usuarioNumDocumento=value}
}