package com.nandits.movieDb.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nandits.movieDb.data.Repository
import com.nandits.movieDb.data.local.EntityMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    fun getReview(id: Int) = repository.getReview(id).asLiveData()
    
    fun isFavorite(id: Int) = repository.isFavorite(id)
    
    fun insert(data: EntityMovie) = repository.insert(data)
    
    fun delete(data: EntityMovie) = repository.delete(data)
}