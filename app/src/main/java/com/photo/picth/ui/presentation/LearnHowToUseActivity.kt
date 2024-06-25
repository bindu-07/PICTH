package com.photo.picth.ui.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.photo.picth.R
import com.photo.picth.adapter.BannerAdapter
import com.photo.picth.databinding.ActivityLearnHowToUseBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LearnHowToUseActivity.newInstance] factory method to
 * create an instance of this fragment.
 */
class LearnHowToUseActivity : AppCompatActivity(R.layout.activity_learn_how_to_use) {
    private var _binding: ActivityLearnHowToUseBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        supportActionBar!!.hide()
        _binding =  ActivityLearnHowToUseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        _binding!!.rvLearningMaterials.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        _binding!!.rvLearningMaterials.adapter = BannerAdapter(R.layout.rv_item_learn, "achieve")


    }


}