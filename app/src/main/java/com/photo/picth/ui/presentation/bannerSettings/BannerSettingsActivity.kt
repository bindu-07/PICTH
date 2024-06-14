package com.photo.picth.ui.presentation.bannerSettings

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.photo.picth.R
import com.photo.picth.databinding.ActivityMainBinding
import com.photo.picth.databinding.FragmentBannerSettingsBinding
import com.photo.picth.databinding.FragmentMessageBinding

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
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        supportActionBar!!.hide()
        _binding = FragmentBannerSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}