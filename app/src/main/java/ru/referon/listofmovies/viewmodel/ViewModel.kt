package ru.referon.listofmovies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception
import ru.referon.listofmovies.FeedModel
import ru.referon.listofmovies.repository.Repository

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository()
    private val _data = MutableLiveData(FeedModel())
    val data: LiveData<FeedModel>
        get() = _data

    fun loadMovies(offset: Int){
        viewModelScope.launch {
            _data.value = FeedModel(loading = true)
            try {
                val result = repository.getMovies(offset)
                if (!result.isSuccessful){
                    throw Exception()
                }
                val body = result.body() ?: throw Exception()
                _data.value = FeedModel(movie = body)
            }catch (e: Exception){
                _data.value = FeedModel(error = true)
            }
        }

    }
}