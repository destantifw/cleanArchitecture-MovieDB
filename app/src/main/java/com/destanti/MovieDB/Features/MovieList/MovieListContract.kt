package com.destanti.MovieDB.Features.MovieList

import com.destanti.MovieDB.Base.BasePresenter
import com.destanti.MovieDB.Base.BaseView
import com.destanti.MovieDB.data.Model.MovieDetailModel
import com.destanti.MovieDB.data.Model.MovieListResult
import com.destanti.MovieDB.data.Model.ReviewResult


class MovieListContract {

    interface View : BaseView<Presenter> {
        fun showMovie(movie: List<MovieListResult>)
        fun showError()
    }

    interface Presenter : BasePresenter {
        fun getMovieList()
        fun nextPage()
        val genreId: Int
    }

}