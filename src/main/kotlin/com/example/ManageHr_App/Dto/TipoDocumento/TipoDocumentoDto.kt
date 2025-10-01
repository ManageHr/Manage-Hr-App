package com.example.ManageHr_App.Dto.TipoDocumento

class TipoDocumentoDto(
    private val idTipoDocumento: Int,
    private val nombreTipoDocumento: String,
    private val abreviacionTipoDocumento: String
) {
    fun getIdTipoDocumento(): Int = idTipoDocumento
    fun getNombreTipoDocumento(): String = nombreTipoDocumento
    fun getAbreviacionTipoDocumento(): String = abreviacionTipoDocumento
}