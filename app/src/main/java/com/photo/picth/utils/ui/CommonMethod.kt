package com.photo.picth.utils.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import com.photo.picth.presentation.BannerEditActivity
import com.photo.picth.ui.MainActivity
import com.photo.picth.ui.activities.auth.LoginActivity


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

          fun openWhatsAppWithMessage(context: Activity,message: String) {
            val uri = Uri.parse("https://api.whatsapp.com/send?phone=&text=$message")
            val sendIntent = Intent(Intent.ACTION_VIEW, uri)
            sendIntent.setPackage("com.whatsapp")

            try {
                context.startActivity(sendIntent)
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle the case where WhatsApp is not installed
            }
          }

          fun showLogoutDialog(context: Activity) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Logout")
            builder.setMessage("Are you sure you want to logout?")

            builder.setPositiveButton("Yes") { dialog, which ->
                // Handle logout logic here
                MainActivity.mInstance.startNewActivity(LoginActivity::class.java)

                dialog.dismiss()

            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        fun showToast(context: Activity,msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }


    }


}

