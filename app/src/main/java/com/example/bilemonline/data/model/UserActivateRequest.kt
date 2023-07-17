package com.example.bilemonline.data.model

data class UserActivateRequest(
    val code: String,
    val email: String,
    val username: String
)