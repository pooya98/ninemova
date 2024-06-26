package com.ninemova.Network.utils

import com.ninemova.Network.api.CommentApi
import com.ninemova.Network.api.FavoriteApi
import com.ninemova.Network.api.GenreApi
import com.ninemova.Network.api.OpenAiApi
import com.ninemova.Network.api.ReplyApi
import com.ninemova.Network.api.SearchApi
import com.ninemova.Network.api.UserApi
import com.ninemova.Network.api.YoutubeApi
import com.ninemova.core.NineMovaApplication
import retrofit2.create

class RetrofitUtils {

    companion object {
        val searchApi: SearchApi = NineMovaApplication.tmdbRetrofit.create()
        val genreApi: GenreApi = NineMovaApplication.tmdbRetrofit.create()
        val youtubeApi: YoutubeApi = NineMovaApplication.youtubeRetrofit.create()
        val openAiAPI: OpenAiApi = NineMovaApplication.openAiRetrofit.create()
        val userApi: UserApi = NineMovaApplication.serverRetrofit.create()
        val commentApi: CommentApi = NineMovaApplication.serverRetrofit.create()
        val replyApi: ReplyApi = NineMovaApplication.serverRetrofit.create()
        val favoriteApi: FavoriteApi = NineMovaApplication.serverRetrofit.create()
    }
}
