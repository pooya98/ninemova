package com.ninemova.ui.detail

sealed interface DetailViewEvent {
    data class Error(val errorMessage: String) : DetailViewEvent

    data object Success : DetailViewEvent
}
