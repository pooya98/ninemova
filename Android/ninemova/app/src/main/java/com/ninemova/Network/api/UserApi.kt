package com.ninemova.Network.api

import com.ninemova.Network.request.server.SignUpRequest
import com.ninemova.Network.response.server.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @POST("user/signUp")
    suspend fun signUp(@Body request: SignUpRequest): Response<UserResponse>

    @GET("user/{user_id}")
    suspend fun findUser(@Path("user_id") userId: Int): Response<UserResponse>
}
