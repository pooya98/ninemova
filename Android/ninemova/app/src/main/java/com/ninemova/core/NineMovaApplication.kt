package com.ninemova.core

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ninemova.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "ninemova")

class NineMovaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val tmdbOkHttpClient = OkHttpClient.Builder()
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

        val openAiOkHttpClient = OkHttpClient.Builder()
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val serverOkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        tmdbRetrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(tmdbOkHttpClient)
            .build()

        youtubeRetrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.YOUTUBE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(youtubeOkHttpClient)
            .build()

        openAiRetrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.OPENAI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(openAiOkHttpClient)
            .build()

        serverRetrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(serverOkHttpClient)
            .build()
    }

    companion object {
        lateinit var tmdbRetrofit: Retrofit
        lateinit var youtubeRetrofit: Retrofit
        lateinit var openAiRetrofit: Retrofit
        lateinit var serverRetrofit: Retrofit
        private val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
    }
}
