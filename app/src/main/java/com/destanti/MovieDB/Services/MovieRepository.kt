package com.example.destanti.submission2.Services

import com.destanti.MovieDB.Services.MovieDataSource
import com.destanti.MovieDB.data.Model.MovieDetailModel
import com.destanti.MovieDB.data.Model.MovieList
import com.destanti.MovieDB.data.Model.ReviewModel
import com.destanti.MovieDB.data.Model.VideoDetail


class MovieRepository(val dataSource: MovieDataSource) : MovieDataSource {

    companion object {

        private var INSTANCE: MovieRepository? = null


        fun getInstance(dataSource: MovieDataSource): MovieRepository {
            return INSTANCE ?: MovieRepository(dataSource)
                    .apply { INSTANCE = this }
        }
    }


    override fun getMovieDetail(id: Int, callback: MovieDataSource.LoadMovieDetailCallback) {
        dataSource.getMovieDetail(id, object : MovieDataSource.LoadMovieDetailCallback {
            override fun onSuccess(movie: MovieDetailModel) {
                callback.onSuccess(movie)
            }

            override fun onFailed(throwable: Throwable) {
                callback.onFailed(throwable)
            }
        })
    }

    override fun getVideoDetail(id: Int, callback: MovieDataSource.LoadVideoDetailCallback) {
        dataSource.getVideoDetail(id, object: MovieDataSource.LoadVideoDetailCallback{
            override fun onSuccess(video: VideoDetail) {
                callback.onSuccess(video)
            }

            override fun onFailed(throwable: Throwable) {
                callback.onFailed(throwable)
            }
        })
    }

    override fun getUserReviewList(id: Int, callback: MovieDataSource.LoadUserReviewCallback) {
        dataSource.getUserReviewList(id, object: MovieDataSource.LoadUserReviewCallback{
            override fun onSuccess(review: ReviewModel) {
                callback.onSuccess(review)
            }

            override fun onFailed(throwable: Throwable) {
                callback.onFailed(throwable)
            }
        })
    }


    override fun getMovieListByGenre(id: Int, page: Int, callback: MovieDataSource.LoadMovieListByGenreCallback) {
        dataSource.getMovieListByGenre(id, page, object: MovieDataSource.LoadMovieListByGenreCallback{
            override fun onSuccess(movie: MovieList) {
                callback.onSuccess(movie)
            }
            override fun onFailed(throwable: Throwable) {
                callback.onFailed(throwable)
            }
        })
    }
}