package com.ninemova.ui.recommend

sealed interface RecommendViewEvent {

    data class ChatGptSuccess(
        val flagCode: Int = 0
    ) : RecommendViewEvent

    data class TmdbApiSuccess(
        val flagCode: Int = 0
    ) : RecommendViewEvent

    data class Error(
        val errorCode: Int = 0,
        val errorMessage: String = "",
    ) : RecommendViewEvent

    data class ChatGptError(
        val errorCode: Int = 0,
    ) : RecommendViewEvent

    data class TmdbApiError(
        val errorCode: Int = 0,
    ) : RecommendViewEvent
}
