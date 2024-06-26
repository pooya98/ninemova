package com.ninemova.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputLayout
import com.ninemova.R
import com.ninemova.domain.data.Comment
import com.ninemova.domain.data.Genre
import com.ninemova.domain.data.Movie
import com.ninemova.domain.data.PieChartItem
import com.ninemova.domain.data.Reply
import com.ninemova.domain.data.UserTag
import com.ninemova.ui.adapter.CommentListAdapter
import com.ninemova.ui.adapter.HomeCommentListAdapter
import com.ninemova.ui.adapter.MovieListAdapter
import com.ninemova.ui.adapter.PieChartLabelListAdapter
import com.ninemova.ui.adapter.ReplyListAdapter
import com.ninemova.ui.adapter.TopMovieListAdapter
import com.ninemova.ui.adapter.UserTagListAdapter
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel

@BindingAdapter("app:imageUri")
fun ImageView.bindImageUrl(imageUri: String?) {
    val url = "https://image.tmdb.org/t/p/original$imageUri"
    imageUri?.let {
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

@BindingAdapter("app:movies")
fun ViewPager2.bindMovies(items: List<Movie>) {
    if (this.adapter != null) {
        (this.adapter as TopMovieListAdapter).submitList(items.toMutableList())
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

@BindingAdapter("app:setPieChartItems")
fun setPieChartItems(pieChart: PieChart, pieChartItems: List<PieChartItem>?) {
    pieChartItems?.let {
        pieChart.clearChart()
        it.forEach { pieChartItem ->
            pieChart.addPieSlice(
                PieModel(
                    pieChartItem.name,
                    pieChartItem.rate,
                    Color.parseColor(pieChartItem.color),
                ),
            )
        }
        pieChart.startAnimation()
    }
}

@BindingAdapter("app:setPieChartLabels")
fun setPieChartLabels(listView: ListView, pieChartItems: List<PieChartItem>?) {
    if (pieChartItems != null) {
        val adapter = PieChartLabelListAdapter(listView.context, pieChartItems)
        listView.adapter = adapter
    }
}

@BindingAdapter("app:setPieChartLabelText")
fun setPieChartLabelText(textView: TextView, pieChartItem: PieChartItem) {
    textView.setText("${pieChartItem.name} (${pieChartItem.rate}%)")
}

@BindingAdapter("app:setPieChartLabelColor")
fun setPieChartLabelColor(view: View, pieChartItem: PieChartItem) {
    view.setBackgroundColor(Color.parseColor(pieChartItem.color))
}

@BindingAdapter("app:setUserTagItems")
fun setUserTagItems(listView: ListView, userTagItems: List<UserTag>?) {
    if (userTagItems != null) {
        val adapter = UserTagListAdapter(listView.context, userTagItems)
        listView.adapter = adapter
    }
}

@BindingAdapter("app:setUserTagItemText")
fun setUserTagItemText(textView: TextView, userTagText: String) {
    textView.setText("\"${userTagText}\"")
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

@BindingAdapter("app:comments")
fun RecyclerView.bindComments(items: List<Comment>) {
    if (this.adapter != null) {
        (this.adapter as CommentListAdapter).submitList(items.toMutableList())
    }
}

@BindingAdapter("app:homeComments")
fun RecyclerView.bindHomeComments(items: List<Comment>) {
    if (this.adapter != null) {
        (this.adapter as HomeCommentListAdapter).submitList(items.toMutableList())
    }
}

@BindingAdapter("app:setReleaseYear")
fun setReleaseYear(textView: TextView, releaseDate: String) {
    textView.setText("(${releaseDate.substring(0, 4)})")
}

@BindingAdapter("app:setCommentText")
fun setCommentText(textView: TextView, userTagText: String) {
    textView.setText("\"${userTagText}\"")
}

@BindingAdapter("app:replies")
fun RecyclerView.bindReplies(items: List<Reply>) {
    if (this.adapter != null) {
        (this.adapter as ReplyListAdapter).submitList(items.toMutableList())
    }
}

@BindingAdapter("app:profileImage")
fun ImageView.bindProfile(imageUri: String?) {
    Glide.with(this)
        .load(imageUri)
        .circleCrop()
        .placeholder(R.drawable.image_user)
        .into(this)
}

@BindingAdapter("app:loading")
fun bindLoading(circularProgressIndicator: CircularProgressIndicator, items: List<Any>?) {
    if (items == null) {
        circularProgressIndicator.visibility = View.VISIBLE
    } else {
        circularProgressIndicator.visibility = View.GONE
    }
}

@BindingAdapter("app:isLiked")
fun ImageView.bindLiked(isLiked: Boolean) {
    val drawableID = if (isLiked) {
        R.drawable.ic_favorite
    } else {
        R.drawable.ic_unfavorite
    }
    setImageResource(drawableID)
}