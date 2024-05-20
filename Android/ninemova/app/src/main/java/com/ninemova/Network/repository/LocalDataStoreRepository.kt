package com.ninemova.Network.repository

import com.ninemova.domain.data.User

interface LocalDataStoreRepository {

    suspend fun saveUser(user: User)

    suspend fun deleteUser()

    suspend fun getUserId(): Int

    suspend fun getUserName(): String

    suspend fun getUserNickName(): String

    suspend fun getUserProfileImageUrl(): String
}
