package com.nandits.parkee_movie.data.local

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val dao: MovieDao){
    fun getFavorite() = dao.getFavorite()
    
    fun isFavorite(id: Int) = dao.isFavorite(id)
    
    suspend fun insert(data: EntityMovie) = dao.insert(data)
    
    suspend fun delete(data: EntityMovie) = dao.delete(data)
}