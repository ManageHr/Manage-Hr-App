package com.example.ManageHr_App.Dto.Usuarios

data class UserDto(
    val id: Long? = null,
    val name: String,
    val email: String,
    val password: String,
    val rol: String
)