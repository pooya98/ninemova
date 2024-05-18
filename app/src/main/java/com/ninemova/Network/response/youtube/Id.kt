package com.ninemova.Network.response.youtube

import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("kind")
    val kind: String? = null,
    @SerializedName("videoId")
    val videoId: String? = null,
)
