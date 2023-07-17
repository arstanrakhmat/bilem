package com.example.bilemonline.data.repository

import com.example.bilemonline.data.api.BilemApi
import com.example.bilemonline.data.model.UserActivateRequest
import com.example.bilemonline.data.model.UserActivateResponse
import com.example.bilemonline.data.model.UserSignUpRequest
import com.example.bilemonline.data.model.UserSignUpResponse
import retrofit2.Response

class AuthRepository(private val api: BilemApi) {
    suspend fun userSignUp(user: UserSignUpRequest): Response<UserSignUpResponse> {
        return api.userSignUp(user = user)
    }

    suspend fun userActivate(user: UserActivateRequest): Response<UserActivateResponse> {
        return api.userActivate(userActivate = user)
    }
}