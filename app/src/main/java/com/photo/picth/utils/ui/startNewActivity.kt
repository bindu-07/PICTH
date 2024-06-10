package com.photo.picth.utils.ui

import android.app.Activity
import android.content.Intent

fun <T : Activity> Activity.startNewActivity(activityClass: Class<T>) {
    val intent = Intent(this, activityClass)
    startActivity(intent)
}
