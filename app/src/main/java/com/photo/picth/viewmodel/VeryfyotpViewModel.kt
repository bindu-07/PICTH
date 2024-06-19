package com.photo.picth.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photo.picth.data.api.request.VeryfyotpRequest
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.data.api.response.VeryfyotpResponse
import com.photo.picth.repository.ApiRepository
import kotlinx.coroutines.launch

class VeryfyotpViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = ApiRepository()
    val veryfyotpResult: MutableLiveData<BaseResponse<VeryfyotpResponse>> = MutableLiveData()

    fun VeryfyotpUser(username: String, otp: String) {

        veryfyotpResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val forgetPasswordUser = VeryfyotpRequest(username = username, otp = otp)
                val response = userRepo.veryfyOtpUser(forgetPasswordUser)
                if (response?.code() == 200) {
                    veryfyotpResult.value = BaseResponse.Success(response.body())
                } else {
                    veryfyotpResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                veryfyotpResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}