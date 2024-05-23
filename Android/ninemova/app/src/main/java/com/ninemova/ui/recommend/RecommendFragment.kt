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
                recommendVieModel.selectTab(1)
            }
            btnNewWorldRecommend.setOnClickListener {
                recommendVieModel.selectTab(2)
            }
            ivRecommendMovieThumnail.setOnClickListener {
                recommendVieModel.uiState.value.selectedMovie?.let { selectedMovie ->
                    findNavController().navigate(
                        RecommendFragmentDirections.actionRecommendToDetail(selectedMovie)
                    )
                }
            }
            btnRefresh.setOnClickListener {
                showLoadingDialog()
                recommendVieModel.setLoadingOn()
                recommendVieModel.loadData()
            }
        }
        collectUiEvent()
        showLoadingDialog()
        recommendVieModel.selectTab(1)
    }

    private fun collectUiEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                recommendVieModel.uiEvent.collectLatest { event ->
                    when (event) {
                        is RecommendViewEvent.ChatGptSuccess -> {
                            if (event.flagCode == 1) {
                                recommendVieModel.fetchAiRecommendMovie()
                            } else {
                                recommendVieModel.fetchNewWorldRecommendMovie()
                            }
                        }

                        is RecommendViewEvent.ChatGptError -> {
                            if (event.errorCode == 1) {
                                recommendVieModel.getAiMovieTitle()
                            } else {
                                recommendVieModel.getNewMovieTitle()
                            }
                        }

                        is RecommendViewEvent.TmdbApiSuccess -> {
                            if (event.flagCode == 1) {
                                if (recommendVieModel.uiState.value.newWorldMovie != null) {
                                    recommendVieModel.setLoadingOff()
                                }
                            } else {
                                if (recommendVieModel.uiState.value.aiRecommendMovie != null) {
                                    recommendVieModel.setLoadingOff()
                                }
                            }
                        }

                        is RecommendViewEvent.TmdbApiError -> {
                            if (event.errorCode == 1) {
                                recommendVieModel.getAiMovieTitle()
                            } else {
                                recommendVieModel.getNewMovieTitle()
                            }
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
