package com.destanti.MovieDB.Features.MovieList
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.destanti.MovieDB.Base.Injection
//import com.destanti.MovieDB.Features.MovieDetail.MovieDetailActivity
//import com.destanti.MovieDB.R
//import com.destanti.MovieDB.data.Model.MovieListResult
//
//class MovieListActivity: AppCompatActivity(), MovieListContract.View {
//
//    private var mPresenter: MovieListContract.Presenter? = null
//
//    var movieList: MutableList<MovieListResult> = mutableListOf()
//    lateinit var rvAdapter : MovieListAdapter
//    lateinit var layoutManager: GridLayoutManager
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_main)
//
//        MovieListPresenter(Injection.provideRepository(this), this, getIntentValue())
//
//        val actionBar = supportActionBar
//
//        if (actionBar != null){
//            actionBar.setDisplayShowHomeEnabled(true)
//            actionBar.setTitle("")
//        }
//
//        setAdapter()
//
//        mPresenter?.start()
//    }
//
//    private fun getIntentValue():Int {
//        return intent.getIntExtra("id", 80)
//    }
//
//    private fun setAdapter() {
//         layoutManager = GridLayoutManager(this, 2)
//
//        rvMovieList.layoutManager = layoutManager
//        rvAdapter = MovieListAdapter(this, this.movieList)
//        rvMovieList.adapter = rvAdapter
//
//        rvAdapter.onItemClick = {item ->
//            var intent = Intent(this, MovieDetailActivity::class.java).apply {
//                putExtra("genreId", item.id)
//            }
//
//            startActivity(intent)
//        }
//
//        rvMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {
//                    mPresenter?.nextPage()
//                    progressBar.isVisible = true
//                }
//
//                super.onScrolled(recyclerView, dx, dy)
//            }
//        })
//
//    }
//
//    override fun showMovie(movie: List<MovieListResult>) {
//
//        progressBar.isVisible = false
//        movieList.addAll(movie)
//        rvAdapter.notifyDataSetChanged()
//    }
//
//    override fun showError() {
//        progressBar.isVisible = false
//
//    }
//
//    override fun setPresenter(presenter: MovieListContract.Presenter) {
//        this.mPresenter = presenter
//    }
//
//}