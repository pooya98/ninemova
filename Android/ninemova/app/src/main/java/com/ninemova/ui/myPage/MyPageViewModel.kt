package com.ninemova.ui.myPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ninemova.BuildConfig
import com.ninemova.Network.response.openai.AnalysisResult
import com.ninemova.Network.utils.RepositoryUtils
import com.ninemova.domain.data.PieChartItem
import com.ninemova.domain.data.User
import com.ninemova.domain.data.UserTag
import com.ninemova.ui.util.ErrorMessage
import com.ninemova.ui.util.PromptMessage
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {

    private val repository = RepositoryUtils.openAiRepository
    private val localDataStoreRepository = RepositoryUtils.localDataStoreRepository
    private val favoriteRepository = RepositoryUtils.favoriteRepository

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> get() = _response

    private val _userTags = MutableLiveData<List<UserTag>>()
    val userTags: LiveData<List<UserTag>> get() = _userTags

    private val _keywords = MutableLiveData<List<PieChartItem>>()
    val keywords: LiveData<List<PieChartItem>> get() = _keywords

    private val _actors = MutableLiveData<List<PieChartItem>>()
    val actors: LiveData<List<PieChartItem>> get() = _actors

    private val _genres = MutableLiveData<List<PieChartItem>>()
    val genres: LiveData<List<PieChartItem>> get() = _genres

    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User> get() = _userInfo

    private val _favoriteMovies = MutableLiveData<String>()
    val favoriteMovies: LiveData<String> = _favoriteMovies

    private val _initFavoriteMovies = MutableLiveData<String>()
    val initFavoriteMovies: LiveData<String> = _initFavoriteMovies

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        fetchUserFavoriteMovies()
        fetchUserInfo()
    }

    fun setFavoriteMovies() {
        _initFavoriteMovies.value = _favoriteMovies.value
    }

    fun fetchUserFavoriteMovies() {
        viewModelScope.launch {
            favoriteRepository.getUserFavoriteMovies(localDataStoreRepository.getUserId())
                .collectLatest { movieNames ->
                    movieNames?.let { list ->
                        _favoriteMovies.value =
                            list.joinToString(separator = ", ")
                    }
                }
        }
    }

    fun fetchUserTagResponse() {
        viewModelScope.launch {
            try {
                val result = repository.getChatResponse(
                    PromptMessage.promptUserTag(_favoriteMovies.value ?: ""),
                    BuildConfig.OPENAI_API_KEY,
                ) ?: ""
                _response.value = result
                val analysisResult: AnalysisResult =
                    Gson().fromJson(result, AnalysisResult::class.java)
                val userTagItems = mutableListOf<UserTag>()
                analysisResult.answer.resultElements.forEach { element ->
                    userTagItems.add(UserTag(element.name))
                }
                _userTags.value = userTagItems
            } catch (e: Exception) {
                _errorMessage.value = ErrorMessage.CHATGPT_ERROR_MESSAGe
            }
        }
    }

    fun fetchKeywordResponse() {
        viewModelScope.launch {
            try {
                val result = repository.getChatResponse(
                    PromptMessage.promptKeyWords(
                        _favoriteMovies.value ?: "",
                    ),
                    BuildConfig.OPENAI_API_KEY,
                ) ?: ""
                _response.value = result
                val analysisResult: AnalysisResult =
                    Gson().fromJson(result, AnalysisResult::class.java)
                val pieChartItems = mutableListOf<PieChartItem>()
                analysisResult.answer.resultElements.forEachIndexed { index, element ->
                    pieChartItems.add(
                        PieChartItem(
                            element.name,
                            element.rate,
                            piechartColors[index],
                        ),
                    )
                }
                _keywords.value = pieChartItems
            } catch (e: Exception) {
                _errorMessage.value = ErrorMessage.CHATGPT_ERROR_MESSAGe
            }
        }
    }

    fun fetchActorResponse() {
        viewModelScope.launch {
            try {
                val result = repository.getChatResponse(
                    PromptMessage.promptActor(
                        _favoriteMovies.value ?: "",
                    ),
                    BuildConfig.OPENAI_API_KEY,
                ) ?: ""
                _response.value = result
                val analysisResult: AnalysisResult =
                    Gson().fromJson(response.value, AnalysisResult::class.java)
                val pieChartItems = mutableListOf<PieChartItem>()
                analysisResult.answer.resultElements.forEachIndexed { index, element ->
                    pieChartItems.add(
                        PieChartItem(
                            element.name,
                            element.rate,
                            piechartColors[index],
                        ),
                    )
                }
                _actors.value = pieChartItems
            } catch (e: Exception) {
                _errorMessage.value = ErrorMessage.CHATGPT_ERROR_MESSAGe
            }
        }
    }

    fun fetchGenreResponse() {
        viewModelScope.launch {
            try {
                val result = repository.getChatResponse(
                    PromptMessage.promptGenres(
                        _favoriteMovies.value ?: "",
                    ),
                    BuildConfig.OPENAI_API_KEY,
                ) ?: ""
                _response.value = result
                val analysisResult: AnalysisResult =
                    Gson().fromJson(result, AnalysisResult::class.java)
                val pieChartItems = mutableListOf<PieChartItem>()
                analysisResult.answer.resultElements.forEachIndexed { index, element ->
                    pieChartItems.add(
                        PieChartItem(
                            element.name,
                            element.rate,
                            piechartColors[index],
                        ),
                    )
                }
                _genres.value = pieChartItems
            } catch (e: Exception) {
                _errorMessage.value = ErrorMessage.CHATGPT_ERROR_MESSAGe
            }
        }
    }

    private fun fetchUserInfo() {
        viewModelScope.launch {
            val userId = localDataStoreRepository.getUserId()
            val userName = localDataStoreRepository.getUserName()
            val userNickName = localDataStoreRepository.getUserNickName()
            val userImageUri = localDataStoreRepository.getUserProfileImageUrl()
            _userInfo.value = User(userId, userName, userNickName, userImageUri)
        }
    }

    companion object {
        val piechartColors = listOf<String>("#14A104", "#0569B8", "#BF0E01")
    }
}
