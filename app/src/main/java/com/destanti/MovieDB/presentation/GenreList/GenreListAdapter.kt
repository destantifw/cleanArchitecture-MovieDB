package com.destanti.MovieDB.presentation.GenreList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.destanti.MovieDB.core.BaseViewHolder
import com.destanti.MovieDB.data.Model.Genre
import com.destanti.MovieDB.data.Model.MovieListResult
import com.destanti.MovieDB.databinding.ItemGenreBinding
import com.destanti.MovieDB.databinding.ItemMovieListBinding

class GenreListAdapter (
    private val context: Context,
    private val itemClickListener: OnGenreClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var genreList = listOf<Genre>()

    interface OnGenreClickListener {
        fun onGenreClick(genre: Genre, position: Int)
    }

    fun setGenreList(genreList: List<Genre>) {
        this.genreList = genreList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemGenreBinding.inflate(LayoutInflater.from(context), parent, false)

        val holder = MainViewHolder(itemBinding)

        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION } ?: return@setOnClickListener
            itemClickListener.onGenreClick(genreList[position], position)
        }



        return holder
    }

    override fun getItemCount(): Int = genreList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(genreList[position])
        }
    }

    private inner class MainViewHolder(
        val binding: ItemGenreBinding
    ) : BaseViewHolder<Genre>(binding.root) {
        override fun bind(item: Genre) = with(binding) {
            tvGenreName.text = item.name

        }
    }
}