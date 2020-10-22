package com.g.tragosapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.destanti.MovieDB.core.Resource
import com.destanti.MovieDB.data.Model.MovieEntity
import com.destanti.MovieDB.data.Model.MovieListResult
import com.destanti.MovieDB.data.Model.asMovieList
import com.destanti.MovieDB.data.local.MovieDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {



//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun saveMovie(movie: MovieEntity)
//ail.asFavoriteEntity())
//    }

    suspend fun saveMovie(movie: MovieEntity) {
        movieDao.saveMovie(movie)
    }

    suspend fun getCachedMoviesByGenre(genreId: Int): Resource<List<MovieListResult>> {
        return Resource.Success(movieDao.getMovieByGenre().asMovieList())
    }

    suspend fun getCachedMovies(): Resource<List<MovieListResult>> {
        return Resource.Success(movieDao.getMovies().asMovieList())
    }
}