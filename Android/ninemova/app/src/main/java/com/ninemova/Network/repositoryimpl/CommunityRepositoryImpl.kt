package com.ninemova.Network.repositoryimpl

import com.ninemova.Network.RetrofitUtils
import com.ninemova.Network.repository.CommentRepository
import com.ninemova.Network.request.CommentRequest
import com.ninemova.domain.data.Comment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CommunityRepositoryImpl : CommentRepository {

    private val commentApi = RetrofitUtils.commentApi
    private val searchApi = RetrofitUtils.searchApi

    override suspend fun getComments(): Flow<List<Comment>> = flow {
        runCatching {
            commentApi.getComments()
        }.onSuccess { response ->
            response.body()?.let { body ->
                val comments = body.mapNotNull { data ->
                    data.comment.movieId?.let { movieId ->
                        searchApi.getMovie(moveId = movieId, language = "ko").body()?.let { movie ->
                            Comment(
                                id = data.comment.id,
                                movieName = movie.title,
                                posterPath = movie.posterPath ?: movie.backdropPath,
                                backDropPath = movie.backdropPath ?: movie.posterPath,
                                commentScore = data.comment.score,
                                commentContent = data.comment.content,
                                writer = data.user.nickName,
                                userId = data.user.id,
                            )
                        }
                    }
                }
                emit(comments)
            }
        }
    }

    override suspend fun createComment(request: CommentRequest): Flow<Comment?> = flow {
        runCatching {
            commentApi.createComment(request)
        }.onSuccess { response ->
            val comment = response.body()?.let { body ->
                body.movieId?.let { movieId ->
                    searchApi.getMovie(moveId = movieId, language = "ko").body()?.let { movie ->
                        Comment(
                            id = body.id,
                            movieName = movie.title,
                            posterPath = movie.posterPath,
                            commentScore = body.score,
                            commentContent = body.content,
                        )
                    }
                }
            }
            emit(comment)
        }.onFailure {
            emit(null)
        }
    }

    override suspend fun getRecentComments(): Flow<List<Comment>>  = flow {
        runCatching {
            commentApi.getRecentComments()
        }.onSuccess { response ->
            response.body()?.let { body ->
                val comments = body.mapNotNull { data ->
                    data.comment.movieId?.let { movieId ->
                        searchApi.getMovie(moveId = movieId, language = "ko").body()?.let { movie ->
                            Comment(
                                id = data.comment.id,
                                movieName = movie.title,
                                posterPath = movie.posterPath ?: movie.backdropPath,
                                backDropPath = movie.backdropPath ?: movie.posterPath,
                                commentScore = data.comment.score,
                                commentContent = data.comment.content,
                                writer = data.user.nickName,
                                userId = data.user.id,
                            )
                        }
                    }
                }
                emit(comments)
            }
        }
    }
}
