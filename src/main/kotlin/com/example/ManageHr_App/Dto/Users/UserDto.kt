package com.example.ManageHr_App.Dto.Users

data class UserDto(
    val id: Long,
    val name: String,
    val email: String?,
    val email_verified_at: String?=null,
    val password: String,
    val rol: Int?=null,
    val remember_token: String?=null,
    val created_at: String?,
    val updated_at: String?
)