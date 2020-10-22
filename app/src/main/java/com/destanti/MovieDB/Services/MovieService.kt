package com.destanti.MovieDB.Services

import com.destanti.MovieDB.Base.GlobalConfig
import com.destanti.MovieDB.data.Model.MovieDetailModel
import com.destanti.MovieDB.data.Model.MovieList
import com.destanti.MovieDB.data.Model.ReviewModel
import com.destanti.MovieDB.data.Model.VideoDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/{movieId}")
    fun getMovieDetail(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String): Call<MovieDetailModel>

    @GET("movie/{movieId}/videos")
    fun getVideoDetail(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String): Call<VideoDetail>

    @GET("movie/{movieId}/reviews")
    fun getUserReviewList(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String): Call<ReviewModel>

    @GET("discover/movie")
    fun getMovieListByGenre(@Query("api_key") apiKey: String, @Query("with_genres") genreId: Int,  @Query("page") page: Int): Call<MovieList>


}
