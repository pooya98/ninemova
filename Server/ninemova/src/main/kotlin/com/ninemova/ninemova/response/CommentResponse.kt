package com.ninemova.ninemova.response

import com.ninemova.ninemova.dto.Comment
import com.ninemova.ninemova.dto.User

data class CommentResponse(
    val comment: Comment,
    val user: User,
)
