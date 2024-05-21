package com.ninemova.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ninemova.databinding.ItemCommentHomeBinding
import com.ninemova.domain.data.Comment

class HomeCommentListAdapter : ListAdapter<Comment, HomeCommentViewHolder>(diffUtil) {

    private var homeCommentClickListener: HomeCommentClickListener? = null

    fun setHomeCommentClickListener(listener: HomeCommentClickListener) {
        homeCommentClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCommentViewHolder {
        val binding =
            ItemCommentHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeCommentViewHolder(binding, homeCommentClickListener)
    }

    override fun onBindViewHolder(holder: HomeCommentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Comment>() {
            override fun areItemsTheSame(
                oldItem: Comment,
                newItem: Comment,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Comment,
                newItem: Comment,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class HomeCommentViewHolder(
    private val binding: ItemCommentHomeBinding,
    private val listener: HomeCommentClickListener?
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Comment) {
        binding.comment = item
        itemView.setOnClickListener {
            listener?.onClick(item)
        }
    }
}

interface HomeCommentClickListener {
    fun onClick(item: Comment)
}
