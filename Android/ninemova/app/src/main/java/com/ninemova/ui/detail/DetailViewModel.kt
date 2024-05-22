package com.ninemova.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninemova.Network.utils.RepositoryUtils
import com.ninemova.Network.request.server.FavoriteRequest
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
    private val favoriteRepository = RepositoryUtils.favoriteRepository
    private val localDataStoreRepository = RepositoryUtils.localDataStoreRepository

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
            getFavorite()
            getGenres()
            getMovieTrailer()
        }
    }

    private suspend fun getGenres() {
        genreRepository.getGenres(uiState.value.movie.genreIds).collectLatest { genres ->
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

    private suspend fun getMovieTrailer() {
        youtubeRepository.getTrailers(uiState.value.movie.title ?: "").collectLatest { videoId ->
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

    private suspend fun getFavorite() {
        favoriteRepository.getFavorite(
            localDataStoreRepository.getUserId(),
            uiState.value.movie.id ?: 0
        ).collectLatest { result ->
            _uiState.update { uiState ->
                uiState.copy(
                    isLiked = result,
                )
            }
        }
    }

    fun setIsLiked() {
        _uiState.update { uiState ->
            uiState.copy(
                isLiked = !uiState.isLiked
            )
        }
    }

    fun handleFavorite() {
        viewModelScope.launch {
            if (uiState.value.isLiked) {
                favoriteRepository.createFavorite(
                    FavoriteRequest(
                        localDataStoreRepository.getUserId(),
                        uiState.value.movie.id ?: 0,
                        uiState.value.movie.title ?: ""
                    )
                ).collectLatest { response ->
                    if (response == null) {
                        _uiState.update { uiState ->
                            uiState.copy(
                                isLiked = false,
                            )
                        }
                        _uiEvent.emit(
                            DetailViewEvent.Error(
                                errorMessage = ErrorMessage.CLICK_LIKED_ERROR_MESSAGE,
                            )
                        )
                    }
                }
            } else {
                favoriteRepository.deleteFavorite(
                    localDataStoreRepository.getUserId(),
                    uiState.value.movie.id ?: 0
                ).collectLatest { result ->
                    if (result.not()) {
                        _uiEvent.emit(DetailViewEvent.Error("실패"))
                    }
                }
            }
        }
    }
}
