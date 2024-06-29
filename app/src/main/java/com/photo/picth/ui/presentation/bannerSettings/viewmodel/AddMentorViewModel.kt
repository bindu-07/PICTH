package com.photo.picth.ui.presentation.bannerSettings.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.repository.ApiRepository
import com.photo.picth.ui.presentation.bannerSettings.data.AddMentorRequest
import com.photo.picth.ui.presentation.bannerSettings.data.AddMentorResponse
import com.photo.picth.ui.presentation.bannerSettings.data.MentorResponse
import kotlinx.coroutines.launch

class AddMentorViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = ApiRepository()
    val addmentorResult: MutableLiveData<BaseResponse<AddMentorResponse>> = MutableLiveData()
    val getmentorResult: MutableLiveData<BaseResponse<MentorResponse>> = MutableLiveData()
    private var isDataFetched = false

    fun addMentor(mentorImage: String, mentorName: String, mentorRole: String) {

        addmentorResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val addMentorRequest = AddMentorRequest(mentorImage = mentorImage, mentorName = mentorName, mentorRole = mentorRole)
                val response = userRepo.addMentor(addMentorRequest = addMentorRequest)
                if (response?.code() == 200) {
                    addmentorResult.value = BaseResponse.Success(response.body())
                } else {
                    addmentorResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                addmentorResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

    fun getMentors(){
        if (isDataFetched) return
        getmentorResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = userRepo.getMentorData()
                if (response?.code() == 200) {
                    getmentorResult.value = BaseResponse.Success(response.body())
                } else {
                    getmentorResult.value = BaseResponse.Error(response!!.message())
                }
                isDataFetched = true
            } catch (ex: Exception) {
                getmentorResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

}