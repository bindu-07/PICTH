package com.photo.picth.network.interfaces

import com.photo.picth.network.Injector
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
import com.photo.picth.ui.presentation.bannerSettings.BannerSettingItem
import com.photo.picth.ui.presentation.homepage.data.HomeModelResponse
import com.photo.picth.ui.presentation.profile.model.ProfileModel
import com.photo.picth.utils.ui.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST("api/admin/create")
    suspend fun RegisterUser(@Body RegisterRequest: RegisterRequest): Response<RegisterResponse>

    @POST("api/admin/login")
    suspend fun LoginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("api/admin/forgot-password")
    suspend fun ForgotPasswordUser(@Body forgotPasswordRequest: ForgotPasswordRequest): Response<ForgotPasswordResponse>

    @POST("api/admin/verify-otp")
    suspend fun veryfyOtpdUser(@Body veryfyOtpdRequest: VeryfyotpRequest): Response<VeryfyotpResponse>

    @POST("api/admin/reset-password")
    suspend fun resetPasswordUser(@Body resetPasswordRequest: ResetPasswordRequest): Response<ResetPasswordResponse>

    @POST("/api/admin/logout")
    suspend fun logoutUser(@Body logoutRequest: LogoutRequest): Response<LogoutResponse>

    @GET("api/items/all-item-category")
    suspend fun getHomeData(): Response<HomeModelResponse>

    @GET("api/banner/topline-images")
    suspend fun getTopLineImages(): Response<BannerSettingItem>

    @GET("api/admin/role-details")
    suspend fun getProfileData(): Response<ProfileModel>

    companion object {
        fun getApi(): ApiInterface? {
            return Injector.provideRetrofit(Constants.BASE_URL).create(ApiInterface::class.java)
        }
    }
}