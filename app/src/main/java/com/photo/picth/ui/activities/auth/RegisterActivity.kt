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
        var gender = "Male"
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            supportActionBar!!.hide()
            _binding = ActivityRegisterBinding.inflate(layoutInflater)
            setContentView(binding.root)
            init()

        }

        private fun init(){
            binding.imgSignIn.setOnClickListener {
                val  mobileNumber = binding.etMobileNumber.text.toString()
                val  password = binding.etPassword.text.toString()
                val  name = binding.etPassword.text.toString()
                if (mobileNumber.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()){
                    val bundle = Bundle()
                    bundle.putString("Activity", "RegisterActivity")
                    val intent = Intent(this, OTPActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                    finish()
                }else{
                    binding.etMobileNumber.error = "Enter Mobile Number"
                    binding.etPassword.error = "Enter Password"
                    binding.etName.error = "Enter Name"
                }

            }

            binding.ivMale.setOnClickListener {
                binding.ivMale.setBackgroundResource(R.drawable.circle_bg)
                binding.ivFemale.setBackgroundResource(17170445);
                gender = "Male"
            }
            binding.ivFemale.setOnClickListener {
                binding.ivFemale.setBackgroundResource(R.drawable.circle_bg)
                binding.ivMale.setBackgroundResource(17170445);
                gender = "FeMale"
            }
            binding.tvLogin.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
