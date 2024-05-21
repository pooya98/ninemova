package com.ninemova.Network.api

import com.ninemova.Network.request.ReplyRequest
import com.ninemova.Network.response.ReplyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReplyApi {

    @POST("reply/register")
    suspend fun createReply(@Body request: ReplyRequest): Response<ReplyResponse>

    @GET("reply/list/{comment_id}")
    suspend fun getReplies(@Path("comment_id") commentId: Int): Response<List<ReplyResponse>>
}
