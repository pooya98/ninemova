package com.ninemova.Network.repositoryimpl

import com.ninemova.Network.RetrofitUtils
import com.ninemova.Network.repository.FavoriteRepository
import com.ninemova.Network.request.FavoriteRequest
import com.ninemova.Network.response.FavoriteResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoriteRepositoryImpl : FavoriteRepository {

    private val favoriteApi = RetrofitUtils.favoriteApi

    override suspend fun createFavorite(request: FavoriteRequest): Flow<FavoriteResponse?> = flow {
        runCatching {
            favoriteApi.createFavorite(request)
        }.onSuccess { response ->
            response.body()?.let { favorite ->
                emit(favorite)
            } ?: run {
                emit(null)
            }
        }.onFailure {
            emit(null)
        }
    }

    override suspend fun deleteFavorite(userId: Int, movieId:Int): Flow<Boolean> = flow {
        runCatching {
            favoriteApi.deleteFavorite(userId,movieId)
        }.onSuccess { response ->
            response.body()?.let { result ->
                emit(result)
            }
        }.onFailure {
            emit(false)
        }
    }

    override suspend fun getFavorite(userId: Int, movieId: Int): Flow<Boolean> = flow {
        runCatching {
            favoriteApi.getFavorite(userId, movieId)
        }.onSuccess { response ->
            response.body()?.let { result ->
                emit(result)
            } ?: run {
                emit(false)
            }
        }.onFailure {
            emit(false)
        }
    }
}
