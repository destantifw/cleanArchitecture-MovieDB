package com.destanti.MovieDB.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.destanti.MovieDB.application.ToastHelper
import com.destanti.MovieDB.core.Resource
import com.destanti.MovieDB.data.Model.Genre
import com.destanti.MovieDB.domain.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect


class MainViewModel @ViewModelInject constructor(
    private val repository: MovieRepository,
    private val toastHelper: ToastHelper,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val currentMoviePage = savedStateHandle.getLiveData<Int>("params", 1)

    fun setPage(page: Int) {
        currentMoviePage.value = page
    }

    val fetchMovieList = currentMoviePage.distinctUntilChanged().switchMap { page ->
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                repository.getMovieList(page).collect {
                    emit(it)
                }
            } catch (e: Exception) {
               // emit(Resource.Failure(e))
            }
        }
    }

    fun getGenreList() =
        liveData<Resource<List<Genre>>>(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                repository.getGenreList().collect {
                    emit(it)
                }
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }

}