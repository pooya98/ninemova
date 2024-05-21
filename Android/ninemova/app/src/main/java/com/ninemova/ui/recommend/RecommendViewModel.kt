package com.ninemova.ui.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ninemova.BuildConfig
import com.ninemova.Network.RepositoryUtils
import com.ninemova.Network.request.SearchMovieRequest
import com.ninemova.Network.response.openai.AnalysisResult
import com.ninemova.ui.util.ErrorMessage
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
    private val genreRepository = RepositoryUtils.genreRepository

    private val _uiState = MutableStateFlow(RecommendUiState())
    val uiState: StateFlow<RecommendUiState> = _uiState

    private val _uiEvent = MutableSharedFlow<RecommendViewEvent>()
    val uiEvent: SharedFlow<RecommendViewEvent> = _uiEvent

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> get() = _response

    fun fetchAiRecommendMovie() {
        viewModelScope.launch {
            val movieTitle = getAiRecommendMovieTitle()

            movieRepository.searchMovies(
                request = SearchMovieRequest(
                    query = movieTitle,
                ),
            ).collectLatest { response ->
                if (response.isEmpty()) {
                    _uiEvent.emit(RecommendViewEvent.Error(errorMessage = ErrorMessage.NO_SEARCH_RESULT_ERROR_MESSAGE))
                } else {
                    _uiState.update { state ->
                        state.copy(
                            aiRecommendMovie = response[0],
                            selectedMovie = response[0]
                        )
                    }
                }
            }
            getGenres()
        }
    }

    private suspend fun getAiRecommendMovieTitle(): String {
        try {
            val result = repository.getChatResponse(promptAiMovieRecommends, BuildConfig.OPENAI_API_KEY)
            _response.value = result!!

            val analysisResult: AnalysisResult =
                Gson().fromJson(result!!, AnalysisResult::class.java)
            val movieTitles = mutableListOf<String>()
            analysisResult.answer.resultElements.forEachIndexed { index, element ->
                movieTitles.add(element.name)
            }
            return movieTitles[0]
        } catch (e: Exception) {
            _response.value = "Error: ${e.message}"
        }
        return ""
    }

    fun fetchNewWorldRecommendMovie(){
        viewModelScope.launch {
            val movieTitle = getNewWorldMovieTitle()

            movieRepository.searchMovies(
                request = SearchMovieRequest(
                    query = movieTitle,
                ),
            ).collectLatest { response ->
                if (response.isEmpty()) {
                    _uiEvent.emit(RecommendViewEvent.Error(errorMessage = ErrorMessage.NO_SEARCH_RESULT_ERROR_MESSAGE))
                } else {
                    _uiState.update { state ->
                        state.copy(
                            newWorldRecommendMovie = response[0],
                            selectedMovie = response[0]
                        )
                    }
                }
            }
            getGenres()
        }
    }

    private suspend fun getNewWorldMovieTitle(): String {
        try {
            val result = repository.getChatResponse(promptAiNewWorldRecommends, BuildConfig.OPENAI_API_KEY)
            _response.value = result!!

            val analysisResult: AnalysisResult =
                Gson().fromJson(result!!, AnalysisResult::class.java)
            val movieTitles = mutableListOf<String>()
            analysisResult.answer.resultElements.forEachIndexed { index, element ->
                movieTitles.add(element.name)
            }
            return movieTitles[0]
        } catch (e: Exception) {
            _response.value = "Error: ${e.message}"
        }
        return ""
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
            Please provide the information in the following JSON format:
            
            {
              "question": "Your question here",
              "answer": {"resultElements": [{"name": "", "rate": 0.0}]}
            }
            
            Question: TMDB에 있는 영화 중에서 다음의 리스트에 있는 영화들과 비슷한 주제의 영화 1개의 제목을 "name"부분에 담아서 반환해주세요. 영화 리스트 : [${movieListString}]
        """.trimIndent()

        val promptAiNewWorldRecommends = """
            Please provide the information in the following JSON format:
            
            {
              "question": "Your question here",
              "answer": {"resultElements": [{"name": "", "rate": 0.0}]}
            }
            
            Question: TMDB에 있는 영화 중에서 다음의 리스트에 있는 영화들과는 다른 주제의 영화 1개의 제목을 "name"부분에 담아서 반환해주세요. 영화 리스트 : [${movieListString}]
        """.trimIndent()
    }
}
