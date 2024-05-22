package com.ninemova.Network.request

data class ReplyRequest(
    val commentId: Int,
    val userId: Int,
    val content: String,
)
