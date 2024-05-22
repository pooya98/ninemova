package com.ninemova.ui.recommend

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.ninemova.R
import com.ninemova.databinding.FragmentRecommendBinding
import com.ninemova.ui.base.BaseFragment
import com.ninemova.ui.dialog.LoadingDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
                recommendVieModel.uiState.value.selectedMovie?.let { selectedMovie ->
                    findNavController().navigate(
                        RecommendFragmentDirections.actionRecommendToDetail(selectedMovie)
                    )
                }
            }
        }
        collectUiEvent()
        showLoadingDialog()
    }

    private fun collectUiEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                recommendVieModel.uiEvent.collectLatest { event ->
                    when (event) {
                        is RecommendViewEvent.SearchSuccess -> {
                            recommendVieModel.fetchNewWorldRecommendMovie()
                            recommendVieModel.fetchAiRecommendMovie()
                        }

                        is RecommendViewEvent.Error -> {
                            showMessage(event.errorMessage)
                        }
                    }
                }
            }
        }
    }

    private fun showLoadingDialog() {
        val dialog = LoadingDialog(recommendVieModel.isLoading)
        dialog.show(requireActivity().supportFragmentManager, "loadingDialog")
    }
}
