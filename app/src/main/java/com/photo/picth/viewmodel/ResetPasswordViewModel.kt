package com.photo.picth.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photo.picth.data.api.request.ResetPasswordRequest
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.data.api.response.ResetPasswordResponse
import com.photo.picth.repository.ApiRepository
import kotlinx.coroutines.launch

class ResetPasswordViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = ApiRepository()
    val resetPasswordResult: MutableLiveData<BaseResponse<ResetPasswordResponse>> = MutableLiveData()

    fun ResetPasswordUser(username: String, password: String) {

        resetPasswordResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val resetPasswordUser = ResetPasswordRequest(username = username, newPassword = password)
                val response = userRepo.resetPasswordUser(resetPasswordUser)
                if (response?.code() == 200) {
                    resetPasswordResult.value = BaseResponse.Success(response.body())
                } else {
                    resetPasswordResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                resetPasswordResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}