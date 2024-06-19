package com.photo.picth.data.api.request

import com.google.gson.annotations.SerializedName

data class LogoutRequest (
    @SerializedName("refreshToken")
    var refreshToken: String,
    @SerializedName("accessToken")
    var accessToken: String,
)