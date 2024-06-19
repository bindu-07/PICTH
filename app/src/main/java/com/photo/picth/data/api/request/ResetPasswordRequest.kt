package com.photo.picth.data.api.request

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest (
    @SerializedName("username")
    var username: String,
    @SerializedName("newPassword")
    var newPassword: String

)