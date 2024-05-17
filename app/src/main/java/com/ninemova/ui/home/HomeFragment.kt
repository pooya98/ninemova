package com.ninemova.ui.home

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.ninemova.R
import com.ninemova.databinding.FragmentHomeBinding
import com.ninemova.domain.data.Comment
import com.ninemova.domain.data.Movie
import com.ninemova.ui.base.BaseFragment
import com.ninemova.ui.community.CommentListAdapter

private const val TAG = "HomeFragment_싸피"
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var topMovieListAdapter: TopMovieListAdapter
    private val popularMovieListAdapter = PopularMovieListAdapter()
    override fun initView() {
        topMovieListAdapter = TopMovieListAdapter(topMovieItems)
        binding.viewpagerTopMovieList.adapter = topMovieListAdapter
        binding.indicatorViewpager.attachTo(binding.viewpagerTopMovieList)

        with(binding.recyclerviewPopularMovieList) {
            popularMovieListAdapter.submitList(popularMovieItems.toMutableList())
            Log.d(TAG, "initView: $popularMovieItems")
            //layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
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
            Movie(1, "위플래쉬"),
            Movie(2, "위플래쉬"),
            Movie(3, "위플래쉬"),
            Movie(4, "위플래쉬"),
            Movie(3, "위플래쉬"),
        )
    }
}
