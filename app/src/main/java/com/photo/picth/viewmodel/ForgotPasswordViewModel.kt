package com.photo.picth.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photo.picth.data.api.request.ForgotPasswordRequest
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.data.api.response.ForgotPasswordResponse
import com.photo.picth.repository.ApiRepository
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = ApiRepository()
    val forgotPasswordResult: MutableLiveData<BaseResponse<ForgotPasswordResponse>> = MutableLiveData()

    fun ForgetPasswordUser(username: String) {

        forgotPasswordResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val forgetPasswordUser = ForgotPasswordRequest(username = username)
                val response = userRepo.ForgotPasswordUser(forgetPasswordUser)
                if (response?.code() == 200) {
                    forgotPasswordResult.value = BaseResponse.Success(response.body())
                } else {
                    forgotPasswordResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                forgotPasswordResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}