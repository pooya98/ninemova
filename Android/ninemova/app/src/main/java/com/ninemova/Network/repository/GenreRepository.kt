package com.ninemova.Network.repository

import com.ninemova.domain.data.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    suspend fun getGenres(genres: List<Int>): Flow<List<Genre>>
}
