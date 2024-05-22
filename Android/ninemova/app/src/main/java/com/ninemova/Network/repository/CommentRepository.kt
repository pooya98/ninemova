package com.ninemova.Network.repository

import com.ninemova.Network.request.server.CommentRequest
import com.ninemova.domain.data.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    suspend fun getComments(): Flow<List<Comment>>

    suspend fun createComment(request: CommentRequest): Flow<Comment?>

    suspend fun getRecentComments(): Flow<List<Comment>>
}
