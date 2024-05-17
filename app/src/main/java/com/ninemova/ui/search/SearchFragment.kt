package com.ninemova.ui.search

import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.ninemova.R
import com.ninemova.databinding.FragmentSearchBinding
import com.ninemova.ui.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val movieListAdapter = MovieListAdapter()
    override fun initView() {
        setRecyclerView()
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
}
