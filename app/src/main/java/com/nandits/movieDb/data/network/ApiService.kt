package com.nandits.movieDb.data.network

import com.nandits.movieDb.data.response.NowPlayingResponse
import com.nandits.movieDb.data.response.PopularResponse
import com.nandits.movieDb.data.response.ReviewResponse
import com.nandits.movieDb.data.response.TopRatedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    
    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<PopularResponse>
    
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<NowPlayingResponse>
    
    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<TopRatedResponse>
    
    @GET("movie/{id}/reviews")
    suspend fun getReview(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<ReviewResponse>
}