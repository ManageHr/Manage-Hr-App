package com.example.ManageHr_App.Dto.Users

data class UserDto(
    val id: Long,
    val name: String,
    val email: String?,
    val email_verified_at: String?,
    val password: String,
    val rol: Int,
    val remember_token: String?,
    val created_at: String?,
    val updated_at: String?
)