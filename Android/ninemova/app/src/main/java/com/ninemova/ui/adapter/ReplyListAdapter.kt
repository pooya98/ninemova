package com.ninemova.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ninemova.databinding.ItemReplyBinding
import com.ninemova.domain.data.Reply

class ReplyListAdapter : ListAdapter<Reply, ReplyViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyViewHolder {
        val binding = ItemReplyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReplyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReplyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Reply>() {
            override fun areItemsTheSame(
                oldItem: Reply,
                newItem: Reply,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Reply,
                newItem: Reply,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class ReplyViewHolder(
    private val binding: ItemReplyBinding,
) : ViewHolder(binding.root) {
    fun bind(item: Reply) {
        binding.reply = item
    }
}
