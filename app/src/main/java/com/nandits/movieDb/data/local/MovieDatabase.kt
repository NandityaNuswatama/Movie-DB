package com.nandits.movieDb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EntityMovie::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}