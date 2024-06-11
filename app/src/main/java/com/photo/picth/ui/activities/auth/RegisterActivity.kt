package com.photo.picth.ui.activities.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.photo.picth.R
import com.photo.picth.databinding.ActivityLoginBinding
import com.photo.picth.databinding.ActivityRegisterBinding
import com.photo.picth.ui.MainActivity

    class RegisterActivity : AppCompatActivity() {
        private var _binding: ActivityRegisterBinding? = null
        val binding get() = _binding!!
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            supportActionBar!!.hide()
            _binding = ActivityRegisterBinding.inflate(layoutInflater)
            setContentView(binding.root)
            init()

        }

        private fun init(){
            binding.imgSignIn.setOnClickListener {
                val intent = Intent(this, OTPActivity::class.java)
                startActivity(intent)
                finish()
            }
            binding.tvLogin.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
