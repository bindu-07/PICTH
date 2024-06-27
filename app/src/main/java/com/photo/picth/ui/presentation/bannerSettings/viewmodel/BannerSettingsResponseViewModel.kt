package com.photo.picth.ui.presentation.bannerSettings.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.repository.ApiRepository
import com.photo.picth.ui.presentation.bannerSettings.data.BannerSettingItem
import com.photo.picth.ui.presentation.bannerSettings.data.BannerSettingResponse
import kotlinx.coroutines.launch

class BannerSettingsResponseViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = ApiRepository()
    val homeResult: MutableLiveData<BaseResponse<BannerSettingResponse>> = MutableLiveData()
    private var isDataFetched = false

    fun getBannerSettings(){
        if (isDataFetched) return
        homeResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = userRepo.getBannerSettings()
                if (response?.code() == 200) {
                    homeResult.value = BaseResponse.Success(response.body())
                } else {
                    homeResult.value = BaseResponse.Error(response!!.message())
                }
                isDataFetched = true
            } catch (ex: Exception) {
                homeResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}