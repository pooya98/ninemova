package com.ninemova.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninemova.Network.RepositoryUtils
import com.ninemova.domain.data.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val genreRepository = RepositoryUtils.genreRepository
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState

    fun setMovie(item: Movie) {
        _uiState.update { state ->
            state.copy(
                movie = item,
            )
        }
        viewModelScope.launch {
            genreRepository.getGenres(item.genreIds ?: listOf()).collectLatest { genres ->
                _uiState.update { state ->
                    state.copy(
                        genres = genres,
                    )
                }
            }
        }
    }
}
