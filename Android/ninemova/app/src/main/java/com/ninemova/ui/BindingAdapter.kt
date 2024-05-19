package com.ninemova.ui

import android.content.res.ColorStateList
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputLayout
import com.ninemova.R
import com.ninemova.domain.data.Genre
import com.ninemova.domain.data.Movie
import com.ninemova.ui.adapter.MovieListAdapter

@BindingAdapter("app:imageUri")
fun ImageView.bindImageUrl(imageUri: String?) {
    val url = "https://image.tmdb.org/t/p/original$imageUri"
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

@BindingAdapter("app:chips")
fun ChipGroup.bindChips(items: List<Genre>) {
    removeAllViews()
    items.forEach { genre ->
        Chip(context).apply {
            text = genre.name
            setChipIconResource(R.mipmap.ic_app_logo_round)
            setChipBackgroundColorResource(R.color.gray)
            chipStrokeWidth = 5f
            addView(this)
        }
    }
}

@BindingAdapter("app:enabledColor")
fun Button.bindEnabledColor(enabled: Boolean) {
    val colorResId = if (enabled.not()) {
        R.color.gray
    } else {
        R.color.main_color_1
    }
    val color = ContextCompat.getColor(context, colorResId)
    setBackgroundColor(color)
}

@BindingAdapter("app:password", "app:repassword")
fun TextInputLayout.bindValidation(password: String, rePassword: String) {
    if (password != rePassword && rePassword.isNotEmpty() && password.isNotEmpty()) {
        error = ContextCompat.getString(context, R.string.invalid_password_message)
        val color = ContextCompat.getColor(context, R.color.red)
        setErrorTextColor(ColorStateList.valueOf(color))
    } else {
        error = null
    }
}
