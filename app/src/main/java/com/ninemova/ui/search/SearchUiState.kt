package com.ninemova.ui.search

import com.ninemova.domain.data.Movie

data class SearchUiState(
    val query: String? = "",
    val movies: List<Movie> = listOf(),
)
