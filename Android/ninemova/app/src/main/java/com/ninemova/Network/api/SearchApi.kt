package com.ninemova.Network.api

import com.ninemova.Network.response.MovieResult
import com.ninemova.Network.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {

    @GET("search/movie")
    suspend fun getMovies(
        @Query("include_adult") includeAdult: Boolean,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("query") query: String,
    ): Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") moveId: Int,
        @Query("language") language: String,
    ): Response<MovieResult>
}
