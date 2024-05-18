package com.ninemova.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninemova.Network.RepositoryUtils
import com.ninemova.domain.data.Movie
import com.ninemova.ui.util.ErrorMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val genreRepository = RepositoryUtils.genreRepository
    private val youtubeRepository = RepositoryUtils.youtubeRepository
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState
    private val _uiEvent = MutableSharedFlow<DetailViewEvent>()
    val uiEvent: SharedFlow<DetailViewEvent> = _uiEvent

    fun setMovie(item: Movie) {
        _uiState.update { state ->
            state.copy(
                movie = item,
            )
        }
        viewModelScope.launch {
            getGenres()
            getMovieTrailer()
        }
    }

    private suspend fun getGenres() {
        uiState.value.movie?.let { movie ->
            genreRepository.getGenres(movie.genreIds).collectLatest { genres ->
                if (genres.isEmpty()) {
                    _uiEvent.emit(
                        DetailViewEvent.Error(
                            errorMessage = ErrorMessage.GET_GENRES_ERROR_MESSAGE,
                        ),
                    )
                } else {
                    _uiState.update { state ->
                        state.copy(
                            genres = genres,
                        )
                    }
                }
            }
        }
    }

    private suspend fun getMovieTrailer() {
        uiState.value.movie?.let { movie ->
            youtubeRepository.getTrailers(movie.title ?: "").collectLatest { videoId ->
                if (videoId.isNotEmpty()) {
                    _uiState.update { state ->
                        state.copy(
                            videoId = videoId,
                        )
                    }
                } else {
                    _uiEvent.emit(
                        DetailViewEvent.Error(
                            errorMessage = ErrorMessage.GET_YOUTUBE_VIDEO_ERROR_MESSAGE,
                        ),
                    )
                }
            }
        }
    }
}
