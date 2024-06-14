package com.photo.picth.network

import android.text.TextUtils
import com.photo.picth.utils.ui.ApiConsts
import com.photo.picth.utils.ui.AppController
import com.photo.picth.utils.ui.Constants
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object ApiClient {
    var mHttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    var mOkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(mHttpLoggingInterceptor)
        .addNetworkInterceptor(networkInterceptor()) // only used when network is on
        .addInterceptor(ForbiddenInterceptor())
        .build()

    var mRetrofit: Retrofit? = null



    val client: Retrofit?
        get() {
            if(mRetrofit == null){
                mRetrofit = Retrofit.Builder()
                    .baseUrl(ApiConsts.BASE_URL)
                    .client(mOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())

                    .build()
            }
            return mRetrofit
        }

    private fun networkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val helper = AppController.mInstance
            val cacheControl: CacheControl = CacheControl.Builder()
                .maxStale(7, TimeUnit.DAYS)
                .build()
            val request: Request = if (!TextUtils.isEmpty(helper.getString(AppController.mInstance.getAuth()))) {
                chain.request().newBuilder()
                    .header("Authorization", "Bearer " + helper.getString(AppController.mInstance.getAuth()))
                    .header("Accept", "application/json")
                    // .header("security_key", Constants.SECURITY_KEY)
                    .header("Cache-Control", "public, max-age=" + 6000)
                    .removeHeader("Pragma")
                    .cacheControl(cacheControl)
                    .build()
            } else {
                chain.request().newBuilder().build()
            }
            chain.proceed(request)
        }
    }
    class ForbiddenInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val response = chain.proceed(request);


            if (response.code == 401) {
                println("++++++_______=====  " + response.message)
                AppController.mInstance.tokenExpire()
            }

            return response
        }
    }
}