package com.photo.picth.utils.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

import com.photo.picth.ui.activities.auth.LoginActivity
import com.photo.picth.ui.activities.others.SplashActivity
import com.photo.picth.utils.ui.Constants.Companion.AUTH_KEY
import com.photo.picth.utils.ui.Constants.Companion.DEVICE_TOKEN
import com.photo.picth.utils.ui.Constants.Companion.FCM_TOKEN


import java.util.*

class AppController : Application(), LifecycleObserver {
    private lateinit var preferences: SharedPreferences
    private lateinit var prefToken: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var editorToken: SharedPreferences.Editor
    private val mPrefToken = "Mentor"


    companion object {
        @JvmStatic
        lateinit var mInstance: AppController
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        //  setLocale()

    }

    private fun setLocale() {
        val locale = Locale("nl")
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this



        initializePreferences()
        initializePreferencesToken()

      //  ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        Log.d("MyApp", "App in background")

      //  println("+___________ current  " + ProcessLifecycleOwner.get().lifecycle.currentState)

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        Log.d("MyApp", "App in foreground")

    }

    @Synchronized
    fun getInstance(): AppController {
        return mInstance
    }

    fun hasNetwork(): Boolean {
        return mInstance.checkIfHasNetwork()
    }

    // initialize shared preferences
    @SuppressLint("CommitPrefEdits")
    private fun initializePreferences() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        editor = preferences.edit()
    }

    // initialize shared preferences for token
    @SuppressLint("CommitPrefEdits")
    private fun initializePreferencesToken() {
        prefToken = getSharedPreferences(mPrefToken, Context.MODE_PRIVATE)
        editorToken = prefToken.edit()
    }

    // save string
    fun setString(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
    }

    // get string
    fun getString(key: String): String {
        return preferences.getString(key, "")!!
    }

    // save string
    fun setInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.commit()

    }

    // get string
    fun getInt(key: String): Int {
        return preferences.getInt(key, 0)!!
    }

    //save boolean
    fun setBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.commit()
    }

    // get Boolean
    fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    //save boolean
    fun setFirstInstall(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.commit()
    }

    // get Boolean
    fun getFirstInstall(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    // clear preferences
    fun clearData() {
        try {


            preferences.edit().clear().apply()
        }catch (ex:Exception){

        }

    }

    fun setAuth(value: String) {
        editorToken.putString(AUTH_KEY, value)
        editorToken.commit()
    }

    // get device token
    fun getAuth(): String {
        return prefToken.getString(AUTH_KEY, "")!!
    }


    fun setDeviceToken(value: String) {
        editorToken.putString(DEVICE_TOKEN, value)
        editorToken.commit()
    }

    // get device token
    fun getDeviceToken(): String {
        return prefToken.getString(DEVICE_TOKEN, "232376")!!
    }

    fun setFcmToken(value: String) {
        editorToken.putString(FCM_TOKEN, value)
        editorToken.commit()
    }

    // get device token
    fun getFcmToken(): String {
        return prefToken.getString(FCM_TOKEN, "232376")!!
    }


    @Suppress("DEPRECATION")
    private fun checkIfHasNetwork(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    @Suppress("DEPRECATION")
    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }




    fun setSelectedLanguage(selectedLanguage: String?) {
        editor.putString("selectedLanguage", selectedLanguage)
        editor.commit()
    }

    fun getSelectedLanguage(): String? {
        return preferences.getString("selectedLanguage", "")
    }

    fun tokenExpire() {
        clearData()
        AppController.mInstance.setBoolean(Constants.IS_LOGIN,false)
        val intent = Intent(mInstance, LoginActivity::class.java)
        startActivity(intent)


    }





}