package com.ninemova.ui.login

sealed interface LoginViewEvent {

    data object NavigateToMain : LoginViewEvent
    data class Error(val errorMessage: String) : LoginViewEvent
}
