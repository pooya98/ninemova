package com.ninemova.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninemova.Network.RepositoryUtils
import com.ninemova.Network.request.SearchMovieRequest
import com.ninemova.Network.request.SearchNowPlayingMoviesRequest
import com.ninemova.Network.request.SearchPopularMoviesRequest
import com.ninemova.ui.util.ErrorMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _uiEvent = MutableSharedFlow<HomeViewEvent>()
    val uiEvent: SharedFlow<HomeViewEvent> = _uiEvent
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    private val movieRepository = RepositoryUtils.movieRepository

    fun searchNowPlayingMovies() {
        viewModelScope.launch {
            movieRepository.searchNowPlayingMovies(
                request = SearchNowPlayingMoviesRequest(),
            ).collectLatest { response ->
                if (response.isEmpty()) {
                    _uiEvent.emit(HomeViewEvent.Error(errorMessage = ErrorMessage.NO_SEARCH_RESULT_ERROR_MESSAGE))
                } else {
                    _uiState.update { state ->
                        state.copy(
                            nowPlayingMovies = response,
                        )
                    }
                }
            }
        }
    }

    fun searchPopularMovies() {
        viewModelScope.launch {
            movieRepository.searchPopularMovies(
                request = SearchPopularMoviesRequest(),
            ).collectLatest { response ->
                if (response.isEmpty()) {
                    _uiEvent.emit(HomeViewEvent.Error(errorMessage = ErrorMessage.NO_SEARCH_RESULT_ERROR_MESSAGE))
                } else {
                    _uiState.update { state ->
                        state.copy(
                            popularMovies = response,
                        )
                    }
                }
            }
        }
    }
}
