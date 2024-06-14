package com.photo.picth.ui.activities.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.photo.picth.databinding.ActivityContactUsBinding

class ContactUsActivity : AppCompatActivity(){
    private var _binding: ActivityContactUsBinding? = null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        _binding = ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    private fun init(){


    }
}