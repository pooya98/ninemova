package com.ninemova.ui.community

import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.ninemova.R
import com.ninemova.databinding.FragmentCommunityBinding
import com.ninemova.domain.data.CommentData
import com.ninemova.ui.base.BaseFragment

class CommunityFragment : BaseFragment<FragmentCommunityBinding>(R.layout.fragment_community) {

    private val commentListAdapter = CommentListAdapter()
    override fun initView() {
        setRecyclerView()
    }

    private fun setRecyclerView() {
        with(binding.recyclerViewComment) {
            adapter = commentListAdapter
            commentListAdapter.submitList(CommentData.commentList.toMutableList())
            layoutManager = FlexboxLayoutManager(context).apply {
                flexWrap = FlexWrap.WRAP
                flexDirection = FlexDirection.ROW
            }
        }
    }
}
