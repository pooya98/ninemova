package com.ninemova.Network.response.youtube

import com.google.gson.annotations.SerializedName

data class Snippet(
    @SerializedName("channelId")
    val channelId: String? = null,
    @SerializedName("channelTitle")
    val channelTitle: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("liveBroadcastContent")
    val liveBroadcastContent: String? = null,
    @SerializedName("publishTime")
    val publishTime: String? = null,
    @SerializedName("publishedAt")
    val publishedAt: String? = null,
    @SerializedName("thumbnails")
    val thumbnails: Thumbnails? = null,
    @SerializedName("title")
    val title: String? = null,
)
