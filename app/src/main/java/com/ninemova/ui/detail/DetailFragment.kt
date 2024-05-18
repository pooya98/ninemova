package com.ninemova.ui.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.ninemova.R
import com.ninemova.databinding.FragmentDetailBinding
import com.ninemova.ui.base.BaseFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
            lifecycle.addObserver(videoTrailer)
        }
        collectFlow()
    }

    private fun collectFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                collectVideoId()
                collectUiEvent()
            }
        }
    }

    private suspend fun collectVideoId() {
        detailViewModel.uiState.collectLatest { uiState ->
            uiState.videoId?.let { videoId ->
                binding.videoTrailer.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
            }
        }
    }

    private suspend fun collectUiEvent() {
        detailViewModel.uiEvent.collectLatest { uiEvent ->
            when (uiEvent) {
                is DetailViewEvent.Error -> {
                    showMessage(uiEvent.errorMessage)
                }

                is DetailViewEvent.Success -> {
                }
            }
        }
    }

    override fun onDestroyView() {
        binding.videoTrailer.release()
        super.onDestroyView()
    }
}
