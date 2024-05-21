package com.ninemova.ui.home

sealed interface HomeViewEvent {
    data object Success : HomeViewEvent
    data class Error(
        val errorCode: Int = 0,
        val errorMessage: String = "",
    ) : HomeViewEvent
}
