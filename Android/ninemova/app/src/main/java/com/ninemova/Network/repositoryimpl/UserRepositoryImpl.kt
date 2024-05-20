package com.ninemova.Network.repositoryimpl

import com.ninemova.Network.RetrofitUtils
import com.ninemova.Network.repository.UserRepository
import com.ninemova.Network.request.SignUpRequest
import com.ninemova.domain.data.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl : UserRepository {
    private val userApi = RetrofitUtils.userApi
    override suspend fun signUp(request: SignUpRequest): Flow<User?> = flow {
        runCatching {
            userApi.signUp(
                request,
            )
        }.onSuccess { response ->
            response.body()?.let { response ->
                emit(
                    User(
                        userName = response.userName,
                        nickName = response.nickName,
                        profileImageUrl = response.profileImageUrl,
                    ),
                )
            } ?: run {
                emit(null)
            }
        }.onFailure {
            emit(null)
        }
    }
}