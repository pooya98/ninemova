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
import com.ninemova.ui.util.ErrorMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "RecommendViewModel_싸피"

class RecommendViewModel : ViewModel() {
    private val repository = RepositoryUtils.openAiRepository
    private val movieRepository = RepositoryUtils.movieRepository
    private val genreRepository = RepositoryUtils.genreRepository

    private val _uiState = MutableStateFlow(RecommendUiState())
    val uiState: StateFlow<RecommendUiState> = _uiState
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _uiEvent = MutableSharedFlow<RecommendViewEvent>()
    val uiEvent: SharedFlow<RecommendViewEvent> = _uiEvent

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> get() = _response

    init {
        getRecommendMovieTitle()
    }

    private fun getRecommendMovieTitle() {
        viewModelScope.launch {
            getAiMovieTitle()
            getNewMovieTitle()
            _uiEvent.emit(RecommendViewEvent.SearchSuccess)
        }
    }

    private suspend fun getAiMovieTitle() {
        repository.getChatResponse(promptAiMovieRecommends, BuildConfig.OPENAI_API_KEY)
            ?.let { response ->
                val chatGptResult = Gson().fromJson(response, AnalysisResult::class.java)
                val aiMovieTitle = chatGptResult.answer.resultElements.first()
                _uiState.update { uiState ->
                    uiState.copy(
                        aiRecommendMovieTitle = aiMovieTitle.name,
                    )
                }
            } ?: run {
            _uiEvent.emit(RecommendViewEvent.Error(errorMessage = "AI 영화 추천 응답 실패"))
        }
    }

    private suspend fun getNewMovieTitle() {
        repository.getChatResponse(promptAiNewWorldRecommends, BuildConfig.OPENAI_API_KEY)
            ?.let { response ->
                val chatGptResult = Gson().fromJson(response, AnalysisResult::class.java)
                val newMovieTitle = chatGptResult.answer.resultElements.first()

                _uiState.update { uiState ->
                    uiState.copy(
                        newWorldMovieTitle = newMovieTitle.name,
                    )
                }
            } ?: run {
            _uiEvent.emit(RecommendViewEvent.Error(errorMessage = "새로운 영화 추천 응답 실패"))
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
                    _uiEvent.emit(RecommendViewEvent.Error(errorMessage = ErrorMessage.NO_SEARCH_RESULT_ERROR_MESSAGE))
                } else {
                    _uiState.update { state ->
                        state.copy(
                            selectedMovie = response[0],
                        )
                    }
                    _isLoading.update {
                        false
                    }
                }
            }
            getGenres()
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
                    _uiEvent.emit(RecommendViewEvent.Error(errorMessage = ErrorMessage.NO_SEARCH_RESULT_ERROR_MESSAGE))
                } else {
                    _uiState.update { state ->
                        state.copy(
                            selectedMovie = response[0],
                        )
                    }
                    _isLoading.update {
                        false
                    }
                }
            }
            getGenres()
        }
    }

    private suspend fun getGenres() {
        uiState.value.selectedMovie?.let { movie ->
            genreRepository.getGenres(movie.genreIds).collectLatest { genres ->
                if (genres.isEmpty()) {
                    _uiEvent.emit(
                        RecommendViewEvent.Error(
                            errorMessage = ErrorMessage.GET_GENRES_ERROR_MESSAGE,
                        ),
                    )
                } else {
                    _uiState.update { state ->
                        state.copy(
                            genres = genres,
                        )
                    }
                }
            }
        }
    }

    companion object {
        val movieListString = "악인전, 내부자들, 범죄도시, 베테랑, 범죄와의 전쟁, 히트맨, 조작된 도시, 부산행, 스파이더웹"

        val promptAiMovieRecommends = """
            Question: TMDB에 있는 영화 중에서 다음의 리스트에 있는 영화들과 비슷한 주제의 영화 1개의 제목을 반환해주세요. 영화 리스트 : [$movieListString]
        
            다음의 JSON 형식에 맞춰서 응답해주세요.
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 영화 이름, "rate": 관련 지수(0.0~100.0)})]}
            }
        """.trimIndent()

        val promptAiNewWorldRecommends = """
            Question: TMDB에 있는 영화 중에서 다음의 리스트에 있는 영화들과는 다른 주제의 영화 1개의 제목을 반환해주세요. 영화 리스트 : [$movieListString]
        
            다음의 JSON 형식에 맞춰서 응답해주세요.
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 영화 이름, "rate": 관련 지수(0.0~100.0)})]}
            }
        """.trimIndent()
    }
}
