package com.ninemova.domain.data

import java.io.Serializable

data class Comment(
    val id: Int? = null,
    val movieName: String? = null,
    val posterPath: String? = null,
    val backDropPath: String? = null,
    val commentScore: Double? = null,
    val commentContent: String? = null,
    val userId: Int? = null,
    val writer: String? = null,
) : Serializable
