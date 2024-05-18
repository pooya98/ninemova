package com.ninemova.Network.response.youtube

import com.google.gson.annotations.SerializedName

data class Thumbnails(
    @SerializedName("default")
    val default: Size? = null,
    @SerializedName("high")
    val high: Size? = null,
    @SerializedName("medium")
    val medium: Size? = null,
)
