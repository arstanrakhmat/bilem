package com.example.bilemonline.data.model

data class UserInfo(
    val createdAt: String,
    val deleted: Boolean,
    val deletedAt: Any,
    val email: String,
    val id: String,
    val password: String,
    val profile: Profile,
    val provider: Any,
    val role: String,
    val updatedAt: String,
    val username: String
)