package com.photo.picth.ui.activities.music

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.photo.picth.R

class OnlineMusicListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        supportActionBar!!.hide()
        setContentView(R.layout.activity_online_music_list)
    }
}