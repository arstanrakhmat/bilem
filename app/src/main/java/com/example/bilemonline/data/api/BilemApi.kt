package com.example.bilemonline.data.api

import com.example.bilemonline.data.model.CategoryResponse
import com.example.bilemonline.data.model.ChangePasswordResponse
import com.example.bilemonline.data.model.CourseById
import com.example.bilemonline.data.model.CourseResponse
import com.example.bilemonline.data.model.GetModuleResponse
import com.example.bilemonline.data.model.LoginResponse
import com.example.bilemonline.data.model.UserActivateRequest
import com.example.bilemonline.data.model.UserActivateResponse
import com.example.bilemonline.data.model.UserSignUpRequest
import com.example.bilemonline.data.model.UserSignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BilemApi {

    @FormUrlEncoded
    @POST("auth/sign-in")
    suspend fun userSignIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("auth/forgot-password")
    suspend fun userForgotPassword(
        @Field("email") email: String
    ): Response<Unit>

    @FormUrlEncoded
    @POST("auth/verify-password")
    suspend fun userVerifyPassword(
        @Field("email") email: String,
        @Field("code") code: String
    ): Response<Unit>

    @FormUrlEncoded
    @POST("auth/change-password/{email}")
    suspend fun userChangePassword(
        @Path("email")
        email: String,

        @Field("password")
        password: String,

        @Field("confirmPassword")
        confirmPassword: String
    ): Response<ChangePasswordResponse>

    @GET("course/{id}")
    suspend fun getCourseById(
        @Header("Authorization") token: String?,
        @Path("id")
        id: String
    ): Response<CourseById>

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
        @Header("Authorization") token: String?,

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

    @GET("module/{courseId}")
    suspend fun getModuleByCourseId(
        @Header("Authorization") token: String?,
        @Path("courseId") courseId: String
    ): Response<GetModuleResponse>
}