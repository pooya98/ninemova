package com.ninemova.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninemova.Network.RepositoryUtils
import com.ninemova.Network.request.ReplyRequest
import com.ninemova.domain.data.Comment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val replyRepository = RepositoryUtils.replyRepository
    private val localDataStoreRepository = RepositoryUtils.localDataStoreRepository
    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState

    fun setComment(comment: Comment) {
        _uiState.update { uiState ->
            uiState.copy(
                comment = comment,
            )
        }
        loadReplies()
    }

    private fun loadReplies() {
        viewModelScope.launch {
            replyRepository.getReplies(uiState.value.comment.id ?: -1).collectLatest { replies ->
                _uiState.update { uiState ->
                    uiState.copy(
                        replies = replies,
                    )
                }
            }
        }
    }

    fun registerReply() {
        viewModelScope.launch {
            replyRepository.createReply(
                ReplyRequest(
                    commentId = uiState.value.comment.id ?: 0,
                    userId = localDataStoreRepository.getUserId(),
                    content = uiState.value.sendContent ?: "",
                ),
            ).collectLatest { reply ->
                if (reply == null) {
                } else {
                    loadReplies()
                }
            }
        }
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        _uiState.update { uiState ->
            uiState.copy(
                sendContent = s.toString(),
            )
        }
    }
}
