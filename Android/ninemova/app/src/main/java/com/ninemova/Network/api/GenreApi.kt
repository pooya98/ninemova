package com.ninemova.Network.api

import com.ninemova.Network.response.tmdb.GetGenresResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreApi {

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("language") language: String = "ko",
    ): Response<GetGenresResponse>
}