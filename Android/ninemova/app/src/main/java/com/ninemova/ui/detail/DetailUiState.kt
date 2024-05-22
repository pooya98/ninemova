package com.ninemova.ui.detail

import com.ninemova.domain.data.Genre
import com.ninemova.domain.data.Movie

data class DetailUiState(
    val movie: Movie = Movie(),
    val genres: List<Genre> = listOf(),
    val videoId: String? = null,
    val isLiked: Boolean = false,
)
