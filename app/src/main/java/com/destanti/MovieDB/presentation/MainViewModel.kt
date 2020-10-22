package com.destanti.MovieDB.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.destanti.MovieDB.application.ToastHelper
import com.destanti.MovieDB.core.Resource
import com.destanti.MovieDB.domain.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect


class MainViewModel @ViewModelInject constructor(
    private val repository: MovieRepository,
    private val toastHelper: ToastHelper,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val currentMovieName = savedStateHandle.getLiveData<String>("movieName", "")

    fun setMovieName(movieName: String) {
        currentMovieName.value = movieName
    }

    val fetchCocktailList = currentMovieName.distinctUntilChanged().switchMap { movie ->
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                repository.getMovieList().collect {
                    emit(it)
                }
            } catch (e: Exception) {
               // emit(Resource.Failure(e))
            }
        }
    }

}