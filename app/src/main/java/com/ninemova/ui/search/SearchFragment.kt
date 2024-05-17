package com.ninemova.ui.search

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.ninemova.R
import com.ninemova.databinding.FragmentSearchBinding
import com.ninemova.ui.adapter.MovieListAdapter
import com.ninemova.ui.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val searchViewModel: SearchViewModel by viewModels()
    private val movieListAdapter = MovieListAdapter()
    override fun initView() {
        collectUiEvent()
        setRecyclerView()
        with(binding) {
            viewModel = searchViewModel
            textInputLayoutSearch.setStartIconOnClickListener {
                searchViewModel.searchMovies()
            }
        }
    }

    private fun setRecyclerView() {
        with(binding.recyclerViewMovie) {
            adapter = movieListAdapter
            layoutManager = FlexboxLayoutManager(context).apply {
                flexWrap = FlexWrap.WRAP
                flexDirection = FlexDirection.ROW
            }
        }
    }

    private fun collectUiEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.uiEvent.collectLatest { event ->
                    when (event) {
                        is SearchViewEvent.Error -> {
                            showMessage(event.errorMessage)
                        }

                        is SearchViewEvent.Success -> {
                        }
                    }
                }
            }
        }
    }
}
