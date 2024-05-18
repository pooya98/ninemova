package com.ninemova.core

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ninemova.BuildConfig
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

        val youtubeOkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

        youtubeRetrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.YOUTUBE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(youtubeOkHttpClient)
            .build()
    }

    companion object {
        lateinit var retrofit: Retrofit
        lateinit var youtubeRetrofit: Retrofit
        private val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
    }
}