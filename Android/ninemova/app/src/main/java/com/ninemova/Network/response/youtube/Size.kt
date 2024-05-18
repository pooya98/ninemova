package com.ninemova.Network.response.youtube

import com.google.gson.annotations.SerializedName

data class Size(
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("width")
    val width: Int? = null,
)
