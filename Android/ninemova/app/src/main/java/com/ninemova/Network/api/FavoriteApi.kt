package com.ninemova.Network.api

import com.ninemova.Network.request.server.FavoriteRequest
import com.ninemova.Network.response.server.FavoriteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoriteApi {

    @POST("favoriteMovie/create")
    suspend fun createFavorite(
        @Body request: FavoriteRequest
    ): Response<FavoriteResponse>

    @DELETE("favoriteMovie/delete/{userId}/{movieId}")
    suspend fun deleteFavorite(
        @Path("userId") userId: Int,
        @Path("movieId") movieId: Int,
    ): Response<Boolean>

    @GET("favoriteMovie/find/{userId}/{movieId}")
    suspend fun getFavorite(
        @Path("userId") userId: Int,
        @Path("movieId") movieId: Int,
    ): Response<Boolean>

    @GET("favoriteMovie/getList/{userId}")
    suspend fun getUserFavoriteMovies(
        @Path("userId") userId: Int,
    ): Response<List<String>>
}
