package com.nandits.movieDb.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM table_movie")
    fun getFavorite(): List<EntityMovie>
    
    @Query("SELECT EXISTS (SELECT * FROM table_movie WHERE id=:id)")
    fun isFavorite(id: Int): LiveData<Boolean>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: EntityMovie)
    
    @Delete
    suspend fun delete(data: EntityMovie)
}