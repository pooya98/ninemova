package com.ninemova.ui.recommend

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ninemova.R
import com.ninemova.databinding.FragmentRecommendBinding
import com.ninemova.ui.base.BaseFragment

class RecommendFragment : BaseFragment<FragmentRecommendBinding>(R.layout.fragment_recommend) {

    private val recommendVieModel: RecommendViewModel by viewModels()
    override fun initView() {
        with(binding) {
            viewModel = recommendVieModel
            btnAiRecommend.setOnClickListener {
                recommendVieModel.fetchAiRecommendMovie()
            }
            binding.btnNewWorldRecommend.setOnClickListener {
                recommendVieModel.fetchNewWorldRecommendMovie()
            }
            binding.ivRecommendMovieThumnail.setOnClickListener {
                recommendVieModel.uiState.value.selectedMovie?.let {
                    findNavController().navigate(
                        RecommendFragmentDirections.actionRecommendToDetail(recommendVieModel.uiState.value.selectedMovie!!)
                    )
                }
            }
        }
    }
}
