package com.destanti.MovieDB.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.destanti.MovieDB.core.BaseViewHolder
import com.destanti.MovieDB.data.Model.MovieListResult
import com.destanti.MovieDB.databinding.ItemMovieListBinding

class MainAdapter(
    private val context: Context,
    private val itemClickListener: OnMovieClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var movieList = listOf<MovieListResult>()

    interface OnMovieClickListener {
        fun onMovieClick(movie: MovieListResult, position: Int)
    }

    fun setMovielList(movieList: List<MovieListResult>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemMovieListBinding.inflate(LayoutInflater.from(context), parent, false)

        val holder = MainViewHolder(itemBinding)

        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != NO_POSITION } ?: return@setOnClickListener
            itemClickListener.onMovieClick(movieList[position], position)
        }



        return holder
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(movieList[position])
        }
    }

    private inner class MainViewHolder(
        val binding: ItemMovieListBinding
    ) : BaseViewHolder<MovieListResult>(binding.root) {
        override fun bind(item: MovieListResult) = with(binding) {

            val link = "https://image.tmdb.org/t/p/w500/"+item.posterPath
            Glide.with(context)
                .load(link)
                .centerCrop()
                .into(ivItemMovie)

        tvMovieTitle.text = item.title

        }
    }
}