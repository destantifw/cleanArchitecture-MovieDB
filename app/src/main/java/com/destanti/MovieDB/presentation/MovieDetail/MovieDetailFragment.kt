package com.destanti.MovieDB.presentation.MovieDetail

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.destanti.MovieDB.R
import com.destanti.MovieDB.core.Resource
import com.destanti.MovieDB.data.Model.MovieListResult
import com.destanti.MovieDB.databinding.FragmentMovieDetailBinding
import com.destanti.MovieDB.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_detail.*

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {
    private val viewModel by activityViewModels<MovieDetailViewModel>()

    private lateinit var movie: MovieListResult
    private lateinit var binding: FragmentMovieDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let {
            MovieDetailFragmentArgs.fromBundle(it).also { args ->
                movie = args.moviedetail
            }
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieDetailBinding.bind(view)

        binding.icStar.setImageResource(R.mipmap.icon_star)
        binding.tfMovieTitle.text = movie.title
//        binding.genreList.text = movie.genres[0].name
        binding.tvOverview.text = movie.overview
//        binding.tvCompanies.text = "Companis: " + movie [0].name
        binding.tvLanguages.text = "Companies: " + movie.originalLanguage
        binding.tvCountries.text = "Country: Indonesia"
        binding.tvRateAvg.text = movie.voteAverage.toString()
        binding.tvRateCount.text = "("+movie.voteCount.toString()+")"


        viewModel.setMovieId(movie.id)
        fetchData()
    }

    fun showVideo(key: String) {
        binding.trailerWebView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        })

        val webSettings: WebSettings = trailerWebView.getSettings()
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true

        binding.trailerWebView.loadUrl("https://www.youtube.com/embed/$key")
    }

    fun fetchData(){

        viewModel.fetchVideoList.observe(viewLifecycleOwner) { result ->

            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    if (result.data.isEmpty()) {
                        return@observe
                    }
                    showVideo(result.data[0].key)
                }
                is Resource.Failure -> {
                    showToast("${result.exception}")
                }
            }
        }

    }
}