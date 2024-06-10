package com.photo.picth.utils.ui

import android.app.Activity
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity


class CommonMethod {
    companion object {
        fun shareApp(context: Activity) {
            val shareText =
                "Check out this amazing app: https://play.google.com/store/apps/details?id=" + context.packageName

            val shareIntent = Intent()
            shareIntent.setAction(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)
            shareIntent.setType("text/plain")

            val chooser = Intent.createChooser(shareIntent, "Share App Via")
            if (shareIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(chooser)
            }
        }

    }

    // Extension function to start an activity
    fun <T : Activity> Activity.startActivity(activityClass: Class<T>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}

