package com.ninemova.core

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NineMovaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(500, TimeUnit.MILLISECONDS)
            .connectTimeout(500, TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(AddHeaderInterceptor())
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(NetworkConst.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    companion object {
        lateinit var retrofit: Retrofit
        private val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
    }
}
