package com.photo.picth.ui.presentation.bannerSettings.data

import com.google.gson.annotations.SerializedName

data class UpdateBannerSettingRequest (
    @SerializedName("mentorImage")
    var mentorImage: String,
    @SerializedName("rankBanners")
    var rankBanners: Boolean,
    @SerializedName("achivementBanners")
    var achivementBanners: Boolean,
    @SerializedName("bonanzaBanners")
    var bonanzaBanners: Boolean,
    @SerializedName("cappingBanners")
    var cappingBanners: Boolean,
    @SerializedName("teamLogo")
    var teamLogo: Boolean,
    @SerializedName("successSystem")
    var successSystem: Boolean,
    @SerializedName("mobileNumber")
    var mobileNumber: Boolean,
    @SerializedName("bannersNumber")
    var bannersNumber: Boolean,
    @SerializedName("bannersRank")
    var bannersRank: Boolean,
    @SerializedName("bannersRankName")
    var bannersRankName: String,
    @SerializedName("socialNames")
    var socialNames: Boolean,
    @SerializedName("socialCustomName")
    var socialCustomName: String,
    @SerializedName("mentarName")
    var mentarName: String,
    @SerializedName("mentarRole")
    var mentarRole: String,

)