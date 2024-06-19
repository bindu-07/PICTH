package com.photo.picth.utils.ui

import android.text.Html
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.photo.picth.R
import com.photo.picth.utils.ui.AppController

class Util {
    companion object {

        fun showSnackBar(mParentview: View, message: String) {
            val snackbar = Snackbar.make(
                mParentview,
                Html.fromHtml("<font color=\"#ffffff\">" + message + "</font>"),
                Snackbar.LENGTH_LONG
            )
            snackbar.setBackgroundTint(ContextCompat.getColor(AppController.mInstance, R.color.black));

//            val snackBarView = snackbar.view
//            snackBarView.setBackgroundColor(mParentview.resources.getColor(R.color.black))
//
//

            snackbar.show()
        }


        /*
                fun showAlertDialog(message: String, signupActivity: SignupActivity) {
                    Alerter.create(signupActivity)
                            .setTitle("Alert Title")
                            .setText("Alert text...")
                            .show()
                }
        */


    }

}