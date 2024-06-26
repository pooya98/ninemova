package com.ninemova.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ninemova.databinding.ItemCommentBinding
import com.ninemova.domain.data.Comment

class CommentListAdapter : ListAdapter<Comment, CommentViewHolder>(diffUtil) {

    private var commentClickListener: CommentClickListener? = null

    fun setCommentClickListener(listener: CommentClickListener) {
        commentClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding, commentClickListener)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
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

class CommentViewHolder(
    private val binding: ItemCommentBinding,
    private val listener: CommentClickListener?
) : ViewHolder(binding.root) {
    fun bind(item: Comment) {
        binding.comment = item
        itemView.setOnClickListener {
            listener?.onClick(item)
        }
    }
}

interface CommentClickListener {
    fun onClick(item: Comment)
}