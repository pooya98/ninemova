package com.ninemova.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ninemova.R
import com.ninemova.databinding.FragmentHomeBinding
import com.ninemova.domain.data.Movie
import com.ninemova.ui.adapter.MovieClickListener
import com.ninemova.ui.adapter.MovieListAdapter
import com.ninemova.ui.adapter.TopMovieClickListener
import com.ninemova.ui.adapter.TopMovieListAdapter
import com.ninemova.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()
    private var topMovieListAdapter = TopMovieListAdapter()
    private val popularMovieListAdapter = MovieListAdapter()
    override fun initView() {
        setNowPlayingMoviesRecyclerView()
        setPopularMoviesRecyclerView()
        with(binding) {
            viewModel = homeViewModel
        }
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
}
