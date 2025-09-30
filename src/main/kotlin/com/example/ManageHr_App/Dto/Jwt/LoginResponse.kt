package com.example.ManageHr_App.Dto.Jwt

import com.example.ManageHr_App.Dto.Usuarios.UserDto

data class LoginResponse(
    val user: UserDto,
    val token: String,
    val redirect: String = "/directorio"
)