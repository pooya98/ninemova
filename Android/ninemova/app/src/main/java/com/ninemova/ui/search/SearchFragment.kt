package com.ninemova.ui.search

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.ninemova.R
import com.ninemova.databinding.FragmentSearchBinding
import com.ninemova.domain.data.Movie
import com.ninemova.ui.adapter.MovieClickListener
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
            movieListAdapter.setMovieClickListener(object : MovieClickListener {
                override fun onClick(item: Movie) {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchToDetail(item),
                    )
                }
            })
            adapter = movieListAdapter
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
