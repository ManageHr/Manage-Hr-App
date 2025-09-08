package com.example.ManageHr_App.Dto.CategoriasVacantes

class CategoriaVacanteDto(
    private var idCatVac: Long? = null,
    private var nomCategoria: String
) {
    fun getIdCatVac() = idCatVac
    fun setIdCatVac(v: Long?) { idCatVac = v }

    fun getNomCategoria() = nomCategoria
    fun setNomCategoria(v: String) { nomCategoria = v }
}
