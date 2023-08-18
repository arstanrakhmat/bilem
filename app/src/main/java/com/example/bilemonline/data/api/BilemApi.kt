package com.example.bilemonline.data.api

import com.example.bilemonline.data.model.CategoryResponse
import com.example.bilemonline.data.model.ChangePasswordResponse
import com.example.bilemonline.data.model.Content
import com.example.bilemonline.data.model.CourseById
import com.example.bilemonline.data.model.CourseResponse
import com.example.bilemonline.data.model.GetModuleResponse
import com.example.bilemonline.data.model.GetSectionResponse
import com.example.bilemonline.data.model.LoginResponse
import com.example.bilemonline.data.model.PassingCourses
import com.example.bilemonline.data.model.PassingCoursesItem
import com.example.bilemonline.data.model.UserActivateRequest
import com.example.bilemonline.data.model.UserActivateResponse
import com.example.bilemonline.data.model.UserInfo
import com.example.bilemonline.data.model.UserSignUpRequest
import com.example.bilemonline.data.model.UserSignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @POST("module/{moduleId}")
    suspend fun getSectionsByModelId(
        @Header("Authorization") token: String?,
        @Path("moduleId") modelId: String
    ): Response<GetSectionResponse>

    @GET("section/{sectionId}")
    suspend fun getContentBySectionId(
        @Header("Authorization") token: String?,
        @Path("sectionId") sectionlId: String
    ): Response<Content>

    @GET("users/me")
    suspend fun getProfileInfo(
        @Header("Authorization") token: String?
    ): Response<UserInfo>

    @FormUrlEncoded
    @PUT("users/profile/{userId}")
    suspend fun updateUserData(
        @Path("userId") userId: String,
        @Field("firstName") firstName: String,
        @Field("lastName") lastName: String,
        @Field("age") age: Int,
        @Field("about") about: String,
        @Field("fieldOfActivity") fieldOfActivity: String,
        @Field("education") education: String,
        @Field("city") city: String,
    ): Response<Unit>

    @FormUrlEncoded
    @PUT("users/profile/{userId}")
    suspend fun updateUserData2(
        @Path("userId") userId: String,
        @Field("firstName") firstName: String,
        @Field("lastName") lastName: String,
        @Field("about") about: String
    ): Response<Unit>

    @GET("course/passing")
    suspend fun getPassingCourses(
        @Header("Authorization") token: String?
    ): Response<PassingCourses>

    @PUT("course/favorite/{courseId}")
    suspend fun addCourseToFavorites(
        @Header("Authorization") token: String?,
        @Path("courseId") courseId: String
    ): Response<PassingCoursesItem>

    @GET("course/favorites")
    suspend fun getAllFavoriteCourses(
        @Header("Authorization") token: String?
    ): Response<PassingCourses>

    @GET("course/all/{categoryId}")
    suspend fun getCoursesByCategoryId(
        @Header("Authorization") token: String?,
        @Path("categoryId") categoryId: String
    ) : Response<PassingCourses>
}

