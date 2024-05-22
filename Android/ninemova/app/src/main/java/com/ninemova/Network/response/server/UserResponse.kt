package com.ninemova.Network.response.server

data class UserResponse(
    val id: Int? = 0,
    val userName: String? = "",
    val password: String? = "",
    val nickName: String? = "",
    val profileImageUrl: String? = "",
)
