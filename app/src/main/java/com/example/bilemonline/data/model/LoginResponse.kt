package com.example.bilemonline.data.model

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val user: User
)