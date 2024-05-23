package com.ninemova.ui.post

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.ninemova.R
import com.ninemova.databinding.FragmentPostBinding
import com.ninemova.ui.adapter.ReplyListAdapter
import com.ninemova.ui.base.BaseFragment
import com.ninemova.ui.util.runTickerFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        collectUiEvent()
        loadData()
    }

    private fun loadData() {
        runTickerFlow(
            interval = 1000L,
            scope = lifecycleScope,
            action = { postViewModel.loadReplies(lifecycleScope) },
        )
    }

    private fun setRecyclerView() {
        with(binding.recyclerViewReply) {
            adapter = replyListAdapter
        }
    }

    private fun collectUiEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                postViewModel.uiEvent.collectLatest { event ->
                    when (event) {
                        is PostViewEvent.Success -> {}

                        is PostViewEvent.Error -> {
                            showMessage(event.errorMessage)
                        }
                    }
                    binding.etReply.text = null
                }
            }
        }
    }
}
