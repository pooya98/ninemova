package com.ninemova.ui.post

import com.ninemova.domain.data.Comment
import com.ninemova.domain.data.Reply

data class PostUiState(
    val replies: List<Reply> = listOf(),
    val sendContent: String? = null,
    val comment: Comment = Comment(),
)
