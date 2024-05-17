package com.ninemova.ui.detail

import com.ninemova.domain.data.Genre
import com.ninemova.domain.data.Movie

data class DetailUiState(
    val movie: Movie? = null,
    val genres: List<Genre> = listOf(),
)
