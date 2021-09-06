package com.nandits.parkee_movie.utils

import com.nandits.parkee_movie.data.Resource
import com.nandits.parkee_movie.data.local.EntityMovie
import com.nandits.parkee_movie.data.model.MovieModel
import com.nandits.parkee_movie.data.model.ReviewModel
import com.nandits.parkee_movie.data.response.NowPlayingResponse
import com.nandits.parkee_movie.data.response.PopularResponse
import com.nandits.parkee_movie.data.response.ReviewResponse
import com.nandits.parkee_movie.data.response.TopRatedResponse

object DataMapper {
    fun mapPopularToModel(input: Resource<PopularResponse?>): Resource<ArrayList<MovieModel>> {
        val movies = ArrayList<MovieModel>()
        input.data?.results?.map {
            movies.add(
                MovieModel(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    poster = it.poster_path,
                    backdrop = it.backdrop_path,
                    releaseDate = it.release_date,
                    vote_average = it.vote_average.toString()
                )
            )
        }
        return when (input) {
            is Resource.Loading -> Resource.Loading()
            is Resource.Error -> Resource.Error(message = input.message.toString(), data = null)
            is Resource.Success -> {
                Resource.Success(movies)
            }
        }
    }
    
    fun mapNowPlayingToModel(input: Resource<NowPlayingResponse?>): Resource<ArrayList<MovieModel>> {
        val movies = ArrayList<MovieModel>()
        input.data?.results?.map {
            movies.add(
                MovieModel(
                    id = it.id,
                    title = it.original_title,
                    overview = it.overview,
                    poster = it.poster_path,
                    backdrop = it.backdrop_path,
                    releaseDate = it.release_date,
                    vote_average = it.vote_average.toString()
                )
            )
        }
        return when (input) {
            is Resource.Loading -> Resource.Loading()
            is Resource.Error -> Resource.Error(message = input.message.toString(), data = null)
            is Resource.Success -> {
                Resource.Success(movies)
            }
        }
    }
    
    fun mapTopRatedToModel(input: Resource<TopRatedResponse?>): Resource<ArrayList<MovieModel>> {
        val movies = ArrayList<MovieModel>()
        input.data?.results?.map {
            movies.add(
                MovieModel(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    poster = it.poster_path,
                    backdrop = it.backdrop_path,
                    releaseDate = it.release_date,
                    vote_average = it.vote_average.toString(),
                )
            )
        }
        return when (input) {
            is Resource.Loading -> Resource.Loading()
            is Resource.Error -> Resource.Error(message = input.message.toString(), data = null)
            is Resource.Success -> {
                Resource.Success(movies)
            }
        }
    }
    
    fun mapReviewToModel(input: Resource<ReviewResponse?>): Resource<ArrayList<ReviewModel>> {
        val reviews = ArrayList<ReviewModel>()
        input.data?.results?.map {
            reviews.add(
                ReviewModel(
                    author = it.author,
                    content = it.content
                )
            )
        }
        return when (input) {
            is Resource.Loading -> Resource.Loading()
            is Resource.Error -> Resource.Error(message = input.message.toString(), data = null)
            is Resource.Success -> {
                Resource.Success(reviews)
            }
        }
    }
    
    fun mapEntityToModel(input: List<EntityMovie?>): ArrayList<MovieModel>{
        val movies = ArrayList<MovieModel>()
        input.map {
            movies.add(
                MovieModel(
                    id = it?.id,
                    title = it?.title,
                    poster = it?.poster,
                    vote_average = it?.vote_average,
                    releaseDate = it?.release,
                    backdrop = it?.backdrop,
                    overview = it?.overview
                )
            )
        }
        return movies
    }
}