package com.ninemova.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ninemova.databinding.ItemMovieBinding
import com.ninemova.domain.data.Movie

class MovieListAdapter : ListAdapter<Movie, MovieViewHolder>(diffUtil) {

    private var movieClickListener: MovieClickListener? = null

    fun setMovieClickListener(listener: MovieClickListener) {
        movieClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, movieClickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val movieClickListener: MovieClickListener?
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Movie) {
        binding.imageUrl = item.posterPath
        itemView.setOnClickListener {
            movieClickListener?.onClick(item)
        }

    }
}

interface MovieClickListener {
    fun onClick(item: Movie)
}