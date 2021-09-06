package com.nandits.parkee_movie.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_movie")
data class EntityMovie(
    @PrimaryKey (autoGenerate = false)
    var id: Int,
    var title: String,
    var poster: String,
    var backdrop: String,
    var vote_average: String,
    var release: String,
    var overview: String
)
