package com.ninemova.Network

import com.ninemova.Network.api.SearchApi
import com.ninemova.core.NineMovaApplication
import retrofit2.create

class RetrofitUtils {

    companion object {
        val searchApi: SearchApi = NineMovaApplication.retrofit.create()
    }
}
