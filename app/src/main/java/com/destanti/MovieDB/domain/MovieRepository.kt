package com.destanti.MovieDB.domain

import androidx.lifecycle.LiveData
import com.destanti.MovieDB.core.Resource
import com.destanti.MovieDB.data.Model.Genre
import com.destanti.MovieDB.data.Model.GenreEntity
import com.destanti.MovieDB.data.Model.MovieEntity
import com.destanti.MovieDB.data.Model.MovieListResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovieList(page: Int): Flow<Resource<List<MovieListResult>>>
    suspend fun saveMovie(movie: MovieEntity)
    suspend fun getCachedMoviesByGenre(genreId: Int): Resource<List<MovieListResult>>
    suspend fun getCachedAllMovies(): Resource<List<MovieListResult>>
    suspend fun saveGenre(genre: GenreEntity)
    suspend fun getGenreList(): Flow<Resource<List<Genre>>>
    suspend fun getCachedGenreList(): Resource<List<Genre>>
}