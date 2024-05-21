package com.ninemova.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ninemova.databinding.ItemMovieBinding
import com.ninemova.databinding.ItemTopMovieBinding
import com.ninemova.domain.data.Movie

class TopMovieListAdapter : ListAdapter<Movie, TopMovieViewHolder>(diffUtil) {

    private var topMovieClickListener: TopMovieClickListener? = null

    fun setMovieClickListener(listener: TopMovieClickListener) {
        topMovieClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMovieViewHolder {
        val binding =
            ItemTopMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopMovieViewHolder(binding, topMovieClickListener)
    }

    override fun onBindViewHolder(holder: TopMovieViewHolder, position: Int) {
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

class TopMovieViewHolder(
    private val binding: ItemTopMovieBinding,
    private val topMovieClickListener: TopMovieClickListener?
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Movie) {
        binding.movie = item
        itemView.setOnClickListener {
            topMovieClickListener?.onClick(item)
        }

    }
}

interface TopMovieClickListener {
    fun onClick(item: Movie)
}
