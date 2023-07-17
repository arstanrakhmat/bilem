package com.example.bilemonline.data.model

data class UserSignUpRequest(
    val email: String,
    val password: String,
    val username: String
)