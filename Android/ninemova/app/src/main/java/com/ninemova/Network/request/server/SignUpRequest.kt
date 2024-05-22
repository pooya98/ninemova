package com.ninemova.Network.request.server

data class SignUpRequest(
    val userName: String? = "",
    val passWord: String? = "",
    val nickName: String? = "",
)
