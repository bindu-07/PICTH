package com.photo.picth.ui.presentation.bannerSettings

import com.google.gson.annotations.SerializedName

data class BannerSettingRequest (


    @SerializedName("rankBanners")
    var rankBanners: String,
    @SerializedName("achivementBanners")
    var achivementBanners: String,
    @SerializedName("bonanzaBanners")
    var bonanzaBanners: String,
    @SerializedName("cappingBanners")
    var cappingBanners: String,
    @SerializedName("teamLogo")
    var teamLogo: String,
    @SerializedName("successSystem")
    var successSystem: String,
    @SerializedName("bannersRank")
    var bannersRank: String,
    @SerializedName("bannersNumber")
    var bannersNumber: String,
    @SerializedName("bannersRankName")
    var bannersRankName: String,
    @SerializedName("socialNames")
    var socialNames: String,
    @SerializedName("socialCustomName")
    var socialCustomName: String,


)