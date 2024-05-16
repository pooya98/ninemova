package com.ninemova.domain.data

data class CommentData(
    val id: Int? = null,
    val movieId: Int? = null,
    val commentScore: Double? = null,
    val commentContent: String? = null,
    val writer: String? = null
) {
    companion object {
        val commentList = listOf<CommentData>(
            CommentData(
                id = 0,
                movieId = 0,
                commentScore = 5.0,
                commentContent = "명작영화",
                writer = "방구석 전문가"
            ),
            CommentData(
                id = 1,
                movieId = 0,
                commentScore = 4.0,
                commentContent = "명작영화1",
                writer = "방구석 전문가"
            ),
            CommentData(
                id = 2,
                movieId = 0,
                commentScore = 3.0,
                commentContent = "명작영화2",
                writer = "방구석 전문가"
            ),
            CommentData(
                id = 3,
                movieId = 0,
                commentScore = 2.0,
                commentContent = "명작영화3",
                writer = "방구석 전문가"
            ),
            CommentData(
                id = 4,
                movieId = 0,
                commentScore = 1.0,
                commentContent = "명작영화4",
                writer = "방구석 전문가"
            ),
        )
    }
}
