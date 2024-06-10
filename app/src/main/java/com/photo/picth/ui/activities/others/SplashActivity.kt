package com.photo.picth.ui.activities.others

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.photo.picth.R
import com.photo.picth.ui.MainActivity
import com.photo.picth.ui.activities.auth.LoginActivity
import com.photo.picth.utils.ui.AppController
import com.photo.picth.utils.ui.Constants

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            if (AppController.mInstance.getBoolean(Constants.IS_LOGIN)) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 600)
    }
}