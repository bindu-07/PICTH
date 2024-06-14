package com.photo.picth.ui.presentation.homepage.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photo.picth.data.api.request.LoginRequest
import com.photo.picth.data.api.request.RegisterRequest
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.data.api.response.LoginResponse
import com.photo.picth.data.api.response.RegisterResponse
import com.photo.picth.repository.UserRepository
import com.photo.picth.ui.presentation.homepage.Category
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepository()
    val homeResult: MutableLiveData<BaseResponse<List<Category>>> = MutableLiveData()

    fun getHome(){

        homeResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                 val response = userRepo.getHomeData()
                if (response?.code() == 200) {
                    homeResult.value = BaseResponse.Success(response.body())
                } else {
                    homeResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                homeResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}