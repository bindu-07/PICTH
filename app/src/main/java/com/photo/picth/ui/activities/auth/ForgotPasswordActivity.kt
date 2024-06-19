package com.photo.picth.ui.activities.auth

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.photo.picth.R
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.data.api.response.ForgotPasswordResponse
import com.photo.picth.databinding.ActivityForgotPasswordBinding
import com.photo.picth.utils.ui.AppController
import com.photo.picth.viewmodel.ForgotPasswordViewModel

class ForgotPasswordActivity : AppCompatActivity() {
    private var _binding: ActivityForgotPasswordBinding? = null
    private val viewModel by viewModels<ForgotPasswordViewModel>()

    val binding get() = _binding!!
    var mobileNumber = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        _binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    private fun init(){
//        binding.clSendOtp.setOnClickListener {
//            val  mobileNumber = binding.etMobileNumber.text.toString()
//            if(mobileNumber.isNotEmpty()){
//                val bundle = Bundle()
//                bundle.putString("Activity", "ForgotPasswordActivity")
//                val intent = Intent(this, OTPActivity::class.java)
//                intent.putExtras(bundle)
//                startActivity(intent)
//                finish()
//            }else{
//                binding.etMobileNumber.error = "Please enter mobile number"
//            }
//
//        }
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
            finish()
        }

        val view = binding.root
        setContentView(view)
        val token =  AppController.mInstance.getAuth()
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }
        val token = SessionManager.getToken(this)
//        if (!token.isNullOrBlank()) {
//            navigateToHome()
//        }

        viewModel.forgotPasswordResult.observe(this) {
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

        binding.clSendOtp.setOnClickListener {
            doLogin()

        }

    }

    private fun navigateToHome() {
        val bundle = Bundle()
        bundle.putString("Activity", "ForgotPasswordActivity")
        bundle.putString("mobileNumber", mobileNumber)
        val intent = Intent(this, OTPActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun doLogin() {
          mobileNumber = binding.etMobileNumber.text.toString()
        //val  password = binding.etPassword.text.toString()
        if (mobileNumber.isNotEmpty() ){
            viewModel.ForgetPasswordUser( username = mobileNumber)
        }else{
            binding.etMobileNumber.error = "Enter Mobile Number"
            //binding.etPassword.error = "Enter Password"
        }

    }

    fun doSignup() {

    }

    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
        binding.otpSend.visibility = View.GONE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
        binding.otpSend.visibility = View.VISIBLE

    }

    fun processLogin(data: ForgotPasswordResponse?) {
        showToast("Success:" + data?.message)
        if (!data?.message?.isNullOrEmpty()!!) {
            data?.message?.let {  AppController.mInstance.setAuth( it) }
            navigateToHome()
        }
        showToast("Successfully send otp")
        navigateToHome()
//        if (!data?.message?.isNullOrEmpty()!!) {
//            data?.message?.let { SessionManager.saveAuthToken(this, it) }
//
//        }
    }

    fun processError(msg: String?) {
        showToast("something went wrong!" )
    }

    fun showToast(sText: String) {
        val toast = Toast.makeText(this, sText, Toast.LENGTH_LONG)
        var inflater: LayoutInflater = getLayoutInflater();
        var toastRoot: View = inflater.inflate(R.layout.toast, null)
        toast.setView(toastRoot)


        // set a message
        var text: TextView = toastRoot.findViewById<View>(R.id.tvToast) as TextView
        text.setText(sText)

        toast.setGravity(
            Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
            0, 0)
        toast.setDuration(Toast.LENGTH_SHORT)
        toast.show()
    }
}