package com.nandits.parkee_movie.data.response

data class NowPlayingResponse(
    var dates: Dates?,
    var page: Int?,
    var results: List<Result>?,
    var total_pages: Int?,
    var total_results: Int?
)

data class Dates(
    var maximum: String?,
    var minimum: String?
)