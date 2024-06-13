package com.photo.picth.ui.activities.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.photo.picth.databinding.ActivityForgotPasswordBinding
import com.photo.picth.databinding.ActivityLoginBinding
import com.photo.picth.ui.MainActivity

class ForgotPasswordActivity : AppCompatActivity() {
    private var _binding: ActivityForgotPasswordBinding? = null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        _binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    private fun init(){
        binding.clSendOtp.setOnClickListener {
            val  mobileNumber = binding.etMobileNumber.text.toString()
            if(mobileNumber.isNotEmpty()){
                val bundle = Bundle()
                bundle.putString("Activity", "ForgotPasswordActivity")
                val intent = Intent(this, OTPActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
                finish()
            }else{
                binding.etMobileNumber.error = "Please enter mobile number"
            }

        }
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
            finish()
        }

    }
}