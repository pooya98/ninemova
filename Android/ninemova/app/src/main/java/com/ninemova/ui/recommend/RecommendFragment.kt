package com.ninemova.ui.recommend

import com.ninemova.R
import com.ninemova.databinding.FragmentRecommendBinding
import com.ninemova.domain.data.Movie
import com.ninemova.ui.adapter.RecommendMovieListAdapter
import com.ninemova.ui.base.BaseFragment

class RecommendFragment : BaseFragment<FragmentRecommendBinding>(R.layout.fragment_recommend) {

    private lateinit var recommendMovieListAdapter: RecommendMovieListAdapter
    override fun initView() {
        recommendMovieListAdapter = RecommendMovieListAdapter(recommendMovieItems)
        binding.viewPagerRecommendMovieList.adapter = recommendMovieListAdapter
        binding.indicatorViewpagerRecommendMovieList.attachTo(binding.viewPagerRecommendMovieList)
    }

    companion object {
        val recommendMovieItems = listOf(
            Movie(1, "혹성탈출: 종의 전쟁", posterPath = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/vlHJfLsduZiILN8eYdN57kHZTcQ.jpg"),
            Movie(2, "혹성탈출: 종의 전쟁", posterPath = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/vlHJfLsduZiILN8eYdN57kHZTcQ.jpg"),
            Movie(3, "혹성탈출: 종의 전쟁", posterPath = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/vlHJfLsduZiILN8eYdN57kHZTcQ.jpg"),
            Movie(4, "혹성탈출: 종의 전쟁", posterPath = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/vlHJfLsduZiILN8eYdN57kHZTcQ.jpg"),
            Movie(3, "혹성탈출: 종의 전쟁", posterPath = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/vlHJfLsduZiILN8eYdN57kHZTcQ.jpg"),
        )
    }
}
