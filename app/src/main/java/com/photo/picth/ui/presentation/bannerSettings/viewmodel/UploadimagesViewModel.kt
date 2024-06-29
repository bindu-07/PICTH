package com.photo.picth.ui.presentation.bannerSettings.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photo.picth.data.api.request.LoginRequest
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.data.api.response.LoginResponse
import com.photo.picth.repository.ApiRepository
import com.photo.picth.ui.presentation.bannerSettings.data.ImageUpload
import com.photo.picth.ui.presentation.bannerSettings.data.ImageUploadResponse
import kotlinx.coroutines.launch

class UploadimagesViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = ApiRepository()
    val uploadimagesResult: MutableLiveData<BaseResponse<ImageUploadResponse>> = MutableLiveData()

    fun Uploadimages(toplineimage: String) {

        uploadimagesResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val uploadimages = ImageUpload(toplineimage = toplineimage)
                val response = userRepo.uploadTopLineImage(imageUpload = uploadimages)
                if (response?.code() == 200) {
                    uploadimagesResult.value = BaseResponse.Success(response.body())
                } else {
                    uploadimagesResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                uploadimagesResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}