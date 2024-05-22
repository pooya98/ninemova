package com.ninemova.Network.repository

import com.ninemova.Network.request.server.ReplyRequest
import com.ninemova.domain.data.Reply
import kotlinx.coroutines.flow.Flow

interface ReplyRepository {

    suspend fun createReply(request: ReplyRequest): Flow<Reply?>
    suspend fun getReplies(commentId: Int): Flow<List<Reply>>
}
