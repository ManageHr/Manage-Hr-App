package com.example.ManageHr_App.Usuarios

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuarios")
class UsuarioController {

    @Autowired
    lateinit var usuariosServices: UsuariosServices

    // GET ALL
    @GetMapping
    fun obtenerUsuarios(): List<UsuarioDto> {
        return usuariosServices.obtenerUsuarios()
    }

    // GET BY ID
    @GetMapping("/{id}")
    fun obtenerUsuarioPorId(@PathVariable id: Long): UsuarioDto? {
        return usuariosServices.obtenerUsuarioPorId(id)
    }

    // POST
    @PostMapping
    fun crearUsuario(@RequestBody usuario: UsuarioDto): String {
        val filas = usuariosServices.crearUsuario(usuario)
        return if (filas > 0) "Usuario creado con éxito" else "Error al crear usuario"
    }

    // PUT
    @PutMapping("/{id}")
    fun actualizarUsuario(@PathVariable id: Long, @RequestBody usuario: UsuarioDto): String {
        val filas = usuariosServices.actualizarUsuario(id, usuario)
        return if (filas > 0) "Usuario actualizado con éxito" else "Usuario no encontrado"
    }

    // DELETE
    @DeleteMapping("/{id}")
    fun eliminarUsuario(@PathVariable id: Long): String {
        val filas = usuariosServices.eliminarUsuario(id)
        return if (filas > 0) "Usuario eliminado con éxito" else "Usuario no encontrado"
    }
}
