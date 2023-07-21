package com.example.bilemonline.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bilemonline.data.model.LoginResponse
import com.example.bilemonline.data.model.UserActivateRequest
import com.example.bilemonline.data.model.UserActivateResponse
import com.example.bilemonline.data.model.UserSignUpRequest
import com.example.bilemonline.data.model.UserSignUpResponse
import com.example.bilemonline.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    val register = MutableLiveData<UserSignUpResponse>()
    val activated = MutableLiveData<UserActivateResponse>()
    val singIn = MutableLiveData<LoginResponse>()
    val errorMessage = MutableLiveData<String>()

    val resetPassword = MutableLiveData<String>()
    fun userSignUp(userSignUpRequest: UserSignUpRequest) {
        viewModelScope.launch {
            val response = authRepository.userSignUp(
                userSignUpRequest
            )

            if (response.isSuccessful) {
                register.postValue(response.body())
            } else {
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }

    fun userForgotPassword(email: String) {
        viewModelScope.launch {
            val response = authRepository.userForgotPassword(email)

            if (response.isSuccessful) {
                resetPassword.postValue(response.body())
            } else {
                errorMessage.postValue(response.errorBody().toString())
            }
        }

    }

    fun userSignIn(email: String, password: String) {
        viewModelScope.launch {
            val response = authRepository.userSignIn(
                email, password
            )

            if (response.isSuccessful) {
                singIn.postValue(response.body())
            } else {
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }

    fun userActivate(userActivateRequest: UserActivateRequest) {
        viewModelScope.launch {
            val response = authRepository.userActivate(userActivateRequest)

            if (response.isSuccessful) {
                activated.postValue(response.body())
            } else {
                errorMessage.postValue(response.errorBody().toString())
            }
        }
    }
}