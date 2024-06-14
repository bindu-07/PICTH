package com.photo.picth.ui.activities.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.data.api.response.ResetPasswordResponse
import com.photo.picth.databinding.ActivityConfirmPasswordBinding
import com.photo.picth.ui.MainActivity
import com.photo.picth.utils.ui.AppController
import com.photo.picth.viewmodel.ResetPasswordViewModel

class ConfirmPasswordActivity : AppCompatActivity() {
    private var _binding: ActivityConfirmPasswordBinding? = null
    private val viewModel by viewModels<ResetPasswordViewModel>()
    val binding get() = _binding!!
    var mobNo = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        _binding = ActivityConfirmPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        mobNo = bundle!!.getString("mobNo").toString()
        init()

    }

    private fun init(){
//        binding.imgSignIn.setOnClickListener {
            val newPassword = binding.etNewPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()
//            if (newPassword.isNotEmpty()  && confirmPassword.isNotEmpty() ){
//                if (newPassword == confirmPassword){
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }else{
//                    binding.etConfirmPassword.error = "Password does not match"
//                }
//            }else{
//                binding.etNewPassword.error = "Password is empty"
//                binding.etConfirmPassword.error = "Confirm password is empty"
//            }
//        }
//        binding.tvRegister.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

        val view = binding.root
        setContentView(view)
        val token = AppController.mInstance.getAuth()
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }

        viewModel.resetPasswordResult.observe(this) {
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

        binding.clResetPassword.setOnClickListener {
            doLogin()

        }

    }

    private fun navigateToHome() {
//        val bundle = Bundle()
//        bundle.putString("Activity", "ForgotPasswordActivity")
        val intent = Intent(this, MainActivity::class.java)
        //intent.putExtras(bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    fun doLogin() {
        val  newpassword = binding.etNewPassword.text.toString()
        val  confirmpassword = binding.etConfirmPassword.text.toString()
        if (newpassword.isNotEmpty() && confirmpassword.isNotEmpty() ){
            if (newpassword == confirmpassword){
                viewModel.ResetPasswordUser( username = mobNo, password = newpassword)

            }else{
                binding.etConfirmPassword.error = "Password does not match"
            }
        }else{
            binding.etNewPassword.error = "Enter new Password"
            binding.etConfirmPassword.error = "Enter confirm Password"
        }

    }

    fun doSignup() {

    }

    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
        binding.cnfPassword.visibility = View.GONE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
        binding.cnfPassword.visibility = View.VISIBLE

    }

    fun processLogin(data: ResetPasswordResponse?) {
        showToast("Success:" + data?.message)
        if (!data?.data?.accessToken.isNullOrEmpty()) {
//            data?.data?.accessToken?.let { SessionManager.saveAuthToken(this, it) }
            navigateToHome()
        }
    }

    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}