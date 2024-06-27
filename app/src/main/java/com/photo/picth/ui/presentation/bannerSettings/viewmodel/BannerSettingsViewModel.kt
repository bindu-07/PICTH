package com.photo.picth.ui.presentation.bannerSettings.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.repository.ApiRepository
import com.photo.picth.ui.presentation.bannerSettings.data.BannerSettingItem
import com.photo.picth.ui.presentation.bannerSettings.data.UpdateBannerSettingRequest
import com.photo.picth.ui.presentation.bannerSettings.data.UpdateBannerSettingResponse
import kotlinx.coroutines.launch

class BannerSettingsViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = ApiRepository()
    val homeResult: MutableLiveData<BaseResponse<BannerSettingItem>> = MutableLiveData()
    val updateSettingResult: MutableLiveData<BaseResponse<UpdateBannerSettingResponse>> = MutableLiveData()

    private var isDataFetched = false

    fun getImages(){
        if (isDataFetched) return
        homeResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = userRepo.getTopLineImages()
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
    fun updateSettingUser(updateBannerSettingRequest: UpdateBannerSettingRequest) {
        if (isDataFetched) return
        homeResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = userRepo.updateSettingUser(updateBannerSettingRequest)
                if (response?.code() == 200) {
                    updateSettingResult.value = BaseResponse.Success(response.body())
                } else {
                    updateSettingResult.value = BaseResponse.Error(response!!.message())
                }
                isDataFetched = true
            } catch (ex: Exception) {
                updateSettingResult.value = BaseResponse.Error(ex.message)
            }
        }
    }




}