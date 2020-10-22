package com.destanti.MovieDB.Features.MovieList

import android.util.Log
import com.destanti.MovieDB.Services.MovieDataSource
import com.destanti.MovieDB.data.Model.MovieList
import com.destanti.MovieDB.data.Model.ReviewModel
import com.example.destanti.submission2.Services.MovieRepository

class MovieListPresenter(
    private val repository: MovieRepository,
    private val view: MovieListContract.View, override val genreId: Int)
    : MovieListContract.Presenter {

    val cbMovie = object : MovieDataSource.LoadMovieListByGenreCallback {
        override fun onFailed(throwable: Throwable) {
            Log.d("scroll", "onScrolled: error")
            print("presenter error"+ throwable.toString())
        }

        override fun onSuccess(movies: MovieList) {
            currentPage = movies.page
            totalPage = movies.totalPages
            view.showMovie(movies.results)
        }
    }

    private var currentPage: Int = 1
    private var totalPage: Int = 0


    init {
        view.setPresenter(this)
    }

    override fun getMovieList() {
        repository.getMovieListByGenre(genreId, currentPage, cbMovie)
    }

    override fun nextPage() {
        val page = currentPage + 1
        if (page < totalPage) {
            repository.getMovieListByGenre(genreId, page, cbMovie)
        }
    }

    override fun start() {
        getMovieList()
    }




}