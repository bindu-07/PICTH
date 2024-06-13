package com.photo.picth.ui.activities.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.photo.picth.R
import com.photo.picth.databinding.ActivityConfirmPasswordBinding
import com.photo.picth.databinding.ActivityLoginBinding
import com.photo.picth.ui.MainActivity

class ConfirmPasswordActivity : AppCompatActivity() {
    private var _binding: ActivityConfirmPasswordBinding? = null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        _binding = ActivityConfirmPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    private fun init(){
        binding.imgSignIn.setOnClickListener {
            val newPassword = binding.etNewPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()
            if (newPassword.isNotEmpty()  && confirmPassword.isNotEmpty() ){
                if (newPassword == confirmPassword){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    binding.etConfirmPassword.error = "Password does not match"
                }
            }else{
                binding.etNewPassword.error = "Password is empty"
                binding.etConfirmPassword.error = "Confirm password is empty"
            }
        }
//        binding.tvRegister.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

    }
}