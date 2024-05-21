package com.ninemova.ui.myPage

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
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {
    private val repository = RepositoryUtils.openAiRepository
    private val localRepository = RepositoryUtils.localDataStoreRepository

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

    init {
        fetchUserInfo()
        fetchUserTagResponse()
        fetchKeywordResponse()
        fetchGenreResponse()
        fetchActorResponse()
    }

    private fun fetchUserTagResponse() {
        viewModelScope.launch {
            try {
                val result = repository.getChatResponse(prompt_userTag, BuildConfig.OPENAI_API_KEY)
                _response.value = result!!

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
                val result = repository.getChatResponse(prompt_keyword, BuildConfig.OPENAI_API_KEY)
                _response.value = result!!

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
                val result = repository.getChatResponse(prompt_actor, BuildConfig.OPENAI_API_KEY)
                _response.value = result!!

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
                _actors.value = pieChartItems

            } catch (e: Exception) {
                _response.value = "Error: ${e.message}"
            }
        }
    }

    private fun fetchGenreResponse() {
        viewModelScope.launch {
            try {
                val result = repository.getChatResponse(prompt_genre, BuildConfig.OPENAI_API_KEY)
                _response.value = result!!

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
            val userId = localRepository.getUserId()
            val userName = localRepository.getUserName()
            val userNickName = localRepository.getUserNickName()
            val userImageUri = localRepository.getUserProfileImageUrl()

            _userInfo.value = User(userId, userName, userNickName, userImageUri)
        }
    }

    companion object {
        val piechartColors = listOf<String>("#14A104", "#0569B8", "#BF0E01")
        val movieListString = "악인전, 내부자들, 범죄도시, 베테랑, 범죄와의 전쟁, 히트맨, 조작된 도시, 부산행, 스파이더웹"

        val prompt_userTag = """
            Please provide the information in the following JSON format:
            
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 칭호이름, "rate": 0.0})으로 칭호 3개]}
            }
            
            Question: [${movieListString}] 이 영화들을 모두 본 사용자에게 붙여줄 수 있는 칭호 3가지를 지어서 알려주세요.
        """.trimIndent()

        val prompt_actor = """
            Please provide the information in the following JSON format:
            
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 배우이름, "rate": 연관성지수(%)})으로 배우 3명]}
            }
            
            Question: [${movieListString}] 이 영화들과 공통적으로 관련있는 배우 3명을 알려주세요.
        """.trimIndent()

        val prompt_keyword = """
            Please provide the information in the following JSON format:
            
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 키워드이름, "rate": 연관성지수(%)})으로 키워드(또는 주제) 3개]}
            }
            
            Question: [${movieListString}] 이 영화들에서 뽑아낼 수 있는 키워드(또는 주제) 3개를 알려주세요.
        """.trimIndent()

        val prompt_genre = """
            Please provide the information in the following JSON format:
            
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 장르이름, "rate": 연관성지수(%)})으로 장르 3개]}
            }
            
            Question: [${movieListString}] 이 영화들과 공통적으로 관련있는 장르 3개를 알려주세요.
        """.trimIndent()
    }
}
