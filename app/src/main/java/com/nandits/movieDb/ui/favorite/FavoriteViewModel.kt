package com.nandits.movieDb.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nandits.movieDb.data.Repository
import com.nandits.movieDb.data.local.EntityMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getFavorite(): LiveData<List<EntityMovie>> {
        val liveData = MutableLiveData<List<EntityMovie>>()
        val list = mutableListOf<EntityMovie>()
        CoroutineScope(Dispatchers.IO).launch {
            list.addAll(repository.getFavorite())
            liveData.postValue(list)
        }
        return liveData
    }
}