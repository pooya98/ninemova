package com.ninemova.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninemova.Network.utils.RepositoryUtils
import com.ninemova.Network.request.tmdb.SearchMovieRequest
import com.ninemova.Network.request.tmdb.SearchPopularMoviesRequest
import com.ninemova.ui.util.ErrorMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _uiEvent = MutableSharedFlow<SearchViewEvent>()
    val uiEvent: SharedFlow<SearchViewEvent> = _uiEvent
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    private val movieRepository = RepositoryUtils.movieRepository

    init{
        getInitMovies()
    }
    fun searchMovies() {
        viewModelScope.launch {
            movieRepository.searchMovies(
                request = SearchMovieRequest(
                    query = uiState.value.query,
                ),
            ).collectLatest { response ->
                if (response.isEmpty()) {
                    _uiEvent.emit(SearchViewEvent.Error(errorMessage = ErrorMessage.NO_SEARCH_RESULT_ERROR_MESSAGE))
                } else {
                    _uiState.update { state ->
                        state.copy(
                            movies = response,
                        )
                    }
                }
            }
        }
    }

    private fun getInitMovies(){
        viewModelScope.launch {
            movieRepository.searchPopularMovies(
                request = SearchPopularMoviesRequest(),
            ).collectLatest { response ->
                if (response.isEmpty()) {
                    _uiEvent.emit(SearchViewEvent.Error(errorMessage = ErrorMessage.NO_SEARCH_RESULT_ERROR_MESSAGE))
                } else {
                    _uiState.update { state ->
                        state.copy(
                            movies = response,
                        )
                    }
                }
            }
        }
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        _uiState.update { uiState ->
            uiState.copy(
                query = s.toString(),
            )
        }
    }
}
