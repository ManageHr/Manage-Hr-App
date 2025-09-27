package com.example.ManageHr_App.Service.JWT


import com.example.ManageHr_App.Dto.Jwt.LoginRequest
import com.example.ManageHr_App.Dto.Jwt.LoginResponse
import com.example.ManageHr_App.Dto.Jwt.JwtUtil
import com.example.ManageHr_App.Dto.Usuarios.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class AuthService {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    private val passwordEncoder = BCryptPasswordEncoder()
    private val logger = Logger.getLogger(AuthService::class.java.name)

    private val usuarioRowMapper = RowMapper { rs, _ ->
        UserDto(
            id = rs.getLong("id"),
            name = rs.getString("name"),
            email = rs.getString("email"),
            password = rs.getString("password"),
            rol = rs.getString("rol")
        )
    }

    fun login(loginRequest: LoginRequest): LoginResponse {
        logger.info("Intento de login: ${loginRequest.email}")

        // Buscar usuario por email
        val sql = "SELECT * FROM users WHERE email = ?"
        val usuarios = jdbcTemplate.query(sql, usuarioRowMapper, loginRequest.email)

        if (usuarios.isEmpty()) {
            logger.warning("Usuario no encontrado: ${loginRequest.email}")
            throw IllegalArgumentException("Correo o contraseña incorrectos")
        }

        val usuario = usuarios[0]

        // Verificar contraseña (compara con BCrypt)
        if (!passwordEncoder.matches(loginRequest.password, usuario.password)) {
            logger.warning("Contraseña incorrecta para: ${loginRequest.email}")
            throw IllegalArgumentException("Correo o contraseña incorrectos")
        }

        // Generar token JWT
        val token = jwtUtil.generateToken(usuario.email)

        logger.info("Login exitoso para: ${loginRequest.email}")

        return LoginResponse(
            user = usuario.copy(password = ""), // No enviar password
            token = token
        )
    }

    fun register(usuarioDto: UserDto): LoginResponse {
        // Verificar si el email ya existe
        val checkSql = "SELECT COUNT(*) FROM users WHERE email = ?"
        val count = jdbcTemplate.queryForObject(checkSql, Int::class.java, usuarioDto.email) ?: 0

        if (count > 0) {
            throw IllegalArgumentException("El email ya está registrado")
        }

        // Encriptar contraseña
        val encryptedPassword = passwordEncoder.encode(usuarioDto.password)

        // Insertar usuario
        val sql = "INSERT INTO users (name, email, password, rol) VALUES (?, ?, ?, ?)"
        jdbcTemplate.update(sql, usuarioDto.name, usuarioDto.email, encryptedPassword, usuarioDto.rol)

        // Obtener el usuario recién creado
        val newUserSql = "SELECT * FROM users WHERE email = ?"
        val nuevoUsuario = jdbcTemplate.query(newUserSql, usuarioRowMapper, usuarioDto.email)[0]

        // Generar token
        val token = jwtUtil.generateToken(nuevoUsuario.email)

        return LoginResponse(
            user = nuevoUsuario.copy(password = ""),
            token = token
        )
    }
}