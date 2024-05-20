package com.ninemova.ui.login

data class LoginUiState(
    val id: String? = "",
    val password: String? = "",
    val isValidated: Boolean? = false,
)
