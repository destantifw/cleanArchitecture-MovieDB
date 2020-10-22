package com.destanti.MovieDB.domain

import com.destanti.MovieDB.core.Resource
import com.destanti.MovieDB.data.Model.MovieEntity
import com.destanti.MovieDB.data.Model.MovieListResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovieList(): Flow<Resource<List<MovieListResult>>>
    suspend fun saveMovie(movie: MovieEntity)
    suspend fun getCachedMoviesByGenre(genreId: Int): Resource<List<MovieListResult>>
    suspend fun getCachedAllMovies(): Resource<List<MovieListResult>>
}