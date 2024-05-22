package com.ninemova.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninemova.Network.utils.RepositoryUtils
import com.ninemova.Network.request.tmdb.SearchNowPlayingMoviesRequest
import com.ninemova.Network.request.tmdb.SearchPopularMoviesRequest
import com.ninemova.ui.util.ErrorMessage
import com.ninemova.ui.util.runTickerFlow
import kotlinx.coroutines.CoroutineScope
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
    private val commentRepository = RepositoryUtils.commentRepository

    init {
        searchNowPlayingMovies()
        searchPopularMovies()
        runTickerFlow(
            interval = 1000L,
            scope = viewModelScope,
            action = { searchRecentComments(viewModelScope) }
        )
    }

    private fun searchNowPlayingMovies() {
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

    private fun searchPopularMovies() {
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

    private fun searchRecentComments(scope: CoroutineScope) {
        scope.launch {
            commentRepository.getRecentComments().collectLatest { response ->
                _uiState.update { state ->
                    state.copy(
                        recentComments = response,
                    )
                }
            }
        }
    }
}
