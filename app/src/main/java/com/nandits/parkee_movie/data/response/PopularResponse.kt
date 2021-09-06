package com.nandits.parkee_movie.data.response

data class PopularResponse(
    var page: Int?,
    var results: List<Result>?,
    var total_pages: Int?,
    var total_results: Int?
)