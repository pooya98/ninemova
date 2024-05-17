package com.ninemova.core

import okhttp3.Interceptor
import okhttp3.Response

class AddHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder().run {
            header(
                NetworkConst.AUTHORIZATION_NAME,
                NetworkConst.AUTHORIZATION_VALUE + " " + NetworkConst.ACCESS_TOKEN,
            )
            header(
                NetworkConst.CONTENT_TYPE_NAME,
                NetworkConst.CONTENT_TYPE_VALUE,
            )
        }.build()
        return chain.proceed(requestBuilder)
    }
}
