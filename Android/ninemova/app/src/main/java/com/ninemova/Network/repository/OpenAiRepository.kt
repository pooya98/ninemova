package com.ninemova.Network.repository

interface OpenAiRepository {
    suspend fun getChatResponse(prompt: String, apiKey: String): String?
}
