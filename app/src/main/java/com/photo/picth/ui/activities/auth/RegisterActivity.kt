package com.photo.picth.ui.activities.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.photo.picth.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        EdgeToEdge.enable(this);
        supportActionBar!!.hide()
        setContentView(R.layout.activity_register)

    }
}