package com.ninemova.Network.request.openai

import com.ninemova.Network.response.openai.Message

data class OpenAIRequest(
    val model: String,
    val messages: List<Message>
)
