package com.destanti.MovieDB.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.destanti.MovieDB.data.Model.Genre
import com.destanti.MovieDB.data.Model.GenreEntity
import com.destanti.MovieDB.data.Model.MovieEntity
import com.destanti.MovieDB.data.Model.VideoEntity

@Dao
interface MovieDao {


    @Query("SELECT * FROM movieListTable")
    suspend fun getMovies(): List<MovieEntity>

    @Query("SELECT * FROM movieListTable")
    suspend fun getMovieByGenre(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGenre(genre: GenreEntity)

    @Query("SELECT * FROM genreListTable")
    suspend fun getGenreList(): List<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveVideo(video: VideoEntity)

    @Query("SELECT * FROM videoDetailTable WHERE movieId = :movieId")
    suspend fun getVideos(movieId: Int): List<VideoEntity>
//
//    @Query("SELECT * FROM movieListTable")
//    suspend fun getMovie(): MovieEntity?
}