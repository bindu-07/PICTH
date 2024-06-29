package com.photo.picth.ui.presentation.bannerSettings.data

data class BannerSetting (
    val images: List<Image>,
    val mentarName: String,
    val mentarRole: String,
    val rankBanners: Boolean,
    val achivementBanners: Boolean,
    val bonanzaBanners: Boolean,
    val cappingBanners: Boolean,
    val teamLogo: TeamLogo,
    val bannersNumber: BannersNumber,
    val bannersRank: BannersRank,
    val socialNames: SocialNames,
    //val mentor: List<Mentor>,


)