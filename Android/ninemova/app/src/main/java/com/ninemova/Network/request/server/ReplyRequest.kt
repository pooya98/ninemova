package com.ninemova.Network.request.server

data class ReplyRequest(
    val commentId: Int,
    val userId: Int,
    val content: String,
)
