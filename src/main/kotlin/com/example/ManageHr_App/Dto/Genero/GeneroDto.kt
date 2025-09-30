package com.example.ManageHr_App.Dto.Genero

class GeneroDto(
    private val idGenero: Int,
    private val nombreGenero: String,
    private val abreviacionGenero: String
) {
    fun getIdGenero(): Int = idGenero
    fun getNombreGenero(): String = nombreGenero
    fun getAbreviacionGenero(): String = abreviacionGenero
}