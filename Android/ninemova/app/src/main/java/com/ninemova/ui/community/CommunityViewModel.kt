package com.ninemova.ui.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninemova.Network.RepositoryUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CommunityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CommunityUiState())
    val uiState: StateFlow<CommunityUiState> = _uiState
    private val commentRepository = RepositoryUtils.commentRepository

    init {
        loadComments()
    }

    private fun loadComments() {
        viewModelScope.launch {
            commentRepository.getComments().collectLatest { comments ->
                _uiState.update { uiState ->
                    uiState.copy(
                        comments = comments,
                    )
                }
            }
        }
    }
}
