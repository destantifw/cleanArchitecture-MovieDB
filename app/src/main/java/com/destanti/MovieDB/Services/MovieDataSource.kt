package com.destanti.MovieDB.Services

import com.destanti.MovieDB.data.Model.MovieDetailModel
import com.destanti.MovieDB.data.Model.MovieList
import com.destanti.MovieDB.data.Model.ReviewModel
import com.destanti.MovieDB.data.Model.VideoDetail


interface MovieDataSource {

    fun getMovieDetail(id:Int, callback: LoadMovieDetailCallback)

    interface LoadMovieDetailCallback {
        fun onSuccess(movie: MovieDetailModel)
        fun onFailed(throwable: Throwable)
    }

    fun getVideoDetail(id:Int, callback: LoadVideoDetailCallback)

    interface LoadVideoDetailCallback {
        fun onSuccess(video: VideoDetail)
        fun onFailed(throwable: Throwable)
    }

    fun getUserReviewList(id:Int, callback: LoadUserReviewCallback)

    interface LoadUserReviewCallback {
        fun onSuccess(review: ReviewModel)
        fun onFailed(throwable: Throwable)
    }

    fun getMovieListByGenre(id:Int, page:Int, callback: LoadMovieListByGenreCallback)

    interface LoadMovieListByGenreCallback {
        fun onSuccess(movie: MovieList)
        fun onFailed(throwable: Throwable)
    }

}

