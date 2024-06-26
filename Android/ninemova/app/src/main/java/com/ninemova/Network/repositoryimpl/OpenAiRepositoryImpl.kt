package com.ninemova.Network.repositoryimpl

import com.ninemova.Network.repository.OpenAiRepository
import com.ninemova.Network.request.openai.OpenAIRequest
import com.ninemova.Network.response.openai.Message
import com.ninemova.Network.utils.RetrofitUtils

class OpenAiRepositoryImpl : OpenAiRepository {

    private val openAIApi = RetrofitUtils.openAiAPI

    override suspend fun getChatResponse(prompt: String, apiKey: String): String? {
        val request = OpenAIRequest(
            model = "gpt-4o",
            messages = listOf(Message(role = "user", content = prompt)),
        )
        val response = openAIApi.getChatCompletion("Bearer $apiKey", request)
        return response.choices.firstOrNull()?.message?.content
    }
}
