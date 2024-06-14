package com.photo.picth.data.api.response

import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse (
    @SerializedName("status")
    var status: Int,
    @SerializedName("id")
    var id: String,
    @SerializedName("message")
    var message: String
)