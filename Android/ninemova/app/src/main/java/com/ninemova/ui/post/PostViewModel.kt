package com.ninemova.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninemova.Network.request.server.ReplyRequest
import com.ninemova.Network.utils.RepositoryUtils
import com.ninemova.domain.data.Comment
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

class PostViewModel : ViewModel() {

    private val replyRepository = RepositoryUtils.replyRepository
    private val localDataStoreRepository = RepositoryUtils.localDataStoreRepository
    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState
    private val _uiEvent = MutableSharedFlow<PostViewEvent>()
    val uiEvent: SharedFlow<PostViewEvent> = _uiEvent

    fun setComment(comment: Comment) {
        _uiState.update { uiState ->
            uiState.copy(
                comment = comment,
            )
        }
        runTickerFlow(
            interval = 1000L,
            scope = viewModelScope,
            action = { loadReplies(viewModelScope) },
        )
    }

    private fun loadReplies(scope: CoroutineScope) {
        scope.launch {
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
                    _uiEvent.emit(
                        PostViewEvent.Error(
                            errorMessage = ErrorMessage.REGISTER_COMMENT_ERROR_MESSAGE,
                        ),
                    )
                } else {
                    _uiEvent.emit(PostViewEvent.Success)
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
