package com.nandits.parkee_movie.data.network

import com.nandits.parkee_movie.data.Resource
import com.nandits.parkee_movie.data.response.NowPlayingResponse
import com.nandits.parkee_movie.data.response.PopularResponse
import com.nandits.parkee_movie.data.response.ReviewResponse
import com.nandits.parkee_movie.data.response.TopRatedResponse
import com.nandits.parkee_movie.utils.api_key
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun getPopular(page: Int): Flow<Resource<PopularResponse>>{
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getPopular(api_key, page)
                if (response.isSuccessful){
                    emit(Resource.Success(response.body() as PopularResponse))
                }else Timber.w(response.errorBody().toString())
            }catch (e: Exception){
                emit(Resource.Error(e.message.toString(), null))
                Timber.w(e)
            }
        }
    }
    
    fun getNowPlaying(page: Int): Flow<Resource<NowPlayingResponse>>{
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getNowPlaying(api_key, page)
                if (response.isSuccessful){
                    emit(Resource.Success(response.body() as NowPlayingResponse))
                }else Timber.w(response.errorBody().toString())
            }catch (e: Exception){
                emit(Resource.Error(e.message.toString(), null))
                Timber.w(e)
            }
        }
    }
    
    fun getTopRated(page: Int): Flow<Resource<TopRatedResponse>>{
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getTopRated(api_key, page)
                if (response.isSuccessful){
                    emit(Resource.Success(response.body() as TopRatedResponse))
                }else Timber.w(response.errorBody().toString())
            }catch (e: Exception){
                emit(Resource.Error(e.message.toString(), null))
                Timber.w(e)
            }
        }
    }
    
    fun getReview(id: Int): Flow<Resource<ReviewResponse>>{
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getReview(id, api_key)
                if (response.isSuccessful){
                    emit(Resource.Success(response.body() as ReviewResponse))
                }else Timber.w(response.errorBody().toString())
            }catch (e: Exception){
                emit(Resource.Error(e.message.toString(), null))
                Timber.w(e)
            }
        }
    }
}