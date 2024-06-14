package com.photo.picth.ui.activities.auth

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.photo.picth.R
import androidx.activity.viewModels
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.data.api.response.ForgotPasswordResponse
import com.photo.picth.data.api.response.RegisterResponse
import com.photo.picth.databinding.ActivityRegisterBinding
import com.photo.picth.utils.ui.SessionManager
import com.photo.picth.viewmodel.ForgotPasswordViewModel
import com.photo.picth.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private val viewModel1 by viewModels<ForgotPasswordViewModel>()
    private val viewModel by viewModels<RegisterViewModel>()
        private var _binding: ActivityRegisterBinding? = null

        val binding get() = _binding!!
        var gender = "Male"
        var mobileNumber = ""
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            supportActionBar!!.hide()
            _binding = ActivityRegisterBinding.inflate(layoutInflater)
            setContentView(binding.root)
            init()

        }

        private fun init(){
//            binding.imgSignIn.setOnClickListener {
//                val  mobileNumber = binding.etMobileNumber.text.toString()
//                val  password = binding.etPassword.text.toString()
//                val  name = binding.etPassword.text.toString()
//                if (mobileNumber.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()){
//                    val bundle = Bundle()
//                    bundle.putString("Activity", "RegisterActivity")
//                    val intent = Intent(this, OTPActivity::class.java)
//                    intent.putExtras(bundle)
//                    startActivity(intent)
//                    finish()
//                }else{
//                    binding.etMobileNumber.error = "Enter Mobile Number"
//                    binding.etPassword.error = "Enter Password"
//                    binding.etName.error = "Enter Name"
//                }
//
//            }

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

            val view = binding.root
            setContentView(view)
            val token = SessionManager.getToken(this)
            if (!token.isNullOrBlank()) {
                navigateToHome()
            }

            viewModel.RegisterResult.observe(this) {
                when (it) {
                    is BaseResponse.Loading -> {
                        showLoading()
                    }

                    is BaseResponse.Success -> {
                        stopLoading()
                        processLogin(it.data)
                    }

                    is BaseResponse.Error -> {
                        processError(it.msg)
                    }
                    else -> {
                        stopLoading()
                    }
                }
            }
            viewModel1.forgotPasswordResult.observe(this) {
                when (it) {
                    is BaseResponse.Loading -> {
                        showLoading()
                    }

                    is BaseResponse.Success -> {
                        stopLoading()
                        processOtp(it.data)
                    }

                    is BaseResponse.Error -> {
                        processError(it.msg)
                    }
                    else -> {
                        stopLoading()
                    }
                }
            }

            binding.imgSignIn.setOnClickListener {
                doLogin()

            }

//            binding.btnRegister.setOnClickListener {
//                doSignup()
//            }
        }

    private fun navigateToHome() {
        val bundle = Bundle()
        bundle.putString("Activity", "RegisterActivity")
        bundle.putString("mobileNumber", mobileNumber)
        val intent = Intent(this, OTPActivity::class.java)
        intent.putExtras(bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    fun doLogin() {
        mobileNumber = binding.etMobileNumber.text.toString()
        val  password = binding.etPassword.text.toString()
        val  name = binding.etName.text.toString()
        if (mobileNumber.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()){
            viewModel.RegisterUser(name = name, username = mobileNumber, pwd = password, gender = gender, role = "admin")
            viewModel1.ForgetPasswordUser( username = mobileNumber)
        }else{
            binding.etMobileNumber.error = "Enter Mobile Number"
            binding.etPassword.error = "Enter Password"
            binding.etName.error = "Enter Name"
        }

    }

    fun doSignup() {

    }

    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
        binding.sendOtp.visibility = View.GONE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
        binding.prgbar.visibility = View.GONE

    }

    fun processLogin(data: RegisterResponse?) {
        showToast("Success:" + data?.message)
        if (!data?.data?.accessToken.isNullOrEmpty()) {
            data?.data?.accessToken?.let { SessionManager.saveAuthToken(this, it) }
            navigateToHome()
        }
    }
    fun processOtp(data: ForgotPasswordResponse?) {
        showToast("Success:" + data?.message)
        if (!data?.message.isNullOrEmpty()) {
            //data?.data?.accessToken?.let { SessionManager.saveAuthToken(this, it) }
            //navigateToHome()
        }
    }

    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}
