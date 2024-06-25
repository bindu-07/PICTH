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
import com.photo.picth.data.api.response.LoginResponse
import com.photo.picth.databinding.ActivityLoginBinding
import com.photo.picth.ui.MainActivity
import com.photo.picth.utils.ui.AppController
import com.photo.picth.utils.ui.Constants
import com.photo.picth.utils.ui.SessionManager
import com.photo.picth.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val viewModel by viewModels<LoginViewModel>()
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

//        binding.imgSignIn.setOnClickListener {
//            val  mobileNumber = binding.etMobileNumber.text.toString()
//            val  password = binding.etPassword.text.toString()
//            if (mobileNumber.isNotEmpty() && password.isNotEmpty()){
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }else{
//                binding.etMobileNumber.error = "Please enter mobile number"
//                binding.etPassword.error = "Please enter password"
//            }
//        }
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

        val view = binding.root
        setContentView(view)
        val token = AppController.mInstance.getAuth()
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }

        viewModel.loginResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processLogin(it.data)
                }

                is BaseResponse.Error -> {
                    showToast("something went wrong!" )
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.imgSignIn.setOnClickListener {
            doLogin()

        }


    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
        finish()
    }

    fun doLogin() {
        val  mobileNumber = binding.etMobileNumber.text.toString()
        val  password = binding.etPassword.text.toString()
        if (mobileNumber.isNotEmpty() && password.isNotEmpty() ){
            viewModel.LoginUser( username = mobileNumber, pwd = password)
        }else{
            binding.etMobileNumber.error = "Enter Mobile Number"
            binding.etPassword.error = "Enter Password"
        }

    }

    fun doSignup() {

    }

    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
        binding.labelLogin.visibility = View.GONE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
        binding.labelLogin.visibility = View.VISIBLE

    }

    fun processLogin(data: LoginResponse?) {
        //showToast(this,"Success:" + data?.message)
        if (data?.message == "User details fetch successfully") {
            data.data.accessToken?.let {
                AppController.mInstance.setAuth(it)
             }
            AppController.mInstance.getBoolean(Constants.IS_LOGIN)
            showToast("Successfully login")
        //if (data?.message == "User details fetch successfully") {
            data?.data?.accessToken?.let { SessionManager.saveAuthToken(this, it) }
            data?.data?.refreshToken?.let { SessionManager.saveRefreshToken(this, it) }
            navigateToHome()
        //}
    }

    fun processError(msg: String?) {
        showToast("something went wrong!" )
        //showToast(this,"Error:" + msg)
    }


    fun showToast(sText: String) {

    }
}

    private fun showToast(sText: String) {
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