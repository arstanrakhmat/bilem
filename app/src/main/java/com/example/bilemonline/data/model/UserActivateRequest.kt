package com.example.bilemonline.data.model

data class UserActivateRequest(
    val username: String,
    val email: String,
    val code: String
)