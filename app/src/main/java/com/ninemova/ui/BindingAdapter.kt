package com.ninemova.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ninemova.domain.data.Movie
import com.ninemova.ui.search.MovieListAdapter

@BindingAdapter("app:imageUri")
fun ImageView.bindImageUrl(imageUri: String?) {
    val url = "https://image.tmdb.org/t/p/w154$imageUri"
    imageUri?.let { uri ->
        Glide.with(this)
            .load(url)
            .into(this)
    }
}

@BindingAdapter("app:movies")
fun RecyclerView.bindMovies(items: List<Movie>) {
    if (this.adapter != null) {
        (this.adapter as MovieListAdapter).submitList(items.toMutableList())
    }
}
