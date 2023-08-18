package com.example.bilemonline.data.repository

import com.example.bilemonline.data.api.BilemApi
import com.example.bilemonline.data.model.UserInfo
import retrofit2.Response

class UserRepository(private val api: BilemApi) {

    suspend fun getProfileInfo(token: String?): Response<UserInfo> {
        return api.getProfileInfo(token)
    }
}