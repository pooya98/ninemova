package com.ninemova.Network.api

import com.ninemova.Network.request.SignUpRequest
import com.ninemova.Network.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("user/signUp")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>
}
