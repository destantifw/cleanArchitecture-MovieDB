package com.destanti.MovieDB.presentation.MovieDetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.destanti.MovieDB.application.ToastHelper
import com.destanti.MovieDB.core.Resource
import com.destanti.MovieDB.domain.MovieDetail.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class MovieDetailViewModel @ViewModelInject constructor(
    private val repository: MovieDetailRepository,
    private val toastHelper: ToastHelper,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val currentMovieId = savedStateHandle.getLiveData<Int>("movieId", 0)

    fun setMovieId(movieId: Int) {
        currentMovieId.value = movieId
    }

    val fetchVideoList = currentMovieId.distinctUntilChanged().switchMap { movieId ->
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                repository.getVideoList(movieId).collect{
                    emit(it)
                }
            } catch (e: Exception) {
                // emit(Resource.Failure(e))
            }
        }
    }

}