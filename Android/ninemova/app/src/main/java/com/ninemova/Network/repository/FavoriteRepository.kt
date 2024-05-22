package com.ninemova.Network.repository

import com.ninemova.Network.request.server.FavoriteRequest
import com.ninemova.Network.response.server.FavoriteResponse
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun createFavorite(request: FavoriteRequest): Flow<FavoriteResponse?>
    suspend fun deleteFavorite(userId: Int, movieId: Int): Flow<Boolean>
    suspend fun getFavorite(userId: Int, movieId: Int): Flow<Boolean>

    suspend fun getUserFavoriteMovies(userId: Int): Flow<List<String>?>
}
