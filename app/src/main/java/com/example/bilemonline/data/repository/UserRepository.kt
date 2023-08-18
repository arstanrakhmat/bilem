package com.example.bilemonline.data.repository

import com.example.bilemonline.data.api.BilemApi
import com.example.bilemonline.data.model.UserInfo
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.Path

class UserRepository(private val api: BilemApi) {

    suspend fun getProfileInfo(token: String?): Response<UserInfo> {
        return api.getProfileInfo(token)
    }

    suspend fun updateProfileData(
        userId: String,
        firstName: String,
        lastName: String,
        age: Int,
        about: String,
        fieldOfActivity: String,
        education: String,
        city: String,
    ): Response<Unit> {
        return api.updateUserData(
            userId,
            firstName,
            lastName,
            age,
            about,
            fieldOfActivity,
            education,
            city
        )
    }

    suspend fun updateProfileData2(
        userId: String,
        firstName: String,
        lastName: String,
        about: String,
    ): Response<Unit> {
        return api.updateUserData2(
            userId,
            firstName,
            lastName,
            about)
    }
}