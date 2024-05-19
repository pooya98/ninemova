package com.ninemova.Network.response.openai

data class AnalysisResult(
    val question: String,
    val answer: AnswerBody
)