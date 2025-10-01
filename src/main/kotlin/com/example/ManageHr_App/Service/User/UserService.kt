package com.example.ManageHr_App.Service.User

import com.example.ManageHr_App.Dto.Users.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    private val passwordEncoder = BCryptPasswordEncoder()

    fun getAll(): List<UserDto> {
        val sql = "SELECT id, name, email, password, rol, remember_token, created_at, updated_at FROM users"
        return jdbcTemplate.query(sql) { rs, _ ->
            UserDto(
                id = rs.getLong("id"),
                name = rs.getString("name"),
                email = rs.getString("email"),
                email_verified_at = rs.getString("email_verified_at"),
                password = rs.getString("password"),
                rol = rs.getInt("rol"),
                remember_token = rs.getString("remember_token"),
                created_at = rs.getString("created_at"),
                updated_at = rs.getString("updated_at")
            )
        }
    }

    fun getById(id: Long): UserDto? {
        val sql = "SELECT id, name, email, password, rol, remember_token, created_at, updated_at FROM users WHERE id = ?"
        val users = jdbcTemplate.query(sql, arrayOf(id)) { rs, _ ->
            UserDto(
                id = rs.getLong("id"),
                name = rs.getString("name"),
                email = rs.getString("email"),
                email_verified_at = rs.getString("email_verified_at"),
                password = rs.getString("password"),
                rol = rs.getInt("rol"),
                remember_token = rs.getString("remember_token"),
                created_at = rs.getString("created_at"),
                updated_at = rs.getString("updated_at")
            )
        }
        return if (users.isNotEmpty()) users[0] else null
    }

    fun create(user: UserDto): Int {

        val encryptedPassword = passwordEncoder.encode(user.password)

        val sql = """
            INSERT INTO users 
            (name, email, password, rol, remember_token, created_at, updated_at) 
            VALUES (?, ?, ?, ?, ?, NOW(), NOW())
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            user.name,
            user.email,
            encryptedPassword,
            user.rol,
            user.remember_token
        )
    }

    fun update(id: Long, user: UserDto): Int {
        val sql = """
            UPDATE users 
            SET name = ?, email = ?, rol = ?, remember_token = ?, updated_at = NOW() 
            WHERE id = ?
        """.trimIndent()

        return jdbcTemplate.update(
            sql,
            user.name,
            user.email,
            user.rol,
            user.remember_token,
            id
        )
    }

    fun delete(id: Long): Int {
        val sql = "DELETE FROM users WHERE id = ?"
        return jdbcTemplate.update(sql, id)
    }


    fun getByEmail(email: String): UserDto? {
        val sql = "SELECT id, name, email, password, rol, remember_token, created_at, updated_at FROM users WHERE email = ?"
        val users = jdbcTemplate.query(sql, arrayOf(email)) { rs, _ ->
            UserDto(
                id = rs.getLong("id"),
                name = rs.getString("name"),
                email = rs.getString("email"),
                password = rs.getString("password"),
                rol = rs.getInt("rol"),
                remember_token = rs.getString("remember_token"),
                created_at = rs.getString("created_at"),
                updated_at = rs.getString("updated_at")
                // Si no necesitas email_verified_at, simplemente no lo incluyes
            )
        }
        return if (users.isNotEmpty()) users[0] else null
    }
}