package com.photo.picth.data.api.request

import com.google.gson.annotations.SerializedName
 data class RegisterRequest(
        @SerializedName("name")
        var name: String,
        @SerializedName("username")
        var username: String,
        @SerializedName("password")
        var password: String,
        @SerializedName("gender")
        var gender: String,
        @SerializedName("role")
        var role: String
    )