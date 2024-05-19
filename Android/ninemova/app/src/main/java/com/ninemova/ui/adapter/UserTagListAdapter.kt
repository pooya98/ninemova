package com.ninemova.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.ninemova.R
import com.ninemova.databinding.ItemUserTagBinding
import com.ninemova.domain.data.UserTag

class UserTagListAdapter(context: Context, private val userTagItems: List<UserTag>) :
    ArrayAdapter<UserTag>(context, 0, userTagItems) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemUserTagBinding = if (convertView == null) {
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_user_tag,
                parent,
                false
            )
        } else {
            DataBindingUtil.getBinding(convertView)!!
        }

        binding.userTagItem = getItem(position)
        binding.executePendingBindings()
        return binding.root
    }
}
