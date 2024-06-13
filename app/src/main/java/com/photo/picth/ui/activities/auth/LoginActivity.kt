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
    private var remember = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    private fun init(){

        binding.imgSignIn.setOnClickListener {
            val  mobileNumber = binding.etMobileNumber.text.toString()
            val  password = binding.etPassword.text.toString()
            if (mobileNumber.isNotEmpty() && password.isNotEmpty()){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                binding.etMobileNumber.error = "Please enter mobile number"
                binding.etPassword.error = "Please enter password"
            }
        }
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
//            finish()
        }
        binding.tvForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
            //finish()
        }
        binding.ivChecked.setOnClickListener {
            remember = !remember
            if (remember){
                binding.ivChecked.setBackgroundResource(R.drawable.ic_checked)
            }else{
                binding.ivChecked.setBackgroundResource(R.drawable.ic_empty_step)
            }
        }

    }
}