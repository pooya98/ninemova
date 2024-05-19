package com.ninemova.Network.api

import com.ninemova.Network.request.openai.OpenAIRequest
import com.ninemova.Network.response.openai.OpenAIResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenAiApi {

    @POST("v1/chat/completions")
    suspend fun getChatCompletion(
        @Header("Authorization") authHeader: String,
        @Body request: OpenAIRequest
    ): OpenAIResponse
}
