package com.ninemova.ui.login.signup

data class SignUpUiState(
    val id: String? = "",
    val password: String? = "",
    val rePassword: String? = "",
    val nickName: String? = "",
    val isValidated: Boolean? = false,
)
