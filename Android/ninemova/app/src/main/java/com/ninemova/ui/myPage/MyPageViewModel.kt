package com.ninemova.ui.myPage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ninemova.BuildConfig
import com.ninemova.Network.RepositoryUtils
import com.ninemova.Network.response.openai.AnalysisResult
import com.ninemova.domain.data.PieChartItem
import com.ninemova.domain.data.User
import com.ninemova.domain.data.UserTag
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

    private val favoriteMovies = MutableLiveData<String>()

    init {
        fetchUserFavoriteMovies()
    }

    private fun fetchUserFavoriteMovies() {
        viewModelScope.launch {
            favoriteRepository.getUserFavoriteMovies(localDataStoreRepository.getUserId())
                .collectLatest { movieNames ->
                    movieNames?.let { list ->
                        //PromptMessage.movieNames = list.joinToString(separator = ", ")
                        favoriteMovies.value = list.joinToString(separator = ", ")
                        fetchUserInfo()
                        fetchUserTagResponse()
                        fetchKeywordResponse()
                        fetchGenreResponse()
                        fetchActorResponse()
                    }
                }
        }
    }

    private fun fetchUserTagResponse() {
        viewModelScope.launch {
            try {
                val result = repository.getChatResponse(
                    PromptMessage.promptUserTag(favoriteMovies.value ?: ""),
                    BuildConfig.OPENAI_API_KEY
                )
                _response.value = result!!
                Log.d("jaehan","userTag response : ${result!!}")
                val analysisResult: AnalysisResult =
                    Gson().fromJson(result!!, AnalysisResult::class.java)
                val userTagItems = mutableListOf<UserTag>()
                analysisResult.answer.resultElements.forEachIndexed { index, element ->
                    userTagItems.add(UserTag(element.name))
                }
                _userTags.value = userTagItems

            } catch (e: Exception) {
                _response.value = "Error: ${e.message}"
            }
        }
    }

    private fun fetchKeywordResponse() {
        viewModelScope.launch {
            try {
                val result = repository.getChatResponse(
                    PromptMessage.promptKeyWords(
                        favoriteMovies.value ?: ""
                    ), BuildConfig.OPENAI_API_KEY
                )
                _response.value = result!!
                Log.d("jaehan","keyword response : ${result!!}")
                val analysisResult: AnalysisResult =
                    Gson().fromJson(result!!, AnalysisResult::class.java)
                val pieChartItems = mutableListOf<PieChartItem>()
                analysisResult.answer.resultElements.forEachIndexed { index, element ->
                    pieChartItems.add(
                        PieChartItem(
                            element.name,
                            element.rate,
                            piechartColors[index]
                        )
                    )
                }
                _keywords.value = pieChartItems

            } catch (e: Exception) {
                _response.value = "Error: ${e.message}"
            }
        }
    }

    private fun fetchActorResponse() {
        viewModelScope.launch {
            try {
                val result = repository.getChatResponse(
                    PromptMessage.promptActor(
                        favoriteMovies.value ?: ""
                    ), BuildConfig.OPENAI_API_KEY
                )
                _response.value = result!!.substring(8, result!!.length - 4)

                Log.d("jaehan","actor response : ${result!!}")
                val analysisResult: AnalysisResult =
                    Gson().fromJson(response.value, AnalysisResult::class.java)
                val pieChartItems = mutableListOf<PieChartItem>()
                analysisResult.answer.resultElements.forEachIndexed { index, element ->
                    pieChartItems.add(
                        PieChartItem(
                            element.name,
                            element.rate,
                            piechartColors[index]
                        )
                    )
                }
                _actors.value = pieChartItems

            } catch (e: Exception) {
                _response.value = "Error: ${e.message}"
            }
        }
    }

    private fun fetchGenreResponse() {
        viewModelScope.launch {
            try {
                val result = repository.getChatResponse(
                    PromptMessage.promptGenres(
                        favoriteMovies.value ?: ""
                    ), BuildConfig.OPENAI_API_KEY
                )
                _response.value = result!!
                Log.d("jaehan","genre response : ${result!!}")
                val analysisResult: AnalysisResult =
                    Gson().fromJson(result!!, AnalysisResult::class.java)
                val pieChartItems = mutableListOf<PieChartItem>()
                analysisResult.answer.resultElements.forEachIndexed { index, element ->
                    pieChartItems.add(
                        PieChartItem(
                            element.name,
                            element.rate,
                            piechartColors[index]
                        )
                    )
                }
                _genres.value = pieChartItems

            } catch (e: Exception) {
                _response.value = "Error: ${e.message}"
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
