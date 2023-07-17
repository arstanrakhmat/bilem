package com.example.bilemonline.data.api

import com.example.bilemonline.data.model.UserActivateRequest
import com.example.bilemonline.data.model.UserActivateResponse
import com.example.bilemonline.data.model.UserSignUpRequest
import com.example.bilemonline.data.model.UserSignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BilemApi {
    @POST("auth/sign-up")
    suspend fun userSignUp(
        @Body user: UserSignUpRequest
    ): Response<UserSignUpResponse>

    @POST("auth/activate")
    suspend fun userActivate(
        @Body userActivate: UserActivateRequest
    ): Response<UserActivateResponse>
}