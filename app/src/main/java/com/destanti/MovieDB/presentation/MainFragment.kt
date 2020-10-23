package com.destanti.MovieDB.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.destanti.MovieDB.R
import com.destanti.MovieDB.core.Resource
import com.destanti.MovieDB.data.Model.Genre
import com.destanti.MovieDB.data.Model.Genres
import com.destanti.MovieDB.data.Model.MovieListResult
import com.destanti.MovieDB.databinding.FragmentMainBinding
import com.destanti.MovieDB.utils.hide
import com.destanti.MovieDB.utils.show
import com.destanti.MovieDB.utils.showIf
import com.destanti.MovieDB.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main),
    MainAdapter.OnMovieClickListener {
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var mainAdapter: MainAdapter
    lateinit var layoutManager: GridLayoutManager
    private lateinit var binding: FragmentMainBinding
    var page = 1
    lateinit var genreList:Genres

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mainAdapter = MainAdapter(requireContext(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)
        layoutManager = GridLayoutManager(requireContext(), 2)

        binding.rvMovieList.layoutManager = layoutManager
        binding.rvMovieList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvMovieList.adapter = mainAdapter

        viewModel.getGenreList().observe(viewLifecycleOwner) { result ->
//            binding.progressBar.showIf { result is Resource.Loading }

            when (result) {
                is Resource.Loading -> {
//                    binding.progressBar.show()
                }
                is Resource.Success -> {
                    if (result.data.isEmpty()) {
//                        binding.progressBar.hide()
                        return@observe
                    }
                    genreList = Genres(result.data)
                    getData()
                }
                is Resource.Failure -> {
//                    showToast("${result.exception}")
                }
            }
        }

//        getData()

        binding.rvMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {
                    viewModel.setPage(page)
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })

        binding.buttonGenre.setOnClickListener({
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToGenreList(genreList)
            )
        })


    }

    fun getData(){
        viewModel.fetchMovieList.observe(viewLifecycleOwner) { result ->
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
                    page += 1
                    mainAdapter.setMovielList(result.data)
                    mainAdapter.notifyDataSetChanged()
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