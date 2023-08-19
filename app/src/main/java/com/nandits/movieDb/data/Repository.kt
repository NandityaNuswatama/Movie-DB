package com.nandits.movieDb.data

import com.nandits.movieDb.data.local.EntityMovie
import com.nandits.movieDb.data.local.LocalDataSource
import com.nandits.movieDb.data.network.RemoteDataSource
import com.nandits.movieDb.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) {
    fun getPopularMovie() = remote.getPopular(1).map { DataMapper.mapPopularToModel(it) }
    
    fun getNowPlaying() = remote.getNowPlaying(1).map { DataMapper.mapNowPlayingToModel(it) }
    
    fun getTopRated() = remote.getTopRated(1).map { DataMapper.mapTopRatedToModel(it) }
    
    fun getReview(id: Int) = remote.getReview(id).map { DataMapper.mapReviewToModel(it) }
    
    fun getFavorite() = local.getFavorite()
    
    fun isFavorite(id: Int) = local.isFavorite(id)
    
    fun insert(data: EntityMovie){
        CoroutineScope(Dispatchers.IO).launch {
            local.insert(data)
        }
    }
    
    fun delete(data: EntityMovie){
        CoroutineScope(Dispatchers.IO).launch {
            local.delete(data)
        }
    }
}