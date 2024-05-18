package com.ninemova.Network.repositoryimpl

import com.ninemova.Network.RetrofitUtils
import com.ninemova.Network.repository.YoutubeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class YoutubeRepositoryImpl : YoutubeRepository {

    private val youtubeApi = RetrofitUtils.youtubeApi
    override suspend fun getTrailers(movieName: String): Flow<String> = flow {
        runCatching {
            youtubeApi.getMovieTrailer(movieName = movieName)
        }.onSuccess { response ->
            response.body()?.let { body ->
                emit(body.items.first().id.videoId ?: "")
            } ?: emit("")
        }.onFailure {
            emit("")
        }
    }
}
