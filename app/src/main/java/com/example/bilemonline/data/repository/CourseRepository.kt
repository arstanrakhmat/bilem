package com.example.bilemonline.data.repository

import com.example.bilemonline.data.api.BilemApi
import com.example.bilemonline.data.model.Content
import com.example.bilemonline.data.model.CourseById
import com.example.bilemonline.data.model.CourseResponse
import com.example.bilemonline.data.model.GetModuleResponse
import com.example.bilemonline.data.model.GetSectionResponse
import com.example.bilemonline.data.model.PassingCourses
import com.example.bilemonline.data.model.PassingCoursesItem
import retrofit2.Response

class CourseRepository(private val api: BilemApi) {
    suspend fun getListOfCourses(
        token: String?,
        page: Int,
        limit: Int,
        order: String,
        orderField: String,
        isFree: Boolean,
//        categoryId: String
    ): Response<CourseResponse> {
        return api.getListOfCourses(
            token,
            page,
            limit,
            order,
            orderField, isFree
        )
    }

    suspend fun courseById(token: String?, id: String): Response<CourseById> {
        return api.getCourseById(token, id)
    }

    suspend fun getModuleByCourseId(token: String?, id: String): Response<GetModuleResponse> {
        return api.getModuleByCourseId(token, id)
    }

    suspend fun getSectionByModuleId(token: String?, id: String): Response<GetSectionResponse> {
        return api.getSectionsByModelId(token, id)
    }

    suspend fun getContentBySectionId(token: String?, id: String): Response<Content> {
        return api.getContentBySectionId(token, id)
    }

    suspend fun getPassingCourses(token: String?) : Response<PassingCourses> {
        return api.getPassingCourses(token)
    }

    suspend fun addCourseToFavorites(token: String?, courseId: String): Response<PassingCoursesItem> {
        return api.addCourseToFavorites(token, courseId)
    }

    suspend fun getAllFavoriteCourses(token: String?) : Response<PassingCourses> {
        return api.getAllFavoriteCourses(token)
    }

    suspend fun getCoursesByCategoryId(token: String?, categoryId: String): Response<PassingCourses> {
        return api.getCoursesByCategoryId(token,categoryId)
    }
}