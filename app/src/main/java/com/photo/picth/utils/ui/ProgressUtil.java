package com.photo.picth.utils.ui;



import android.app.ProgressDialog;

import android.content.Context;

import android.graphics.Color;

import android.graphics.drawable.ColorDrawable;

import com.photo.picth.R;


public class ProgressUtil {

    public static ProgressDialog mProgress;

    private static boolean isLoader=false;

    private ProgressUtil() {

    }


    public static void showProgress(Context mContext) {
        try {
            if (mProgress == null) {
                mProgress = new ProgressDialog(mContext);
                mProgress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            mProgress.show();
            mProgress.setContentView(R.layout.progress_layout);
            mProgress.setCancelable(false);
        } catch (Exception e) {
            e.printStackTrace();
            mProgress = null;
        }

    }

    // hide the common progress which is used in all application.
    public static void hideProgress() {
        isLoader=true;
        try {
            if (mProgress != null) {
                mProgress.hide();
                mProgress.dismiss();
                mProgress = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mProgress = null;
        }
    }


}