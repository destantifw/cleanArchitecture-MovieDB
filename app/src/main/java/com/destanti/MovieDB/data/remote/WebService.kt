package com.destanti.MovieDB.data.remote

import com.destanti.MovieDB.data.Model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {
    @GET("movie/{movieId}")
    fun getMovieDetail(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String): Call<MovieDetailModel>

    @GET("movie/{movieId}/videos")
    suspend fun getVideoDetail(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String): VideoDetail?

    @GET("movie/{movieId}/reviews")
    fun getUserReviewList(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String): Call<ReviewModel>

    @GET("discover/movie")
    suspend fun getMovieListByGenre(@Query("api_key") apiKey: String, @Query("with_genres") genreId: Int,  @Query("page") page: Int): MovieList?


    @GET("genre/movie/list")
    suspend fun getGenreList(@Query("api_key") apiKey: String): Genres?


}