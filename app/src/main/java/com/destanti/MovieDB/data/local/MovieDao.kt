package com.destanti.MovieDB.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.destanti.MovieDB.data.Model.MovieEntity

@Dao
interface MovieDao {


    @Query("SELECT * FROM movieListTable")
    suspend fun getMovies(): List<MovieEntity>

    @Query("SELECT * FROM movieListTable")
    suspend fun getMovieByGenre(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)

//
//    @Query("SELECT * FROM movieListTable")
//    suspend fun getMovie(): MovieEntity?
}