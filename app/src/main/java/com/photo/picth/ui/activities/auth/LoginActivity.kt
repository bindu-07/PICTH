package com.photo.picth.ui.activities.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.photo.picth.R
import com.photo.picth.databinding.ActivityLoginBinding
import com.photo.picth.databinding.ActivityMainBinding
import com.photo.picth.ui.MainActivity

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    private fun init(){
        binding.imgSignIn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}