package com.photo.picth.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photo.picth.data.api.request.ForgotPasswordRequest
import com.photo.picth.data.api.request.LogoutRequest
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.data.api.response.ForgotPasswordResponse
import com.photo.picth.data.api.response.LogoutResponse
import com.photo.picth.repository.UserRepository
import kotlinx.coroutines.launch

class LogoutViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepository()
    val logoutResult: MutableLiveData<BaseResponse<LogoutResponse>> = MutableLiveData()

    fun LogoutUser(refreshToken: String, accessToken: String) {

        logoutResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val logoutUser = LogoutRequest(refreshToken = refreshToken , accessToken = accessToken)
                val response = userRepo.LogoutUser(logoutUser)
                if (response?.code() == 200) {
                    logoutResult.value = BaseResponse.Success(response.body())
                } else {
                    logoutResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                logoutResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}