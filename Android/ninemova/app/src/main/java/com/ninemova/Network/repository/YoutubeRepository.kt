package com.ninemova.Network.repository

import kotlinx.coroutines.flow.Flow

interface YoutubeRepository {

    suspend fun getTrailers(movieName: String): Flow<String>
}
