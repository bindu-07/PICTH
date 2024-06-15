package com.photo.picth.ui.presentation.homepage.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.repository.ApiRepository
import com.photo.picth.ui.presentation.homepage.HomeReponseModel
import com.photo.picth.utils.ui.AppController
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = ApiRepository()
    val homeResult: MutableLiveData<BaseResponse<HomeReponseModel>> = MutableLiveData()
    private var isDataFetched = false

    fun getHome(){
        if (isDataFetched) return
        homeResult.value = BaseResponse.Loading()
       viewModelScope.launch {
           try {
               val response = userRepo.getHomeData()
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