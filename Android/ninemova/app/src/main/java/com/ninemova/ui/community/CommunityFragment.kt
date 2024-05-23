package com.ninemova.ui.community

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ninemova.R
import com.ninemova.databinding.FragmentCommunityBinding
import com.ninemova.domain.data.Comment
import com.ninemova.ui.adapter.CommentClickListener
import com.ninemova.ui.adapter.CommentListAdapter
import com.ninemova.ui.base.BaseFragment
import com.ninemova.ui.util.runTickerFlow

class CommunityFragment : BaseFragment<FragmentCommunityBinding>(R.layout.fragment_community) {

    private val communityViewModel: CommunityViewModel by viewModels()
    private val commentListAdapter = CommentListAdapter()
    override fun initView() {
        binding.viewModel = communityViewModel
        setRecyclerView()
        loadData()
    }

    private fun loadData() {
        runTickerFlow(
            interval = 1000L,
            scope = lifecycleScope,
            action = { communityViewModel.loadComments(lifecycleScope) },
        )
    }

    private fun setRecyclerView() {
        with(binding.recyclerViewComment) {
            commentListAdapter.setCommentClickListener(object : CommentClickListener {
                override fun onClick(item: Comment) {
                    findNavController().navigate(
                        CommunityFragmentDirections.actionCommunityToPost(
                            item,
                        ),
                    )
                }
            })
            adapter = commentListAdapter
        }
    }
}
