package com.example.bilemonline.data.repository

import com.example.bilemonline.data.api.BilemApi
import com.example.bilemonline.data.model.ChangePasswordResponse
import com.example.bilemonline.data.model.LoginResponse
import com.example.bilemonline.data.model.UserActivateRequest
import com.example.bilemonline.data.model.UserActivateResponse
import com.example.bilemonline.data.model.UserSignUpRequest
import com.example.bilemonline.data.model.UserSignUpResponse
import retrofit2.Response

class AuthRepository(private val api: BilemApi) {
    suspend fun userSignUp(user: UserSignUpRequest): Response<UserSignUpResponse> {
        return api.userSignUp(user = user)
    }

    suspend fun userForgotPassword(email: String): Response<Unit> {
        return api.userForgotPassword(email)
    }

    suspend fun userVerifyPassword(email: String, code: String): Response<Unit> {
        return api.userVerifyPassword(email, code)
    }

    suspend fun userChangePassword(
        email: String,
        password: String,
        confirmPassword: String
    ): Response<ChangePasswordResponse> {
        return api.userChangePassword(email, password, confirmPassword)
    }


    suspend fun userSignIn(email: String, password: String): Response<LoginResponse> {
        return api.userSignIn(email, password)
    }

    suspend fun userActivate(user: UserActivateRequest): Response<UserActivateResponse> {
        return api.userActivate(userActivate = user)
    }
}