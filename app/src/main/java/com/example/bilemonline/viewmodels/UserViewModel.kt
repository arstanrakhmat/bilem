package com.example.bilemonline.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bilemonline.data.model.UserInfo
import com.example.bilemonline.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    val userInfo = MutableLiveData<UserInfo>()
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
}