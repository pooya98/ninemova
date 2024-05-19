package com.ninemova.Network.response.openai

data class OpenAIResponse (
    val id: String,
    val choices: List<Choice>
)
