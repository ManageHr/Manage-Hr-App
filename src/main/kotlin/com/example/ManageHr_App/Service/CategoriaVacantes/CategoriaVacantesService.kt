package com.example.ManageHr_App.Service.CategoriaVacantes

import com.example.ManageHr_App.Dto.CategoriasVacantes.CategoriaVacanteDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service
import java.sql.ResultSet

@Service
class CategoriaVacantesService {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    private val categoriaMapper = RowMapper { rs: ResultSet, _: Int ->
        CategoriaVacanteDto(
            idCatVac = rs.getLong("idCatVac"),
            nomCategoria = rs.getString("nomCategoria")
        )
    }

    fun listarTodos(nombreFiltro: String?): List<CategoriaVacanteDto> {
        return if (nombreFiltro.isNullOrBlank()) {
            jdbcTemplate.query(
                "SELECT idCatVac, nomCategoria FROM categoriavacantes ORDER BY nomCategoria ASC",
                categoriaMapper
            )
        } else {
            jdbcTemplate.query(
                "SELECT idCatVac, nomCategoria FROM categoriavacantes WHERE nomCategoria LIKE ? ORDER BY nomCategoria ASC",
                categoriaMapper,
                "%$nombreFiltro%"
            )
        }
    }

    fun obtener(idCategoria: Long): CategoriaVacanteDto? =
        jdbcTemplate.query(
            "SELECT idCatVac, nomCategoria FROM categoriavacantes WHERE idCatVac=?",
            categoriaMapper,
            idCategoria
        ).firstOrNull()

    fun existeNombre(nombreCategoria: String, idExcluir: Long? = null): Boolean {
        val (sql, params) = if (idExcluir == null) {
            "SELECT COUNT(*) FROM categoriavacantes WHERE nomCategoria=?" to arrayOf(nombreCategoria)
        } else {
            "SELECT COUNT(*) FROM categoriavacantes WHERE nomCategoria=? AND idCatVac<>?" to arrayOf(nombreCategoria, idExcluir)
        }
        val cantidad = jdbcTemplate.queryForObject(sql, params, Int::class.java) ?: 0
        return cantidad > 0
    }

    fun crear(categoria: CategoriaVacanteDto): Int {
        if (existeNombre(categoria.getNomCategoria())) return -1
        return jdbcTemplate.update(
            "INSERT INTO categoriavacantes (nomCategoria) VALUES (?)",
            categoria.getNomCategoria()
        )
    }

    fun actualizar(idCategoria: Long, categoria: CategoriaVacanteDto): Int {
        if (obtener(idCategoria) == null) return 0
        if (existeNombre(categoria.getNomCategoria(), idCategoria)) return -1
        return jdbcTemplate.update(
            "UPDATE categoriavacantes SET nomCategoria=? WHERE idCatVac=?",
            categoria.getNomCategoria(),
            idCategoria
        )
    }

    fun eliminar(idCategoria: Long): Int =
        jdbcTemplate.update("DELETE FROM categoriavacantes WHERE idCatVac=?", idCategoria)
}
