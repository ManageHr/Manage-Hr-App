package com.example.ManageHr_App.Dto.Eps

class EpsDto (
    private val codigoEps: String,
    private val nombreEps: String
) {
    fun getCodigoEps(): String = codigoEps
    fun getNombreEps(): String = nombreEps
}