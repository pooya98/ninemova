package com.ninemova.ui.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ninemova.databinding.ItemCommentBinding
import com.ninemova.domain.data.CommentData

class CommentListAdapter : ListAdapter<CommentData, CommentViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<CommentData>() {
            override fun areItemsTheSame(
                oldItem: CommentData,
                newItem: CommentData,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CommentData,
                newItem: CommentData,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class CommentViewHolder(private val binding: ItemCommentBinding) : ViewHolder(binding.root) {
    fun bind(item: CommentData) {
        binding.comment = item
    }
}