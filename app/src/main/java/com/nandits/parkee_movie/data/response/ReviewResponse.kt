package com.nandits.parkee_movie.data.response

data class ReviewResponse(
    var id: Int?,
    var page: Int?,
    var results: List<ResultXX>?,
    var total_pages: Int?,
    var total_results: Int?
)

data class ResultXX(
    var author: String?,
    var author_details: AuthorDetails?,
    var content: String?,
    var created_at: String?,
    var id: String?,
    var updated_at: String?,
    var url: String?
)

data class AuthorDetails(
    var avatar_path: String?,
    var name: String?,
    var rating: Double?,
    var username: String?
)