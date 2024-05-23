package com.ninemova.ui.community

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ninemova.Network.utils.RepositoryUtils
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

    fun loadComments(scope: CoroutineScope) {
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
