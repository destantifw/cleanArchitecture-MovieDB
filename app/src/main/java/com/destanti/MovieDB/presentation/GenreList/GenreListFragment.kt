package com.destanti.MovieDB.presentation.GenreList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.destanti.MovieDB.R
import com.destanti.MovieDB.data.Model.Genre
import com.destanti.MovieDB.data.Model.Genres
import com.destanti.MovieDB.data.Model.MovieListResult
import com.destanti.MovieDB.databinding.FragmentGenreBinding
import com.destanti.MovieDB.presentation.MovieDetail.MovieDetailFragmentArgs

class GenreListFragment : Fragment(R.layout.fragment_genre),
    GenreListAdapter.OnGenreClickListener {
    private lateinit var adapter: GenreListAdapter
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var binding: FragmentGenreBinding
    var page = 1

    private lateinit var genres: List<Genre>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        adapter = GenreListAdapter(requireContext(), this)
        requireArguments().let {
            GenreListFragmentArgs.fromBundle(it).also { args ->
                genres = args.genreList.genres
            }
        }
        adapter.setGenreList(genres)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGenreBinding.bind(view)
        layoutManager = LinearLayoutManager(requireContext())

        binding.rvGenreList.layoutManager = layoutManager
        binding.rvGenreList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.HORIZONTAL
            )
        )
        binding.rvGenreList.adapter = adapter

    }

    override fun onGenreClick(genre: Genre, position: Int) {
        TODO("Not yet implemented")
    }

}