package com.ninemova.ui.recommend

sealed interface RecommendViewEvent {
    data object Success : RecommendViewEvent
    data class Error(
        val errorCode: Int = 0,
        val errorMessage: String = "",
    ) : RecommendViewEvent
}
