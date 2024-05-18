package com.ninemova.Network.response.youtube

data class YoutubeSearchResponse(
    val kind: String? = null,
    val etag: String? = null,
    val nextPageToken: String? = null,
    val prevPageToken: String? = null,
    val regionCode: String? = null,
    val pageInfo: PageInfo? = null,
    val items: List<SearchResult> = listOf(),
)
