package com.photo.picth.repository

import com.photo.picth.network.interfaces.UserApi
import com.photo.picth.data.api.request.ForgotPasswordRequest
import com.photo.picth.data.api.request.LoginRequest
import com.photo.picth.data.api.request.RegisterRequest
import com.photo.picth.data.api.request.ResetPasswordRequest
import com.photo.picth.data.api.request.VeryfyotpRequest
import com.photo.picth.data.api.response.ForgotPasswordResponse
import com.photo.picth.data.api.response.LoginResponse
import com.photo.picth.data.api.response.RegisterResponse
import com.photo.picth.data.api.response.ResetPasswordResponse
import com.photo.picth.data.api.response.VeryfyotpResponse
import com.photo.picth.ui.presentation.homepage.Category
import retrofit2.Response

class UserRepository {

    suspend fun RegisterUser(RegisterUser:RegisterRequest): Response<RegisterResponse>? {
        return  UserApi.getApi()?.RegisterUser(RegisterRequest = RegisterUser)
    }
    suspend fun LoginUser(LoginUser:LoginRequest): Response<LoginResponse>? {
        return  UserApi.getApi()?.LoginUser(loginRequest = LoginUser)
    }

    suspend fun ForgotPasswordUser(ForgotPasswordUser: ForgotPasswordRequest): Response<ForgotPasswordResponse>? {
        return  UserApi.getApi()?.ForgotPasswordUser(forgotPasswordRequest = ForgotPasswordUser)
    }

    suspend fun veryfyOtpUser(VeryfyotpUser: VeryfyotpRequest): Response<VeryfyotpResponse>? {
        return  UserApi.getApi()?.veryfyOtpdUser(veryfyOtpdRequest = VeryfyotpUser)
    }

    suspend fun resetPasswordUser(resetPasswordUser: ResetPasswordRequest): Response<ResetPasswordResponse>? {
        return  UserApi.getApi()?.resetPasswordUser(resetPasswordRequest = resetPasswordUser)
    }
    suspend fun getHomeData(): Response<List<Category>>? {
        return  UserApi.getApi()?.getHomeData()
    }
}