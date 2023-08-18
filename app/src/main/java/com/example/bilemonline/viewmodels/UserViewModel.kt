package com.example.bilemonline.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bilemonline.data.model.UserInfo
import com.example.bilemonline.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    val userInfo = MutableLiveData<UserInfo>()
    val dataUpdated = MutableLiveData<Unit>()
    val error = MutableLiveData<String>()

    fun getProfileInfo(token: String?) {
        viewModelScope.launch {
            val response = userRepository.getProfileInfo(token)

            if (response.isSuccessful) {
                userInfo.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }

    fun updateUserInfo(
        userId: String,
        firstName: String,
        lastName: String,
        age: Int,
        about: String,
        fieldOfActivity: String,
        education: String,
        city: String
    ) {
        viewModelScope.launch {
            val response = userRepository.updateProfileData(
                userId,
                firstName,
                lastName,
                age,
                about,
                fieldOfActivity,
                education,
                city
            )

            if (response.isSuccessful) {
                dataUpdated.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }

    fun updateUserInfo2(
        userId: String,
        firstName: String,
        lastName: String,
        about: String,
    ) {
        viewModelScope.launch {
            val response = userRepository.updateProfileData2(
                userId,
                firstName,
                lastName,
                about
            )

            if (response.isSuccessful) {
                dataUpdated.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }
}