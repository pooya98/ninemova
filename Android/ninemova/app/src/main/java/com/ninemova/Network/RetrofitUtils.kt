package com.ninemova.Network

import com.ninemova.Network.api.GenreApi
import com.ninemova.Network.api.SearchApi
import com.ninemova.Network.api.UserApi
import com.ninemova.Network.api.YoutubeApi
import com.ninemova.core.NineMovaApplication
import retrofit2.create

class RetrofitUtils {

    companion object {
        val searchApi: SearchApi = NineMovaApplication.retrofit.create()
        val genreApi: GenreApi = NineMovaApplication.retrofit.create()
        val youtubeApi: YoutubeApi = NineMovaApplication.youtubeRetrofit.create()
        val userApi: UserApi = NineMovaApplication.serverRetrofit.create()
    }
}
