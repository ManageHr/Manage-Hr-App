package com.example.ManageHr_App.Dto.Incapacidades

import java.sql.Date
import java.time.LocalDate

class IncapacidadDto(
    private var idIncapacidad: Long?=null,
    private var archivo:String?=null,
    private var estado: Int?=null,
    private var fechaInicio: LocalDate,
    private var fechaFinal:LocalDate,
    private var contratoId:Long

) {
    fun getIdIncapacidad():Long?{return idIncapacidad }
    fun setIncapacidad(value:Long){ idIncapacidad=value }
    fun getArchivo():String?{return archivo}
    fun setArchivo(value:String){ archivo=value }
    fun getEstado():Int?{ return estado }
    fun setEstado(value:Int){ estado=value }
    fun getFechaInicio():LocalDate{ return fechaInicio }
    fun setFechaInicio(value:LocalDate){ fechaInicio=value }
    fun getFechaFinal():LocalDate{ return fechaFinal }
    fun setFechaFinal(value:LocalDate){ fechaFinal=value }
    fun getContratoId():Long{ return contratoId }
    fun setContratoId(value:Long){ contratoId=value }
}