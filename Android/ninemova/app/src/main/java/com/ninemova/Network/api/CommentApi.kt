package com.ninemova.Network.api

import com.ninemova.Network.request.server.CommentRequest
import com.ninemova.Network.response.server.CommentResponse
import com.ninemova.Network.response.server.CommentsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CommentApi {

    @GET("comment/list")
    suspend fun getComments(): Response<List<CommentsResponse>>

    @POST("comment/register")
    suspend fun createComment(
        @Body request: CommentRequest,
    ): Response<CommentResponse>

    @GET("comment/recent5")
    suspend fun getRecentComments(): Response<List<CommentsResponse>>
}
