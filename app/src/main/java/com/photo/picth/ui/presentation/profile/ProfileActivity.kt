package com.photo.picth.ui.presentation.profile

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.photo.picth.R
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.databinding.ActivityProfileBinding
import com.photo.picth.ui.presentation.profile.model.ProfileModel
import com.photo.picth.ui.presentation.profile.viewmodel.ProfileViewModel
import com.photo.picth.utils.ui.CommonMethod
import com.photo.picth.utils.ui.ProgressUtil

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileActivity.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileActivity : AppCompatActivity(R.layout.activity_profile) {
    private var _binding: ActivityProfileBinding? = null
    val binding get() = _binding!!
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        supportActionBar!!.hide()
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        init()
        viewModel.getProfile()
        getData()

    }
    private fun init() {
        binding.ivBack.setOnClickListener {
            Toast.makeText(this,"sdfhdhhdhdf",Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun getData() {
        viewModel.rsult.observe(this) {
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
    }

    private fun setData(data: ProfileModel?) {
        val languages = data!!.data.languages.map { it.language }
        val roles = data.data.roles.map { it.role }
        val types = data.data.types.map { it.type }

        val languageAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerLanguage.adapter = languageAdapter

        val roleAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerNamePrefix.adapter = roleAdapter

        val typeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerRank.adapter = typeAdapter
    }
}