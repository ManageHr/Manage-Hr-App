package com.example.ManageHr_App.Usuarios

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuarios")
class UsuarioController {

    @Autowired
    lateinit var usuariosServices: UsuariosServices


    @GetMapping
    fun obtenerUsuarios(): ResponseEntity<List<UsuarioDto>> {
        val usuarios = usuariosServices.obtenerUsuarios()
        return ResponseEntity(usuarios, HttpStatus.OK)
    }


    @GetMapping("/{id}")
    fun obtenerUsuarioPorId(@PathVariable id: Long): ResponseEntity<UsuarioDto> {
        val usuario = usuariosServices.obtenerUsuarioPorId(id)
        return if (usuario != null) {
            ResponseEntity(usuario, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    @PostMapping
    fun crearUsuario(@RequestBody usuario: UsuarioDto): ResponseEntity<String> {
        val filas = usuariosServices.crearUsuario(usuario)
        return if (filas > 0) {
            ResponseEntity("Usuario creado con éxito", HttpStatus.CREATED)
        } else {
            ResponseEntity("Error al crear usuario", HttpStatus.BAD_REQUEST)
        }
    }


    @PutMapping("/{id}")
    fun actualizarUsuario(@PathVariable id: Long, @RequestBody usuario: UsuarioDto): ResponseEntity<String> {
        val filas = usuariosServices.actualizarUsuario(id, usuario)
        return if (filas > 0) {
            ResponseEntity("Usuario actualizado con éxito", HttpStatus.OK)
        } else {
            ResponseEntity("Usuario no encontrado", HttpStatus.NOT_FOUND)
        }
    }


    @DeleteMapping("/{id}")
    fun eliminarUsuario(@PathVariable id: Long): ResponseEntity<String> {
        val filas = usuariosServices.eliminarUsuario(id)
        return if (filas > 0) {
            ResponseEntity("Usuario eliminado con éxito", HttpStatus.OK)
        } else {
            ResponseEntity("Usuario no encontrado", HttpStatus.NOT_FOUND)
        }
    }
}
