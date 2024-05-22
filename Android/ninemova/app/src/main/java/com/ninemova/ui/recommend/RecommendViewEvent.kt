package com.ninemova.ui.recommend

sealed interface RecommendViewEvent {
    data object SearchSuccess : RecommendViewEvent
    data class Error(
        val errorCode: Int = 0,
        val errorMessage: String = "",
    ) : RecommendViewEvent
}
