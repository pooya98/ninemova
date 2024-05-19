package com.ninemova.ui.login.signup

sealed interface SignUpViewEvent {
    data object Success : SignUpViewEvent
    data class Error(val errorMessage: String) : SignUpViewEvent
}
