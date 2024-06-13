package com.photo.picth.ui.activities.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.photo.picth.R
import com.photo.picth.databinding.ActivityLoginBinding
import com.photo.picth.databinding.ActivityOtpactivityBinding
import com.photo.picth.ui.MainActivity

class OTPActivity : AppCompatActivity() {
    private var _binding: ActivityOtpactivityBinding? = null
    val binding get() = _binding!!
    var tvId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        _binding = ActivityOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        tvId = bundle!!.getString("Activity").toString()
        init()

    }

    private fun init(){
        if (tvId == "ForgotPasswordActivity"){
            binding.ivDot3.visibility = View.VISIBLE
        }
        binding.clValidateOtp.setOnClickListener {
                if (tvId == "ForgotPasswordActivity") {
                    val otp1 = binding.et1.text.toString()
                    val otp2 = binding.et2.text.toString()
                    val otp3 = binding.et3.text.toString()
                    val otp4 = binding.et4.text.toString()
                    if (otp1.isNotEmpty() && otp2.isNotEmpty() && otp3.isNotEmpty() && otp4.isNotEmpty()) {
                        val intent = Intent(this, ConfirmPasswordActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    val otp1 = binding.et1.text.toString()
                    val otp2 = binding.et2.text.toString()
                    val otp3 = binding.et3.text.toString()
                    val otp4 = binding.et4.text.toString()
                    if (otp1.isNotEmpty() && otp2.isNotEmpty() && otp3.isNotEmpty() && otp4.isNotEmpty()) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

        }
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

    }
}