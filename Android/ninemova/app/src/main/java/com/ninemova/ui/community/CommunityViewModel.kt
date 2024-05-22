package com.ninemova.ui.community

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninemova.Network.utils.RepositoryUtils
import com.ninemova.ui.util.runTickerFlow
import kotlinx.coroutines.CoroutineScope
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
        runTickerFlow(
            interval = 1000L,
            scope = viewModelScope,
            action = { loadComments(viewModelScope) },
        )
    }

    private fun loadComments(scope: CoroutineScope) {
        scope.launch {
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
