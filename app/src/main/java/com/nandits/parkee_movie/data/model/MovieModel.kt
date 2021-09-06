package com.nandits.parkee_movie.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    var id: Int?,
    var title: String?,
    var overview: String?,
    var poster: String?,
    var backdrop: String?,
    var releaseDate: String?,
    var vote_average: String?
): Parcelable