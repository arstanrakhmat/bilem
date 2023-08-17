package com.example.bilemonline.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bilemonline.data.model.Content
import com.example.bilemonline.data.model.CourseById
import com.example.bilemonline.data.model.CourseResponse
import com.example.bilemonline.data.model.GetModuleResponse
import com.example.bilemonline.data.model.GetSectionResponse
import com.example.bilemonline.data.repository.CourseRepository
import kotlinx.coroutines.launch

class CourseViewModel(private val courseRepository: CourseRepository) : ViewModel() {
    val coursesPaid = MutableLiveData<CourseResponse>()
    val coursesFree = MutableLiveData<CourseResponse>()
    val gotCourseByid = MutableLiveData<CourseById>()
    val gotModuleById = MutableLiveData<GetModuleResponse>()
    val gotSectionByModuleId = MutableLiveData<GetSectionResponse>()
    val gotContentBySectionId = MutableLiveData<Content>()
    val error = MutableLiveData<String>()

    fun getListOfCoursesPaid(
        token: String?,
        page: Int,
        limit: Int,
        order: String,
        orderField: String,
        isFree: Boolean,
//        categoryId: String
    ) {
        viewModelScope.launch {
            val response = courseRepository.getListOfCourses(
                token, page, limit, order, orderField, isFree
            )

            if (response.isSuccessful) {
                coursesPaid.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }

    fun getCourseById(token: String?, id: String) {
        viewModelScope.launch {
            val response = courseRepository.courseById(token, id)

            if (response.isSuccessful) {
                gotCourseByid.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }

    fun getListOfCoursesFree(
        token: String?,
        page: Int,
        limit: Int,
        order: String,
        orderField: String,
        isFree: Boolean,
//        categoryId: String
    ) {
        viewModelScope.launch {
            val response = courseRepository.getListOfCourses(
                token, page, limit, order, orderField, isFree
            )

            if (response.isSuccessful) {
                coursesFree.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }

    fun getModuleById(
        token: String?,
        id: String
    ) {
        viewModelScope.launch {
            val response = courseRepository.getModuleByCourseId(token, id)

            if (response.isSuccessful) {
                gotModuleById.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }

    fun getSectionByModuleId(
        token: String?,
        id: String
    ) {
        viewModelScope.launch {
            val response = courseRepository.getSectionByModuleId(token, id)

            if (response.isSuccessful) {
                gotSectionByModuleId.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }

    fun getContentBySectionId(token: String?, id: String) {
        viewModelScope.launch {
            val response = courseRepository.getContentBySectionId(token, id)

            if (response.isSuccessful) {
                gotContentBySectionId.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }
}