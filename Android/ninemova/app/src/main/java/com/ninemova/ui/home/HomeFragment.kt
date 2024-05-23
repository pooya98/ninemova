package com.ninemova.ui.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ninemova.R
import com.ninemova.databinding.FragmentHomeBinding
import com.ninemova.domain.data.Comment
import com.ninemova.domain.data.Movie
import com.ninemova.ui.adapter.HomeCommentClickListener
import com.ninemova.ui.adapter.HomeCommentListAdapter
import com.ninemova.ui.adapter.MovieClickListener
import com.ninemova.ui.adapter.MovieListAdapter
import com.ninemova.ui.adapter.TopMovieClickListener
import com.ninemova.ui.adapter.TopMovieListAdapter
import com.ninemova.ui.base.BaseFragment
import com.ninemova.ui.util.runTickerFlow

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()
    private var topMovieListAdapter = TopMovieListAdapter()
    private val popularMovieListAdapter = MovieListAdapter()
    private val recentCommentListAdapter = HomeCommentListAdapter()
    override fun initView() {
        loadData()
        setNowPlayingMoviesRecyclerView()
        setPopularMoviesRecyclerView()
        setRecentCommentRecyclerView()
        with(binding) {
            viewModel = homeViewModel
        }
    }

    private fun loadData() {
        runTickerFlow(
            interval = 1000L,
            scope = lifecycleScope,
            action = { homeViewModel.searchRecentComments(lifecycleScope) },
        )
    }

    private fun setNowPlayingMoviesRecyclerView() {
        with(binding) {
            topMovieListAdapter.setMovieClickListener(object : TopMovieClickListener {
                override fun onClick(item: Movie) {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeToDetail(item),
                    )
                }
            })
            viewpagerTopMovieList.adapter = topMovieListAdapter
            indicatorViewpager.attachTo(binding.viewpagerTopMovieList)
        }
    }

    private fun setPopularMoviesRecyclerView() {
        with(binding.recyclerviewPopularMovieList) {
            popularMovieListAdapter.setMovieClickListener(object : MovieClickListener {
                override fun onClick(item: Movie) {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeToDetail(item),
                    )
                }
            })
            adapter = popularMovieListAdapter
        }
    }

    private fun setRecentCommentRecyclerView() {
        with(binding.recyclerviewRecentCommentList) {
            recentCommentListAdapter.setHomeCommentClickListener(object : HomeCommentClickListener {
                override fun onClick(item: Comment) {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeToPost(item),
                    )
                    HomeFragmentDirections.actionHomeToPost(item)
                }
            })
            adapter = recentCommentListAdapter
        }
    }
}
