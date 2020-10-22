package com.destanti.MovieDB.ui.MovieDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.destanti.MovieDB.R
import com.destanti.MovieDB.data.Model.MovieListResult
import com.destanti.MovieDB.databinding.FragmentMovieDetailBinding
import com.destanti.MovieDB.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {
    private val viewModel by activityViewModels<MainViewModel>()

    private lateinit var movie: MovieListResult


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

        val binding = FragmentMovieDetailBinding.bind(view)

        binding.icStar.setImageResource(R.mipmap.icon_star)
        binding.tfMovieTitle.text = movie.title
//        binding.genreList.text = movie.genres[0].name
        binding.tvOverview.text = movie.overview
//        binding.tvCompanies.text = "Companis: " + movie [0].name
        binding.tvLanguages.text = "Companies: " + movie.originalLanguage
        binding.tvCountries.text = "Country: Indonesia"
        binding.tvRateAvg.text = movie.voteAverage.toString()
        binding.tvRateCount.text = movie.voteCount.toString()

    }
}