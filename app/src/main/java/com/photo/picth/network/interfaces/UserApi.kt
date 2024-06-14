package com.photo.picth.network.interfaces

import com.photo.picth.network.ApiClient
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
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @POST("/api/admin/create")
    suspend fun RegisterUser(@Body RegisterRequest: RegisterRequest): Response<RegisterResponse>

    @POST("/api/admin/login")
    suspend fun LoginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/api/admin/forgot-password")
    suspend fun ForgotPasswordUser(@Body forgotPasswordRequest: ForgotPasswordRequest): Response<ForgotPasswordResponse>

    @POST("/api/admin/verify-otp")
    suspend fun veryfyOtpdUser(@Body veryfyOtpdRequest: VeryfyotpRequest): Response<VeryfyotpResponse>

    @POST("/api/admin/reset-password")
    suspend fun resetPasswordUser(@Body resetPasswordRequest: ResetPasswordRequest): Response<ResetPasswordResponse>

    @GET("/api/items/all-item-category")
    suspend fun getHomeData(): Response<List<Category>>


    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}