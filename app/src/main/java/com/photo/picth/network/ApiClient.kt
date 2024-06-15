package com.photo.picth.network

import com.photo.picth.utils.ui.AppController
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

class Injector {

    companion object {
        fun provideRetrofit(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun okHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor()) // used if network off OR on
                .addNetworkInterceptor(networkInterceptor()) // only used when network is on
                .addInterceptor(offlineInterceptor())
                .addInterceptor(ForbiddenInterceptor())
                .cache(myCache)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .followRedirects(false)
                .followSslRedirects(false)
                .build()
        }

        private val cacheSize = (5 * 1024 * 1024 // 5 MB
                ).toLong()
        var httpCacheDirectory: File = File(AppController.mInstance.getCacheDir(), "responses")

        val myCache = Cache(httpCacheDirectory, cacheSize)

        private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            //httpLoggingInterceptor.level = if (BuildConfig.DEBUG) BODY else NONE
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }

        private fun networkInterceptor(): Interceptor {
            return Interceptor { chain ->
                val helper = AppController.mInstance
                val cacheControl: CacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
                val token=AppController.mInstance.getAuth()
                val request: Request
                request = if (token.isNotEmpty()) {
                    chain.request().newBuilder()
                        .header("Authorization", "Bearer " + token)
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

        private fun offlineInterceptor(): Interceptor {
            return object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {

                    var request: Request = chain.request()
                    val helper = AppController.mInstance
                    // prevent caching when network is on. For that we use the "networkInterceptor"
                    if (!AppController.mInstance.hasNetwork()) {

                        request = request.newBuilder()
                            .header("Authorization", "Bearer " + helper.getDeviceToken())
                            // .header("Accept", "application/json")
                            .removeHeader("Pragma")

                            //  .removeHeader("Cache-Control")
                            .header(
                                "Cache-Control",
                                "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                            )

                            .build()
                    }
                    return chain.proceed(request)
                }
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
}