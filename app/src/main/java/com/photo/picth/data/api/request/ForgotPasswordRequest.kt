package com.photo.picth.data.api.request

import com.google.gson.annotations.SerializedName

data class ForgotPasswordRequest (
    @SerializedName("username")
    var username: String
)