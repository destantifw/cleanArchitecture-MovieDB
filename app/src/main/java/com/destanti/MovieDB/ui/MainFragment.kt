package com.destanti.MovieDB.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.destanti.MovieDB.R
import com.destanti.MovieDB.core.Resource
import com.destanti.MovieDB.data.Model.MovieListResult
import com.destanti.MovieDB.databinding.FragmentMainBinding
import com.destanti.MovieDB.presentation.MainViewModel
import com.destanti.MovieDB.utils.hide
import com.destanti.MovieDB.utils.show
import com.destanti.MovieDB.utils.showIf
import com.destanti.MovieDB.utils.showToast
import com.g.tragosapp.ui.MainAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main),
    MainAdapter.OnMovieClickListener {
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mainAdapter = MainAdapter(requireContext(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)

        binding.rvMovieList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovieList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvMovieList.adapter = mainAdapter


        viewModel.fetchCocktailList.observe(viewLifecycleOwner) { result ->
            binding.progressBar.showIf { result is Resource.Loading }

            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.show()
                }
                is Resource.Success -> {
                    if (result.data.isEmpty()) {
                        binding.progressBar.hide()
                        return@observe
                    }
                    mainAdapter.setMovielList(result.data)
                    binding.progressBar.hide()
                }
                is Resource.Failure -> {
                    showToast("${result.exception}")
                }
            }
        }
    }

    override fun onMovieClick(movie: MovieListResult, position: Int) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailFragment(movie)
        )
    }
}