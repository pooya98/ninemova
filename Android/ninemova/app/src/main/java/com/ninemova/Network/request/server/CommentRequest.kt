package com.ninemova.Network.request.server

data class CommentRequest(
    val id: Int? = null,
    val userId: Int? = null,
    val movieId: Int? = null,
    val score: Double? = null,
    val content: String? = null,
)
