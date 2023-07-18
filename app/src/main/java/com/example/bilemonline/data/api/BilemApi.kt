package com.example.bilemonline.data.api

import com.example.bilemonline.data.model.CategoryResponse
import com.example.bilemonline.data.model.CourseResponse
import com.example.bilemonline.data.model.UserActivateRequest
import com.example.bilemonline.data.model.UserActivateResponse
import com.example.bilemonline.data.model.UserSignUpRequest
import com.example.bilemonline.data.model.UserSignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BilemApi {
    @POST("auth/sign-up")
    suspend fun userSignUp(
        @Body user: UserSignUpRequest
    ): Response<UserSignUpResponse>

    @POST("auth/activate")
    suspend fun userActivate(
        @Body userActivate: UserActivateRequest
    ): Response<UserActivateResponse>

    @GET("category/flat")
    suspend fun getListOfCategories(
        @Query("page")
        pageNumber: Int = 1,

        @Query("limit")
        limitNumber: Int = 10,

        @Query("order")
        order: String = "ASC",

        @Query("orderField")
        orderField: String = "id"
    ): Response<CategoryResponse>

    @GET("course")
    suspend fun getListOfCourses(
        @Query("page")
        pageNumber: Int = 1,

        @Query("limit")
        limitNumber: Int = 10,

        @Query("order")
        order: String = "ASC",

        @Query("orderField")
        orderField: String = "id",

        @Query("isFree")
        isFree: Boolean,

//        @Query("categoryId")
//        categoryId: String = "UUID"
    ): Response<CourseResponse>
}