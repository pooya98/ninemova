package com.ninemova.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ninemova.databinding.ItemRecommendMovieBinding
import com.ninemova.databinding.ItemTopMovieBinding
import com.ninemova.domain.data.Movie

class RecommendMovieListAdapter(private val items: List<Movie>) : RecyclerView.Adapter<RecommendMovieListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemRecommendMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecommendMovieBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.movie = item
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size
}