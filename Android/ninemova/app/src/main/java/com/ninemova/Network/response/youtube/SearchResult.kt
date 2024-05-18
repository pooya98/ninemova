package com.ninemova.Network.response.youtube

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("etag")
    val etag: String,
    @SerializedName("id")
    val id: Id,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("snippet")
    val snippet: Snippet,
)
