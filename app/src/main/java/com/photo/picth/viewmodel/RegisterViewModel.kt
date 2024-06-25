package com.photo.picth.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photo.picth.data.api.request.RegisterRequest
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.data.api.response.RegisterResponse
import com.photo.picth.repository.ApiRepository
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = ApiRepository()
    val RegisterResult: MutableLiveData<BaseResponse<RegisterResponse>> = MutableLiveData()

    fun RegisterUser(name: String, username: String, pwd: String, gender: String, role: String) {

        RegisterResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val RegisterUser = RegisterRequest(
                    name = name,
                    username = username,
                    password = pwd,
                    gender = gender,
                    role = role

                )
                val response = userRepo.RegisterUser(RegisterUser = RegisterUser)
                if (response?.code() == 200) {
                    RegisterResult.value = BaseResponse.Success(response.body())
                } else {
                    RegisterResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                RegisterResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}