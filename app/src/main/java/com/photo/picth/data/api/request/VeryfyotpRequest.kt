package com.photo.picth.data.api.request

import com.google.gson.annotations.SerializedName

data class VeryfyotpRequest (
    @SerializedName("username")
    var username: String,
    @SerializedName("otp")
    var otp: String
)