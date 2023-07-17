package com.example.bilemonline.data.model

data class UserActivateResponse(
    val accessToken: String,
    val refreshToken: String,
    val userAuthData: UserAuthData
)