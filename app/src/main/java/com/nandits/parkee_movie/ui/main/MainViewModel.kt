package com.nandits.parkee_movie.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nandits.parkee_movie.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel(){
    fun getPopular() = repository.getPopularMovie().asLiveData()
    
    fun getNowPlaying() = repository.getNowPlaying().asLiveData()
    
    fun getTopRated() = repository.getTopRated().asLiveData()
}