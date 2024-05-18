package com.ninemova.core

import com.ninemova.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AddHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder().run {
            header(
                BuildConfig.AUTHORIZATION_NAME,
                BuildConfig.AUTHORIZATION_VALUE + " " + BuildConfig.TMDB_ACCESS_TOKEN,
            )
            header(
                BuildConfig.CONTENT_TYPE_NAME,
                BuildConfig.CONTENT_TYPE_VALUE,
            )
        }.build()
        return chain.proceed(requestBuilder)
    }
}
