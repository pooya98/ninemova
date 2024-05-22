package com.ninemova.Network.response.server

data class CommentsResponse(
    val comment: CommentResponse,
    val user: UserResponse,
)

data class CommentResponse(
    val id: Int? = null,
    val userId: Int? = null,
    val movieId: Int? = null,
    val score: Double? = null,
    val content: String? = null,
)
