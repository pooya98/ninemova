package com.ninemova.Network.response

data class SignUpResponse(
    val id: Int? = 0,
    val userName: String? = "",
    val password: String? = "",
    val nickName: String? = "",
    val profileImageUrl: String? = "",
)
