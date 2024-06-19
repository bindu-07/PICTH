package com.photo.picth.utils.ui

import android.content.Context
import android.content.SharedPreferences
import com.photo.picth.R

object SessionManager {

    const val USER_TOKEN = "user_token"
    const val USER_REFRESH_TOKEN = "user_refresh_token"

    /**
     * Function to save auth token
     */
    fun saveAuthToken(context: Context, token: String) {
        saveString(context, USER_TOKEN, token)
    }

    /**
     * Function to fetch auth token
     */
    fun getToken(context: Context): String? {
        return getString(context, USER_TOKEN)
    }

    fun saveRefreshToken(context: Context, token: String) {
        saveString(context, USER_REFRESH_TOKEN, token)
    }

    /**
     * Function to fetch auth token
     */
    fun getRefreshToken(context: Context): String? {
        return getString1(context, USER_REFRESH_TOKEN)
    }
    fun saveString(context: Context, key: String, value: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()

    }

    fun getString(context: Context, key: String): String? {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getString(this.USER_TOKEN, null)
    }
    fun getString1(context: Context, key: String): String? {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getString(this.USER_REFRESH_TOKEN, null)
    }

    fun clearData(context: Context){
        val editor = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }
}