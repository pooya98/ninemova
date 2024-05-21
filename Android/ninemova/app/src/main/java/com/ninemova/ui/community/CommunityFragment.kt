package com.ninemova.ui.community

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ninemova.R
import com.ninemova.databinding.FragmentCommunityBinding
import com.ninemova.domain.data.Comment
import com.ninemova.ui.adapter.CommentClickListener
import com.ninemova.ui.adapter.CommentListAdapter
import com.ninemova.ui.base.BaseFragment

class CommunityFragment : BaseFragment<FragmentCommunityBinding>(R.layout.fragment_community) {

    private val communityViewModel: CommunityViewModel by viewModels()
    private val commentListAdapter = CommentListAdapter()
    override fun initView() {
        binding.viewModel = communityViewModel
        setRecyclerView()
    }

    private fun setRecyclerView() {
        with(binding.recyclerViewComment) {
            commentListAdapter.setCommentClickListener(object : CommentClickListener {
                override fun onClick(item: Comment) {
                    findNavController().navigate(
                        CommunityFragmentDirections.actionCommunityToPost(
                            item
                        )
                    )
                }
            })
            adapter = commentListAdapter
        }
    }
}
