package com.example.bilemonline.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bilemonline.data.model.CourseResponse
import com.example.bilemonline.data.repository.CourseRepository
import kotlinx.coroutines.launch

class CourseViewModel(private val courseRepository: CourseRepository) : ViewModel() {
    val coursesPaid = MutableLiveData<CourseResponse>()
    val coursesFree = MutableLiveData<CourseResponse>()
    val error = MutableLiveData<String>()

    fun getListOfCoursesPaid(
        page: Int,
        limit: Int,
        order: String,
        orderField: String,
        isFree: Boolean,
//        categoryId: String
    ) {
        viewModelScope.launch {
            val response = courseRepository.getListOfCourses(
                page, limit, order, orderField, isFree
            )

            if (response.isSuccessful) {
                coursesPaid.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }

    fun getListOfCoursesFree(
        page: Int,
        limit: Int,
        order: String,
        orderField: String,
        isFree: Boolean,
//        categoryId: String
    ) {
        viewModelScope.launch {
            val response = courseRepository.getListOfCourses(
                page, limit, order, orderField, isFree
            )

            if (response.isSuccessful) {
                coursesFree.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }
}