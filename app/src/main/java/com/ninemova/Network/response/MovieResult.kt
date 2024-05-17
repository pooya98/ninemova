package com.ninemova.Network.response

import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    val id: Int? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overView: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("media_type")
    val mediaType: String,
    val adult: Boolean? = null,
    val title: String? = null,
    @SerializedName("original_language")
    val language: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int> = listOf(),
    val popularity: Double? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
)
