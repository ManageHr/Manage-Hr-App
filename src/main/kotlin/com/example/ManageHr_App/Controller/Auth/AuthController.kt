package com.example.ManageHr_App.Controller.Auth


import com.example.ManageHr_App.Dto.Jwt.LoginRequest
import com.example.ManageHr_App.Dto.Jwt.LoginResponse
import com.example.ManageHr_App.Dto.Usuarios.UserDto
import com.example.ManageHr_App.Service.JWT.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController {

    @Autowired
    private lateinit var authService: AuthService

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        return try {
            val response = authService.login(loginRequest)
            ResponseEntity.ok(response)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                mapOf("error" to e.message)
            )
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                mapOf("error" to "Error interno del servidor")
            )
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody usuarioDto: UserDto): ResponseEntity<Any> {
        return try {
            val response = authService.register(usuarioDto)
            ResponseEntity.status(HttpStatus.CREATED).body(response)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                mapOf("error" to e.message)
            )
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                mapOf("error" to "Error interno del servidor")
            )
        }
    }
}