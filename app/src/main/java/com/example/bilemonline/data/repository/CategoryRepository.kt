package com.example.bilemonline.data.repository

import com.example.bilemonline.data.api.BilemApi
import com.example.bilemonline.data.model.CategoryResponse
import retrofit2.Response

class CategoryRepository(private val api: BilemApi) {
    suspend fun getListOfCategory(
        page: Int,
        limit: Int,
        order: String,
        orderField: String
    ): Response<CategoryResponse> {
        return api.getListOfCategories(page, limit, order, orderField)
    }
}