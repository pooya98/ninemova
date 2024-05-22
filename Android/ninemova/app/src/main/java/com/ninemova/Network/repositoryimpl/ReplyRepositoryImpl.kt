package com.ninemova.Network.repositoryimpl

import com.ninemova.Network.utils.RetrofitUtils
import com.ninemova.Network.repository.ReplyRepository
import com.ninemova.Network.request.server.ReplyRequest
import com.ninemova.domain.data.Reply
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ReplyRepositoryImpl : ReplyRepository {

    private val replyApi = RetrofitUtils.replyApi
    private val userApi = RetrofitUtils.userApi

    override suspend fun createReply(request: ReplyRequest): Flow<Reply?> = flow {
        runCatching {
            replyApi.createReply(request)
        }.onSuccess { response ->
            response.body()?.let { body ->
                val reply = body.userId?.let { userId ->
                    userApi.findUser(userId).body()?.let { user ->
                        Reply(
                            id = body.id,
                            content = body.content,
                            userNickName = user.nickName,
                            userProfileImageUrl = user.profileImageUrl,
                        )
                    }
                }
                emit(reply)
            } ?: run {
                emit(null)
            }
        }.onFailure {
            emit(null)
        }
    }

    override suspend fun getReplies(commentId: Int): Flow<List<Reply>> = flow {
        runCatching {
            replyApi.getReplies(commentId)
        }.onSuccess { response ->
            response.body()?.let { body ->
                val replies = body.mapNotNull { reply ->
                    reply.userId?.let { userId ->
                        userApi.findUser(userId).body()?.let { user ->
                            Reply(
                                id = reply.id,
                                content = reply.content,
                                userNickName = user.nickName,
                                userProfileImageUrl = user.profileImageUrl,
                            )
                        }
                    }
                }
                emit(replies)
            } ?: run {
                emit(emptyList())
            }
        }.onFailure {
            emit(emptyList())
        }
    }
}
