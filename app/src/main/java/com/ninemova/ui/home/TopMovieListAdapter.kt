package com.ninemova.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ninemova.databinding.ItemTopMovieBinding
import com.ninemova.domain.data.Movie

class TopMovieListAdapter(private val items: List<Movie>) : RecyclerView.Adapter<TopMovieListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemTopMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTopMovieBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.movie = item
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size
}