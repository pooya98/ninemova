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
            btnNewWorldRecommend.setOnClickListener {
                recommendVieModel.fetchNewWorldRecommendMovie()
            }
            ivRecommendMovieThumnail.setOnClickListener {
                recommendVieModel.uiState.value.selectedMovie?.let {selectedMovie ->
                    findNavController().navigate(
                        RecommendFragmentDirections.actionRecommendToDetail(selectedMovie)
                    )
                }
            }
        }
    }
}
