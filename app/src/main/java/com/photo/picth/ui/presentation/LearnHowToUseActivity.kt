package com.photo.picth.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.photo.picth.R

/**
 * A simple [Fragment] subclass.
 * Use the [LearnHowToUseActivity.newInstance] factory method to
 * create an instance of this fragment.
 */
class LearnHowToUseActivity : AppCompatActivity(R.layout.fragment_learn_how_to_use) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        supportActionBar!!.hide()

    }


}