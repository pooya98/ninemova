package com.ninemova.ui.post

sealed interface PostViewEvent {

    data object Success : PostViewEvent

    data class Error(val errorMessage: String) : PostViewEvent
}
