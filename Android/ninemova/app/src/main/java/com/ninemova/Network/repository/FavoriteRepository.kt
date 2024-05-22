package com.ninemova.Network.repository

import com.ninemova.Network.request.FavoriteRequest
import com.ninemova.Network.response.FavoriteResponse
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun createFavorite(request: FavoriteRequest): Flow<FavoriteResponse?>
    suspend fun deleteFavorite(userId: Int, movieId: Int): Flow<Boolean>
    suspend fun getFavorite(userId: Int, movieId: Int): Flow<Boolean>
}
