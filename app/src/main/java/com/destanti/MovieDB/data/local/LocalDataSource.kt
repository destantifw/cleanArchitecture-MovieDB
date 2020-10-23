package com.g.tragosapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.destanti.MovieDB.core.Resource
import com.destanti.MovieDB.data.Model.*
import com.destanti.MovieDB.data.local.MovieDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    suspend fun saveVideo(video: VideoEntity) {
        movieDao.saveVideo(video)
    }

    suspend fun saveMovie(movie: MovieEntity) {
        movieDao.saveMovie(movie)
    }

    suspend fun getCachedMoviesByGenre(genreId: Int): Resource<List<MovieListResult>> {
        return Resource.Success(movieDao.getMovieByGenre().asMovieList())
    }

    suspend fun getCachedMovies(): Resource<List<MovieListResult>> {
        return Resource.Success(movieDao.getMovies().asMovieList())
    }


    suspend fun getCachedVideos(movieId: Int): Resource<List<VideoResult>> {
        return Resource.Success(movieDao.getVideos(movieId).asVideoList())
    }
}