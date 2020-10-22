package com.destanti.MovieDB.Features.MovieList

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.destanti.MovieDB.R
import com.destanti.MovieDB.data.Model.MovieListResult
import com.destanti.MovieDB.data.Model.ReviewResult
import kotlinx.android.synthetic.main.item_movie_list.view.*
import kotlinx.android.synthetic.main.item_user_review.view.*

class MovieListAdapter(private val mContext: Context, private val movieList: List<MovieListResult>)
    : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_movie_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(movieList[position])
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemCount(): Int = movieList.size

    var onItemClick: ((MovieListResult) -> Unit)? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(movieList[adapterPosition])
            }
        }

        fun bindItem(items: MovieListResult) {
            itemView.tvMovieTitle.text = items.title
            val link = "https://image.tmdb.org/t/p/w500/"+items.posterPath
            Glide.with(mContext)
                .load(link)
                .fitCenter()
                .into(itemView.ivItemMovie)

        }
    }
}
