package com.ninemova.ui.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ninemova.BuildConfig
import com.ninemova.Network.request.tmdb.SearchMovieRequest
import com.ninemova.Network.response.openai.AnalysisResult
import com.ninemova.Network.utils.RepositoryUtils
import com.ninemova.ui.util.PromptMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecommendViewModel : ViewModel() {

    private val repository = RepositoryUtils.openAiRepository
    private val movieRepository = RepositoryUtils.movieRepository
    private val favoriteRepository = RepositoryUtils.favoriteRepository
    private val localDataStoreRepository = RepositoryUtils.localDataStoreRepository

    private val _uiState = MutableStateFlow(RecommendUiState())
    val uiState: StateFlow<RecommendUiState> = _uiState
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _uiEvent = MutableSharedFlow<RecommendViewEvent>()
    val uiEvent: SharedFlow<RecommendViewEvent> = _uiEvent

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> get() = _response

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            getFavoriteMovies()
            getRecommendMovieTitle()
        }
    }

    private suspend fun getFavoriteMovies() {
        favoriteRepository.getUserFavoriteMovies(localDataStoreRepository.getUserId())
            .collectLatest { movieNames ->
                val favoriteMovieNames = if (movieNames.isNullOrEmpty()) {
                    "악인전, 내부자들, 범죄도시, 베테랑, 범죄와의 전쟁, 히트맨, 조작된 도시, 부산행, 스파이더웹"
                } else {
                    movieNames.joinToString(separator = ", ")
                }
                _uiState.update { uiState ->
                    uiState.copy(
                        favoriteMovies = favoriteMovieNames
                    )
                }
            }
    }

    private suspend fun getRecommendMovieTitle() {
        getAiMovieTitle()
        getNewMovieTitle()
    }

    suspend fun getAiMovieTitle() {
        repository.getChatResponse(
            PromptMessage.promptAiMovieRecommends(uiState.value.favoriteMovies),
            BuildConfig.OPENAI_API_KEY
        )
            ?.let { response ->
                try {
                    val chatGptResult = Gson().fromJson(response, AnalysisResult::class.java)
                    val aiMovieTitle = chatGptResult.answer.resultElements.first()
                    _uiState.update { uiState ->
                        uiState.copy(
                            aiRecommendMovieTitle = aiMovieTitle.name,
                        )
                    }
                    _uiEvent.emit(RecommendViewEvent.ChatGptSuccess(flagCode = 1))
                } catch (e: Exception) {
                    _uiEvent.emit(
                        RecommendViewEvent.ChatGptError(
                            errorCode = 1
                        )
                    )
                }
            } ?: run {
            _uiEvent.emit(
                RecommendViewEvent.ChatGptError(
                    errorCode = 1
                )
            )
        }
    }

    suspend fun getNewMovieTitle() {
        repository.getChatResponse(
            PromptMessage.promptNewMovieRecommends(uiState.value.favoriteMovies),
            BuildConfig.OPENAI_API_KEY
        )
            ?.let { response ->
                try {
                    val chatGptResult = Gson().fromJson(response, AnalysisResult::class.java)
                    val newMovieTitle = chatGptResult.answer.resultElements.first()
                    _uiState.update { uiState ->
                        uiState.copy(
                            newWorldMovieTitle = newMovieTitle.name,
                        )
                    }
                    _uiEvent.emit(RecommendViewEvent.ChatGptSuccess(flagCode = 2))
                } catch (e: Exception) {
                    _uiEvent.emit(
                        RecommendViewEvent.ChatGptError(
                            errorCode = 2
                        )
                    )
                }
            } ?: run {
            _uiEvent.emit(
                RecommendViewEvent.ChatGptError(
                    errorCode = 2
                )
            )
        }
    }

    fun fetchAiRecommendMovie() {
        viewModelScope.launch {
            movieRepository.searchMovies(
                request = SearchMovieRequest(
                    query = uiState.value.aiRecommendMovieTitle,
                ),
            ).collectLatest { response ->
                if (response.isEmpty()) {
                    _uiEvent.emit(
                        RecommendViewEvent.TmdbApiError(
                            errorCode = 1
                        )
                    )
                } else {

                    _uiState.update { state ->
                        if (state.selectedTab == 1) {
                            state.copy(
                                selectedMovie = response[0],
                                aiRecommendMovie = response[0],
                            )
                        } else {
                            state.copy(
                                aiRecommendMovie = response[0],
                            )
                        }
                    }
                }
            }
            _uiEvent.emit(RecommendViewEvent.TmdbApiSuccess(flagCode = 1))
        }
    }

    fun fetchNewWorldRecommendMovie() {
        viewModelScope.launch {
            movieRepository.searchMovies(
                request = SearchMovieRequest(
                    query = uiState.value.newWorldMovieTitle,
                ),
            ).collectLatest { response ->
                if (response.isEmpty()) {
                    _uiEvent.emit(
                        RecommendViewEvent.TmdbApiError(
                            errorCode = 1
                        )
                    )
                } else {
                    _uiState.update { state ->
                        if (state.selectedTab == 2) {
                            state.copy(
                                selectedMovie = response[0],
                                newWorldMovie = response[0],
                            )
                        } else {
                            state.copy(
                                newWorldMovie = response[0],
                            )
                        }
                    }
                }
            }
            _uiEvent.emit(RecommendViewEvent.TmdbApiSuccess(flagCode = 2))
        }
    }

    fun selectTab(tabID: Int) {
        if (uiState.value.selectedTab != tabID) {
            if (tabID == 1) {
                _uiState.update { state ->
                    state.copy(
                        selectedTab = tabID,
                        selectedMovie = uiState.value.aiRecommendMovie,
                    )
                }
            } else {
                _uiState.update { state ->
                    state.copy(
                        selectedTab = tabID,
                        selectedMovie = uiState.value.newWorldMovie,
                    )
                }
            }
        }
    }

    fun setLoadingOn() {
        _isLoading.update {
            true
        }
    }

    fun setLoadingOff() {
        _isLoading.update {
            false
        }
    }
}
