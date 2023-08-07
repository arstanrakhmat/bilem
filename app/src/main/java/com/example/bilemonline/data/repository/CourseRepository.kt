package com.example.bilemonline.data.repository

import com.example.bilemonline.data.api.BilemApi
import com.example.bilemonline.data.model.CourseById
import com.example.bilemonline.data.model.CourseResponse
import retrofit2.Response

class CourseRepository(private val api: BilemApi) {
    suspend fun getListOfCourses(
        page: Int,
        limit: Int,
        order: String,
        orderField: String,
        isFree: Boolean,
//        categoryId: String
    ): Response<CourseResponse> {
        return api.getListOfCourses(
            page,
            limit,
            order,
            orderField, isFree
        )
    }

    suspend fun courseById(id: String) : Response<CourseById> {
        return api.getCourseById(id)
    }
}