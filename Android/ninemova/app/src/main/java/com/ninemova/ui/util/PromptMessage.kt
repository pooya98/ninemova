package com.ninemova.ui.util

import com.ninemova.ui.recommend.RecommendViewModel


object PromptMessage {

    fun promptUserTag(movieNames: String): String {
        return """
            Question: [${movieNames}] 이 영화들을 모두 본 사용자에게 붙여줄 수 있는 칭호 3가지를 알려주세요.
            
            다음의 JSON 형식에 맞춰서 응답해주세요.
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 칭호이름, "rate": 0.0})으로 칭호 3개]}
            }
        """.trimIndent()
    }


    fun promptActor(movieNames: String): String {
        return """
            Question: [${movieNames}] 이 영화들과 공통적으로 관련있는 배우 3명를 관련 지수와 함께 알려주세요.
            
            다음의 JSON 형식에 맞춰서 응답해주세요.
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 배우이름, "rate": 관련 지수(0.0~100.0)})으로 배우 3명]}
            }
        """.trimIndent()
    }

    fun promptKeyWords(movieNames: String): String {
        return """
            Question: [${movieNames}] 이 영화들에서 뽑아낼 수 있는 주제 3개를 관련 지수와 함께 알려주세요.
            
            다음의 JSON 형식에 맞춰서 응답해주세요.
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 주제 이름, "rate": 관련 지수(0.0~100.0)})으로 주제 3개]}
            }
        """.trimIndent()
    }

    fun promptGenres(movieNames: String): String {
        return """
            Question: [${movieNames}] 이 영화들과 공통적으로 관련있는 장르 3개를 관련 지수와 함께 알려주세요.
            
            다음의 JSON 형식에 맞춰서 응답해주세요.
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 장르 이름, "rate": 관련 지수(0.0~100.0)})으로 장르 3개]}
            }
        """.trimIndent()
    }

    fun promptAiMovieRecommends(movieNames: String): String {

        return """
            Question: TMDB에 있는 영화 중에서 다음의 리스트에 있는 영화들과 비슷한 주제의 영화 1개의 제목을 반환해주세요. 영화 리스트 : [${movieNames}]
        
            다음의 JSON 형식에 맞춰서 응답해주세요.
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 영화 이름, "rate": 관련 지수(0.0~100.0)})]}
            }
        """.trimIndent()
    }

    fun promptNewMovieRecommends(movieNames: String): String {

        return """
            Question: TMDB에 있는 영화 중에서 다음의 리스트에 있는 영화들과는 다른 주제의 영화 1개의 제목을 반환해주세요. 영화 리스트 : [${movieNames}]
        
            다음의 JSON 형식에 맞춰서 응답해주세요.
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 영화 이름, "rate": 관련 지수(0.0~100.0)})]}
            }
        """.trimIndent()
    }
}
