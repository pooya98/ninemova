package com.ninemova.ui.community

import com.ninemova.domain.data.Comment

data class CommunityUiState(
    val comments: List<Comment> = listOf(),
)
