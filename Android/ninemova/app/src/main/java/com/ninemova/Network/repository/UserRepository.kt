package com.ninemova.Network.repository

import com.ninemova.Network.request.server.SignUpRequest
import com.ninemova.domain.data.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun signUp(request: SignUpRequest): Flow<User?>

    suspend fun findUser(userId: Int): Flow<User?>
}
