package com.ninemova.ui.search

sealed interface SearchViewEvent {
    data object Success : SearchViewEvent
    data class Error(
        val errorCode: Int = 0,
        val errorMessage: String = "",
    ) : SearchViewEvent
}
