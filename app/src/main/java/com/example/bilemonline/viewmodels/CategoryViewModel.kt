package com.example.bilemonline.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bilemonline.data.model.CategoryResponse
import com.example.bilemonline.data.repository.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {
    val category = MutableLiveData<CategoryResponse>()
    val error = MutableLiveData<String>()

    fun getListOfCategories(
        page: Int,
        limit: Int,
        order: String,
        orderField: String
    ) {
        viewModelScope.launch {
            val response = categoryRepository.getListOfCategory(
                page, limit, order, orderField
            )

            if (response.isSuccessful) {
                category.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }
}