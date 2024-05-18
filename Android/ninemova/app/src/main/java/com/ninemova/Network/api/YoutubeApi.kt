package com.ninemova.Network.api

import com.ninemova.BuildConfig
import com.ninemova.Network.response.youtube.YoutubeSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {
    @GET("search")
    suspend fun getMovieTrailer(
        @Query("key") apiKey: String = BuildConfig.YOUTUBE_API_KEY,
        @Query("part") part: String = "snippet",
        @Query("q") movieName: String,
    ): Response<YoutubeSearchResponse>
}
