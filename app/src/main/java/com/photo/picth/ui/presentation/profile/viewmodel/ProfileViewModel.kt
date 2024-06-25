package com.photo.picth.ui.presentation.profile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.repository.ApiRepository
import com.photo.picth.ui.presentation.profile.model.ProfileModel
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = ApiRepository()
    val rsult: MutableLiveData<BaseResponse<ProfileModel>> = MutableLiveData()
    private var isDataFetched = false

    fun getProfile(){
        if (isDataFetched) return
        rsult.value = BaseResponse.Loading()
       viewModelScope.launch {
           try {
               val response = userRepo.getProfile()
                if (response?.code() == 200) {
                    rsult.value = BaseResponse.Success(response.body())
                } else {
                    rsult.value = BaseResponse.Error(response!!.message())
                }
               isDataFetched = true
            } catch (ex: Exception) {
               rsult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}