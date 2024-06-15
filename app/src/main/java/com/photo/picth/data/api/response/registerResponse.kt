package com.photo.picth.data.api.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("status")
    var status: Int,
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("id")
    var id: String,
    @SerializedName("message")
    var message: String
) {
    data class Data(
        @SerializedName("user")
        var `user`: User,
        @SerializedName("accessToken")
        var accessToken: String,
        @SerializedName("refreshToken")
        var refreshToken: String
    ) {
        data class User(
            @SerializedName("username")
            var username: String,
            @SerializedName("password")
            var password: String,
            @SerializedName("gender")
            var gender: String,
            @SerializedName("Name")
            var name: String,
        )
    }
}