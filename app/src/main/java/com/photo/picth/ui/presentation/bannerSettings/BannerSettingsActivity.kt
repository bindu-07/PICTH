package com.photo.picth.ui.presentation.bannerSettings

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.photo.picth.R
import com.photo.picth.adapter.BannerSettingsTopUplineItemAdapter
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.databinding.FragmentBannerSettingsBinding
import com.photo.picth.ui.presentation.bannerSettings.data.AddMentorResponse
import com.photo.picth.ui.presentation.bannerSettings.data.BannerSettingResponse
import com.photo.picth.ui.presentation.bannerSettings.data.MentorResponse
import com.photo.picth.ui.presentation.bannerSettings.data.UpdateBannerSettingRequest
import com.photo.picth.ui.presentation.bannerSettings.viewmodel.AddMentorViewModel
import com.photo.picth.ui.presentation.bannerSettings.viewmodel.BannerSettingsResponseViewModel
import com.photo.picth.ui.presentation.bannerSettings.viewmodel.BannerSettingsViewModel
import com.photo.picth.ui.presentation.bannerSettings.viewmodel.UploadimagesViewModel
import com.photo.picth.utils.ui.ChooseImageBottomSheet
import com.photo.picth.utils.ui.CommonMethod
import com.photo.picth.utils.ui.ProgressUtil

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
    private val viewModel2 by viewModels<UploadimagesViewModel>()
    private val viewModel3 by viewModels<AddMentorViewModel>()

    val binding get() = _binding!!
    private var rankBanners = true
    private var achivementBanners = true
    private var bonanzaBanners = true
    private var cappingBanners = true
    private var teamLogo = false
    private var successSystem = false
    private var bannersNumber = false
    private var mobileNumber = false
    private var bannersRank = false
    private var bannersRankName  = ""
    private var socialNames = false
    private var socialCustomName = ""
    var roles = listOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        supportActionBar!!.hide()
        _binding = FragmentBannerSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel1.getBannerSettings()
        viewModel3.getMentors()
        viewModel.getImages()
        getTopImages()
        initView()
        getData()
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
                successSystem = true
                binding.rgTeamLogo.visibility = View.VISIBLE

            } else {
                teamLogo = false
                successSystem = false
                binding.rgTeamLogo.visibility = View.GONE
            }
        }
        binding.switchContactNumber.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                bannersNumber = true
                mobileNumber = true
                binding.rgContactNumber.visibility = View.VISIBLE

            } else {
                bannersNumber = false
                mobileNumber = false
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
                binding.labelEnterCustomRank.visibility = View.GONE
                binding.tilCustomRank.visibility = View.GONE
            }
        }
//        binding.switchRankOnBanner.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                bannersRank = true
//                binding.rgRankBanner.visibility = View.VISIBLE
//
//            } else {
//                bannersRank = false
//                binding.rgRankBanner.visibility =
//                    View.GONE
//            }
//        }
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
                binding.labelEnterCustomSocialName.visibility = View.GONE
                binding.tilCustomSocialName.visibility = View.GONE
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

        viewModel.updateSettingResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    ProgressUtil.showProgress(this)
                }

                is BaseResponse.Success -> {

                    CommonMethod.showToast(this, it.data.toString())
                    //setData(it.data)
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

        viewModel2.uploadimagesResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    ProgressUtil.showProgress(this)
                }

                is BaseResponse.Success -> {

                    CommonMethod.showToast(this, it.data.toString())
                    //setData(it.data)
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
        binding.clSave.setOnClickListener {

              bannersRankName = binding.etRankName.text.toString()
              socialCustomName = binding.etSocialName.text.toString()
//            ChooseImageBottomSheet { imagePath ->
//                viewModel.updateSettingUser(updateBannerSettingRequest, imagePath)
//            }.show(supportFragmentManager, "ChooseImageBottomSheet")
            Log.d("rankBanners", bannersRankName.toString());
              viewModel.updateSettingUser(rankBanners = rankBanners, achivementBanners = achivementBanners, bonanzaBanners = bonanzaBanners, cappingBanners = cappingBanners, teamLogo = teamLogo, successSystem = successSystem, bannersNumber = bannersNumber, mobileNumber = mobileNumber, bannersRank = bannersRank, bannersRankName = bannersRankName, socialNames = socialNames, socialCustomName = socialCustomName)
        }

        binding.topimageUpload.setOnClickListener {
            ChooseImageBottomSheet { imagePath ->
                viewModel2.Uploadimages(imagePath)
            }.show(supportFragmentManager, "ChooseImageBottomSheet")
        }

        binding.imageOne.setOnClickListener {
            bottomAddMentor()
        }
        binding.imageTwo.setOnClickListener {
            bottomAddMentor()
        }
        binding.imageThree.setOnClickListener {
            bottomAddMentor()
        }
        binding.imageFour.setOnClickListener {
            bottomAddMentor()
        }
        binding.imageFive.setOnClickListener {
            bottomAddMentor()
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

        viewModel3.getmentorResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    ProgressUtil.showProgress(this)
                }

                is BaseResponse.Success -> {

                    CommonMethod.showToast(this, it.data.toString())
                    setData1(it.data)
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

    private fun setData1(data: MentorResponse?) {
        Log.d("mentorName1", data.toString());
        val mentorImage = data!!.data.mentorData.map { it.mentorImg }
        val mentorName = data.data.mentorData.map { it.mentorName}
        val mentorRoll = data.data.mentorData.map { it.mentorRole }
        Log.d("mentorName1", mentorImage[0]);
        if (mentorImage[0].isNotEmpty()) {
            // binding.imageOne.visibility = View.GONE
            binding.loadOne.visibility = View.VISIBLE
            //binding.loadOne.setImageURI(mentorImage.get(0))
            Glide.with(this).load(mentorImage[0]).into(binding.loadOne)
        }
        if (mentorImage[1].isNotEmpty()) {
//            binding.imageTwo.visibility = View.GONE
            binding.loadTwo.visibility = View.VISIBLE
            //binding.loadOne.setImageURI(mentorImage.get(0))
            Glide.with(this).load(mentorImage[1]).into(binding.loadTwo)
        }
        if (mentorImage[2].isNotEmpty()) {
           // binding.imageThree.visibility = View.GONE
            binding.loadThree.visibility = View.VISIBLE
            //binding.loadOne.setImageURI(mentorImage.get(0))
            Glide.with(this).load(mentorImage[2]).into(binding.loadThree)
        }
        if (mentorImage[3].isNotEmpty()) {
            //binding.imageOne.visibility = View.GONE
            binding.loadFour.visibility = View.VISIBLE
            //binding.loadOne.setImageURI(mentorImage.get(0))
            Glide.with(this).load(mentorImage[3]).into(binding.loadFour)
        }
        if (mentorImage[4].isNotEmpty()) {
//            binding.imageOne.visibility = View.GONE
            binding.loadFive.visibility = View.VISIBLE
            //binding.loadOne.setImageURI(mentorImage.get(0))
            Glide.with(this).load(mentorImage[4]).into(binding.loadFive)
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
                        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

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
         roles = data?.data?.role?.map { it.role }!!
        Log.d("role1", roles.toString());
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
         bannersRankName = data.data.bannerSetting.bannersRank.name
         socialCustomName = data.data.bannerSetting.socialNames.name

//        Toast.makeText(this@BannerSettingsActivity, " " +
//                "" +bannersRankName+socialCustomName, Toast.LENGTH_SHORT).show()
        if (rankBanners) {
            binding.switchRank.isChecked = true
        }

        if (achivementBanners) {
            binding.switchAchievement.isChecked = true
        }

        if (bonanzaBanners) {
            binding.switchBonanza.isChecked = true
        }
        if (cappingBanners) {
            binding.switchCapping.isChecked = true
        }
        if (teamLogo) {
            binding.switchTeamLogo.isChecked = true
            binding.rgTeamLogo.visibility = View.VISIBLE
        }else{
            binding.rgTeamLogo.visibility = View.GONE
        }
        if (bannersNumber) {
            binding.switchContactNumber.isChecked = true
            binding.rgContactNumber.visibility = View.VISIBLE
        }else{
            binding.rgContactNumber.visibility = View.GONE
        }
        if (bannersRank) {
            binding.switchRankOnBanner.isChecked = true
            binding.rbCustomRankName.isChecked = true
            binding.rgRankBanner.visibility = View.VISIBLE
            binding.labelEnterCustomRank.visibility = View.VISIBLE
            binding.tilCustomRank.visibility = View.VISIBLE
            binding.etRankName.setText(bannersRankName)
        }else{
            binding.rgRankBanner.visibility = View.GONE
        }

        if (socialNames) {
            binding.switchSocialName.isChecked = true
            binding.rbCustomSocialName.isChecked = true
            binding.rgSocialName.visibility = View.VISIBLE
            binding.labelEnterCustomSocialName.visibility = View.VISIBLE
            binding.tilCustomSocialName.visibility = View.VISIBLE
            binding.etSocialName.setText(socialCustomName)

        }else{
            binding.rgSocialName.visibility = View.GONE

        }

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

    fun bottomAddMentor(){
        val dialog = BottomSheetDialog(this)

        val view = layoutInflater.inflate(R.layout.bs_update_mentor, null)

        val et_name = view.findViewById<TextInputEditText>(R.id.et_name)
        val spinner_rank = view.findViewById<Spinner>(R.id.spinner_rank)
        val add_photo = view.findViewById<ShapeableImageView>(R.id.iv_no_photo)
        val iv_no_photo = view.findViewById<ShapeableImageView>(R.id.iv_profile)
        val btnClose = view.findViewById<ShapeableImageView>(R.id.iv_close)
        val cl_add = view.findViewById<ConstraintLayout>(R.id.cl_add)
        val name = et_name.text.toString()
        var role = ""

        val roleAdapter = ArrayAdapter(this, R.layout.spinner_item, roles)
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_rank.adapter = roleAdapter

        spinner_rank.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                Toast.makeText(this@BannerSettingsActivity, " " +
                            "" + roles[position], Toast.LENGTH_SHORT).show()
                role = roles[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        var image_path  = ""
        add_photo.setOnClickListener {
            ChooseImageBottomSheet { imagePath ->
                image_path =imagePath
            }.show(supportFragmentManager, "ChooseImageBottomSheet")
        }
        if (image_path.isNotEmpty()){
            iv_no_photo.visibility = View.VISIBLE
        }

        cl_add.setOnClickListener {
            viewModel3.addMentor(image_path, name, role)
        }

        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setCancelable(false)
        dialog.setContentView(view)

        dialog.show()
    }

}