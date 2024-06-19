package com.photo.picth.ui.activities.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.photo.picth.data.api.response.VeryfyotpResponse
import com.photo.picth.databinding.ActivityOtpactivityBinding
import com.photo.picth.ui.MainActivity
import com.photo.picth.utils.ui.SessionManager
import com.photo.picth.viewmodel.LoginViewModel
import com.photo.picth.viewmodel.VeryfyotpViewModel

class OTPActivity : AppCompatActivity() {
    private var _binding: ActivityOtpactivityBinding? = null
    private val viewModel by viewModels<VeryfyotpViewModel>()
    val binding get() = _binding!!
    var tvId = ""
    var mobNo = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        _binding = ActivityOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        tvId = bundle!!.getString("Activity").toString()
        mobNo = bundle.getString("mobileNumber").toString()
        init()

    }

    private fun init(){
        if (tvId == "ForgotPasswordActivity"){
            binding.ivDot3.visibility = View.VISIBLE
        }
//        binding.clValidateOtp.setOnClickListener {
//                if (tvId == "ForgotPasswordActivity") {
//                    val otp1 = binding.et1.text.toString()
//                    val otp2 = binding.et2.text.toString()
//                    val otp3 = binding.et3.text.toString()
//                    val otp4 = binding.et4.text.toString()
//                    if (otp1.isNotEmpty() && otp2.isNotEmpty() && otp3.isNotEmpty() && otp4.isNotEmpty()) {
//                        val intent = Intent(this, ConfirmPasswordActivity::class.java)
//                        startActivity(intent)
//                        finish()
//                    }
//                } else {
//                    val otp1 = binding.et1.text.toString()
//                    val otp2 = binding.et2.text.toString()
//                    val otp3 = binding.et3.text.toString()
//                    val otp4 = binding.et4.text.toString()
//                    if (otp1.isNotEmpty() && otp2.isNotEmpty() && otp3.isNotEmpty() && otp4.isNotEmpty()) {
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                        finish()
//                    }
//                }
//
//        }
        binding.et1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.isNotEmpty() == true) {
                    binding.et1.background = getDrawable(R.drawable.bg_otp_box_selected)
                    binding.et2.requestFocus() // Move focus to the next box
                } else {
                    binding.et1.background = getDrawable(R.drawable.bg_otp_box_unselected)
                }
            }
        })

        binding.et2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.isNotEmpty() == true) {
                    binding.et2.background = getDrawable(R.drawable.bg_otp_box_selected)
                    binding.et3.requestFocus() // Move focus to the next box
                } else {
                    binding.et2.background = getDrawable(R.drawable.bg_otp_box_unselected)
                }
            }
        })
        binding.et3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.isNotEmpty() == true) {
                    binding.et3.background = getDrawable(R.drawable.bg_otp_box_selected)
                    binding.et4.requestFocus() // Move focus to the next box
                } else {
                    binding.et3.background = getDrawable(R.drawable.bg_otp_box_unselected)
                }
            }
        })
        binding.et4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.isNotEmpty() == true) {
                    binding.et4.background = getDrawable(R.drawable.bg_otp_box_selected)
                    binding.et4.requestFocus() // Move focus to the next box
                } else {
                    binding.et4.background = getDrawable(R.drawable.bg_otp_box_unselected)
                }
            }
        })

            // ... other TextWat
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val view = binding.root
        setContentView(view)
        val token = SessionManager.getToken(this)
//        if (!token.isNullOrBlank()) {
//            navigateToHome()
//        }

        viewModel.veryfyotpResult.observe(this) {
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

        binding.imgSignIn.setOnClickListener {
            doLogin()

        }

    }

    private fun navigateToHome() {
        if (tvId == "ForgotPasswordActivity") {
                val bundle = Bundle()
                bundle.putString("mobNo", mobNo)
                val intent = Intent(this, ConfirmPasswordActivity::class.java)
                intent.putExtras(bundle)
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(intent)

        } else {
            val intent = Intent(this, MainActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
        }

    }

    fun doLogin() {
        val otp1 = binding.et1.text.toString()
        val otp2 = binding.et2.text.toString()
        val otp3 = binding.et3.text.toString()
        val otp4 = binding.et4.text.toString()
        val pwd = otp1+otp2+otp3+otp4
        if (mobNo.isNotEmpty() && pwd.isNotEmpty() ){
            viewModel.VeryfyotpUser( username = mobNo, otp = pwd)
        }else{

            binding.et1.error = "Enter OTP"
        }

    }

    fun doSignup() {

    }

    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
        binding.validateOtp.visibility = View.GONE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
        binding.prgbar.visibility = View.GONE

    }

    fun processLogin(data: VeryfyotpResponse?) {
        showToast("Successfully verify the otp")
//        if (!data?.message.isNullOrEmpty()) {
            //data?.message?.let { SessionManager.saveAuthToken(this, it) }
            navigateToHome()
//        }
    }

    fun processError(msg: String?) {
        showToast("something went wrong")
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