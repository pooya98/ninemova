package com.ninemova.ui.home

import com.ninemova.R
import com.ninemova.databinding.FragmentHomeBinding
import com.ninemova.domain.data.Movie
import com.ninemova.ui.adapter.MovieListAdapter
import com.ninemova.ui.adapter.TopMovieListAdapter
import com.ninemova.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var topMovieListAdapter: TopMovieListAdapter
    private val popularMovieListAdapter = MovieListAdapter()
    override fun initView() {
        topMovieListAdapter = TopMovieListAdapter(topMovieItems)
        binding.viewpagerTopMovieList.adapter = topMovieListAdapter
        binding.indicatorViewpager.attachTo(binding.viewpagerTopMovieList)

        with(binding.recyclerviewPopularMovieList) {
            popularMovieListAdapter.submitList(popularMovieItems.toMutableList())
            adapter = popularMovieListAdapter
        }
    }

    companion object {
        val topMovieItems = listOf(
            Movie(1, "위플래쉬"),
            Movie(2, "위플래쉬"),
            Movie(3, "위플래쉬"),
            Movie(4, "위플래쉬"),
            Movie(3, "위플래쉬"),
        )

        val popularMovieItems = listOf(
            Movie(1, "위플래쉬", posterPath = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/vlHJfLsduZiILN8eYdN57kHZTcQ.jpg"),
            Movie(2, "위플래쉬", posterPath = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/vlHJfLsduZiILN8eYdN57kHZTcQ.jpg"),
            Movie(3, "위플래쉬", posterPath = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/vlHJfLsduZiILN8eYdN57kHZTcQ.jpg"),
            Movie(4, "위플래쉬", posterPath = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/vlHJfLsduZiILN8eYdN57kHZTcQ.jpg"),
            Movie(3, "위플래쉬", posterPath = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/vlHJfLsduZiILN8eYdN57kHZTcQ.jpg"),
        )
    }
}
