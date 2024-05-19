package com.ninemova.ui.myPage

import androidx.fragment.app.viewModels
import com.ninemova.R
import com.ninemova.databinding.FragmentMyPageBinding
import com.ninemova.ui.base.BaseFragment

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val myPageViewModel: MyPageViewModel by viewModels()
    override fun initView() {
        with(binding) {
            viewModel = myPageViewModel
        }

        initUserTagList()
        initPieChart()
    }

    private fun initUserTagList() {
        myPageViewModel.fetchUserTagResponse()
    }

    private fun initPieChart() {
        myPageViewModel.fetchKeywordResponse()
        myPageViewModel.fetchGenreResponse()
        myPageViewModel.fetchActorResponse()
    }
}
