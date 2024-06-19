package com.photo.picth.repository

import com.photo.picth.data.api.request.ForgotPasswordRequest
import com.photo.picth.data.api.request.LoginRequest
import com.photo.picth.data.api.request.LogoutRequest
import com.photo.picth.data.api.request.RegisterRequest
import com.photo.picth.data.api.request.ResetPasswordRequest
import com.photo.picth.data.api.request.VeryfyotpRequest
import com.photo.picth.data.api.response.ForgotPasswordResponse
import com.photo.picth.data.api.response.LoginResponse
import com.photo.picth.data.api.response.LogoutResponse
import com.photo.picth.data.api.response.RegisterResponse
import com.photo.picth.data.api.response.ResetPasswordResponse
import com.photo.picth.data.api.response.VeryfyotpResponse
import com.photo.picth.network.interfaces.ApiInterface
import com.photo.picth.ui.presentation.homepage.HomeReponseModel
import retrofit2.Response

class ApiRepository {

    suspend fun RegisterUser(RegisterUser:RegisterRequest): Response<RegisterResponse>? {
        return  ApiInterface.getApi()?.RegisterUser(RegisterRequest = RegisterUser)
    }
    suspend fun LoginUser(LoginUser:LoginRequest): Response<LoginResponse>? {
        return  ApiInterface.getApi()?.LoginUser(loginRequest = LoginUser)
    }

    suspend fun ForgotPasswordUser(ForgotPasswordUser: ForgotPasswordRequest): Response<ForgotPasswordResponse>? {
        return  ApiInterface.getApi()?.ForgotPasswordUser(forgotPasswordRequest = ForgotPasswordUser)
    }
    suspend fun LogoutUser(LogoutUser: LogoutRequest): Response<LogoutResponse>? {
        return  ApiInterface.getApi()?.logoutUser(logoutRequest = LogoutUser)
    }

    suspend fun veryfyOtpUser(VeryfyotpUser: VeryfyotpRequest): Response<VeryfyotpResponse>? {
        return  ApiInterface.getApi()?.veryfyOtpdUser(veryfyOtpdRequest = VeryfyotpUser)
    }

    suspend fun resetPasswordUser(resetPasswordUser: ResetPasswordRequest): Response<ResetPasswordResponse>? {
        return  ApiInterface.getApi()?.resetPasswordUser(resetPasswordRequest = resetPasswordUser)
    }
    suspend fun getHomeData(): Response<HomeReponseModel>? {
        return  ApiInterface.getApi()?.getHomeData()
    }
}