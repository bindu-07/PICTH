package com.photo.picth.ui.presentation.bannerSettings

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.photo.picth.R
import com.photo.picth.adapter.BannerSettingsTopUplineItemAdapter
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.databinding.ActivityMainBinding
import com.photo.picth.databinding.FragmentBannerSettingsBinding
import com.photo.picth.databinding.FragmentMessageBinding
import com.photo.picth.ui.presentation.bannerSettings.data.BannerSettingResponse
import com.photo.picth.ui.presentation.bannerSettings.data.UpdateBannerSettingRequest
import com.photo.picth.ui.presentation.bannerSettings.viewmodel.BannerSettingsResponseViewModel
import com.photo.picth.ui.presentation.bannerSettings.viewmodel.BannerSettingsViewModel
import com.photo.picth.ui.presentation.homepage.viewmodel.HomeViewModel
import com.photo.picth.ui.presentation.profile.model.ProfileModel
import com.photo.picth.utils.ui.CommonMethod
import com.photo.picth.utils.ui.ProgressUtil
import com.photo.picth.viewmodel.LoginViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BannerSettingsActivity.newInstance] factory method to
 * create an instance of this fragment.
 */
class BannerSettingsActivity : AppCompatActivity(R.layout.fragment_banner_settings) {

    private var _binding: FragmentBannerSettingsBinding? = null
    private val viewModel by viewModels<BannerSettingsViewModel>()
    private val viewModel1 by viewModels<BannerSettingsResponseViewModel>()
    val binding get() = _binding!!
    private var rankBanners = false
    private var achivementBanners = false
    private var bonanzaBanners = false
    private var cappingBanners = false
    private var teamLogo = false
    private var successSystem = false
    private var bannersNumber = false
    private var mobileNumber = false
    private var bannersRank = false
    private var bannersRankName  = ""
    private var socialNames = false
    private var socialCustomName = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        supportActionBar!!.hide()
        _binding = FragmentBannerSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel1.getBannerSettings()
        viewModel.getImages()
        getTopImages()
        getData()
        initView()

    }

    fun initView() {


        binding.switchRank.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                rankBanners = true

            } else {
                rankBanners = false
            }
        }
        binding.switchAchievement.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                achivementBanners = true

            } else {
                achivementBanners = false
            }
        }
        binding.switchCapping.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                cappingBanners = true

            } else {
                cappingBanners = false
            }
        }
        binding.switchBonanza.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                bonanzaBanners = true

            } else {
                bonanzaBanners = false
            }
        }
        binding.switchTeamLogo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                teamLogo = true
                binding.rgTeamLogo.visibility = View.VISIBLE

            } else {
                teamLogo = false
                binding.rgTeamLogo.visibility = View.GONE
            }
        }
        binding.switchContactNumber.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                rankBanners = true
                binding.rgContactNumber.visibility = View.VISIBLE

            } else {
                rankBanners = false
                binding.rgContactNumber.visibility = View.GONE
            }
        }
        binding.switchRankOnBanner.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                bannersRank = true
                binding.rgRankBanner.visibility = View.VISIBLE

            } else {
                bannersRank = false
                binding.rgRankBanner.visibility = View.GONE
            }
        }
        binding.switchRankOnBanner.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                bannersRank = true
                binding.rgRankBanner.visibility = View.VISIBLE

            } else {
                bannersRank = false
                binding.rgRankBanner.visibility =
                    View.GONE
            }
        }
        binding.rbCustomRankName.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {

                binding.labelEnterCustomRank.visibility = View.VISIBLE
                binding.tilCustomRank.visibility = View.VISIBLE
            } else {

                binding.labelEnterCustomRank.visibility = View.GONE
                binding.tilCustomRank.visibility = View.GONE
            }
        }

        binding.switchSocialName.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                socialNames = true
                binding.rgSocialName.visibility = View.VISIBLE

            } else {
                socialNames = false
                binding.rgSocialName.visibility = View.GONE
            }
        }
        binding.rbCustomSocialName.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.labelEnterCustomSocialName.visibility = View.VISIBLE
                binding.tilCustomSocialName.visibility = View.VISIBLE
            } else{
                binding.labelEnterCustomSocialName.visibility = View.GONE
                binding.tilCustomSocialName.visibility = View.GONE
            }
        }

//        binding.clOne.setOnClickListener(
//            View.OnClickListener {
//                if (socialNames) {
//                    socialCustomName = binding.tilCustomSocialName.editText!!.text.toString()
//                }
//            }`
//        )

        binding.clSave.setOnClickListener {

              bannersRankName = binding.etRankName.text.toString()
              socialCustomName = binding.etSocialName.text.toString()
            val updateBannerSettingRequest = UpdateBannerSettingRequest(
                mentorImage = "path_to_image",
                rankBanners = rankBanners,
                achivementBanners = achivementBanners,
                bonanzaBanners = bonanzaBanners,
                cappingBanners = cappingBanners,
                teamLogo = teamLogo,
                successSystem = successSystem,
                bannersNumber = bannersNumber,
                mobileNumber = mobileNumber,
                bannersRank = bannersRank,
                bannersRankName = bannersRankName,
                socialNames = socialNames,
                socialCustomName = socialCustomName,
                mentarName = "",
                mentarRole = ""
            )

            viewModel.updateSettingUser(updateBannerSettingRequest)
        }

    }

    private fun getData() {
        viewModel1.homeResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    ProgressUtil.showProgress(this)
                }

                is BaseResponse.Success -> {

                    CommonMethod.showToast(this, it.data.toString())
                    setData(it.data)
                    ProgressUtil.hideProgress()
                }

                is BaseResponse.Error -> {

                    it.msg?.let { it1 -> CommonMethod.showToast(this, it1) }
                }
                else -> {
                    ProgressUtil.hideProgress()
                }
            }
        }

            viewModel.updateSettingResult.observe(this) {
                when (it) {
                    is BaseResponse.Loading -> {
                        ProgressUtil.showProgress(this)
                    }

                    is BaseResponse.Success -> {

                        CommonMethod.showToast(this, it.data.toString())
                        ProgressUtil.hideProgress()
                    }

                    is BaseResponse.Error -> {

                        it.msg?.let { it1 -> CommonMethod.showToast(this, it1) }
                    }
                    else -> {
                        ProgressUtil.hideProgress()
                    }
                }
            }

    }
    fun getTopImages() {
        viewModel.homeResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    ProgressUtil.showProgress(this)
                }

                is BaseResponse.Success -> {

                    CommonMethod.showToast(this, it.data.toString())
                    binding.rvTopUplines.layoutManager=
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

                    binding.imageOne
                    binding.rvTopUplines.adapter = it.data?.let { it1 -> BannerSettingsTopUplineItemAdapter(it1.data.images) }
                    ProgressUtil.hideProgress()
                }

                is BaseResponse.Error -> {

                    it.msg?.let { it1 -> CommonMethod.showToast(this, it1) }
                }
                else -> {
                    ProgressUtil.hideProgress()
                }
            }
        }
    }

    private fun setData(data: BannerSettingResponse?) {
        //val languages = data!!.data.languages.map { it.language }
        val roles = data?.data?.roles?.map { it.role }
         rankBanners = data?.data?.bannerSetting!!.rankBanners

         achivementBanners = data.data.bannerSetting.achivementBanners
         bonanzaBanners = data.data.bannerSetting.bonanzaBanners
         cappingBanners = data.data.bannerSetting.cappingBanners

         teamLogo = data.data.bannerSetting.teamLogo.teamLogo
         successSystem = data.data.bannerSetting.teamLogo.successSystem

         bannersNumber = data.data.bannerSetting.bannersNumber.bannersNumber
         mobileNumber = data.data.bannerSetting.bannersNumber.mobileNumber

         bannersRank = data.data.bannerSetting.bannersRank.bannersRank
         socialNames = data.data.bannerSetting.socialNames.socialNames



//        val languageAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
//        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.spinnerLanguage.adapter = languageAdapter
//
//        val roleAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
//        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.spinnerNamePrefix.adapter = roleAdapter
//
//        val typeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
//        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.spinnerRank.adapter = typeAdapter
    }

}