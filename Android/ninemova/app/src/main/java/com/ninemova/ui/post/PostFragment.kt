package com.ninemova.ui.post

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ninemova.R
import com.ninemova.databinding.FragmentPostBinding
import com.ninemova.ui.adapter.ReplyListAdapter
import com.ninemova.ui.base.BaseFragment

class PostFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_post) {

    private val args: PostFragmentArgs by navArgs()
    private val postViewModel: PostViewModel by viewModels()
    private val replyListAdapter = ReplyListAdapter()

    override fun initView() {
        with(binding) {
            postViewModel.setComment(args.comment)
            viewModel = postViewModel
        }
        setRecyclerView()
    }

    private fun setRecyclerView() {
        with(binding.recyclerViewReply) {
            adapter = replyListAdapter
        }
    }
}
