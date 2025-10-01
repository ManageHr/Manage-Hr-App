package com.example.ManageHr_App.Controller.User

import com.example.ManageHr_App.Dto.Users.UserDto
import com.example.ManageHr_App.Service.User.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun obtenerTodos(): ResponseEntity<Any> {
        val users = userService.getAll()
        return if (users.isNotEmpty()) {
            ResponseEntity.ok(users)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
        }
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Long): ResponseEntity<Any> {
        val user = userService.getById(id)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen datos para mostrar")
        }
    }

    @GetMapping("/email/{email}")
    fun obtenerPorEmail(@PathVariable email: String): ResponseEntity<Any> {
        val user = userService.getByEmail(email)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado")
        }
    }

    @PostMapping
    fun crearUsuario(@RequestBody user: UserDto): ResponseEntity<Any> {
        val filasAfectadas = userService.create(user)
        return if (filasAfectadas > 0) {
            ResponseEntity.ok("Usuario creado con éxito")
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario")
        }
    }

    @PutMapping("/{id}")
    fun actualizarUsuario(@PathVariable id: Long, @RequestBody user: UserDto): ResponseEntity<Any> {
        val filasAfectadas = userService.update(id, user)
        return if (filasAfectadas > 0) {
            ResponseEntity.ok("Usuario actualizado con éxito")
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario")
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarUsuario(@PathVariable id: Long): ResponseEntity<Any> {
        val filasAfectadas = userService.delete(id)
        return if (filasAfectadas > 0) {
            ResponseEntity.ok("Usuario eliminado con éxito")
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario")
        }
    }
}