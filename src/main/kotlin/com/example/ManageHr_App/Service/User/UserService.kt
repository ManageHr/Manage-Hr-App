package com.example.ManageHr_App.Service.User

import com.example.ManageHr_App.Dto.Users.UserDto
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service

@Service
class UserService(private val jdbcTemplate: JdbcTemplate) {

    private val rowMapper = RowMapper { rs, _ ->
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

    fun getAll(): List<UserDto> {
        val sql = "SELECT * FROM users"
        return jdbcTemplate.query(sql, rowMapper)
    }

    fun getById(id: Long): UserDto? {
        val sql = "SELECT * FROM users WHERE id = ?"
        return try {
            jdbcTemplate.queryForObject(sql, rowMapper, id)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    fun create(user: UserDto): Int {
        val sql = "INSERT INTO users (name, email, email_verified_at, password, rol, remember_token, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
        return jdbcTemplate.update(sql, user.name, user.email, user.email_verified_at, user.password, user.rol, user.remember_token, user.created_at, user.updated_at)
    }

    fun update(id: Long, user: UserDto): Int {
        val sql = "UPDATE users SET name = ?, email = ?, email_verified_at = ?, password = ?, rol = ?, remember_token = ?, created_at = ?, updated_at = ? WHERE id = ?"
        return jdbcTemplate.update(sql, user.name, user.email, user.email_verified_at, user.password, user.rol, user.remember_token, user.created_at, user.updated_at, id)
    }

    fun delete(id: Long): Int {
        val sql = "DELETE FROM users WHERE id = ?"
        return jdbcTemplate.update(sql, id)
    }
}