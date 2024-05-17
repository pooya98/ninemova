package com.ninemova.ui.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ninemova.R
import com.ninemova.databinding.FragmentDetailBinding
import com.ninemova.ui.base.BaseFragment


class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val args: DetailFragmentArgs by navArgs()
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel.setMovie(args.movie)
    }

    override fun initView() {
        with(binding) {
            viewModel = detailViewModel
        }
    }
}