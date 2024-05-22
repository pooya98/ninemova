package com.ninemova.ui.util


object PromptMessage {

    fun promptUserTag(movieNames: String): String {
        val prompt_userTag = """
            Question: [${movieNames}] 이 영화들을 모두 본 사용자에게 붙여줄 수 있는 칭호 3가지를 알려주세요.
            
            다음의 JSON 형식에 맞춰서 응답해주세요.
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 칭호이름, "rate": 0.0})으로 칭호 3개]}
            }
        """.trimIndent()
        return prompt_userTag
    }


    fun promptActor(movieNames: String): String {
        val prompt_actor = """
            Question: [${movieNames}] 이 영화들과 공통적으로 관련있는 배우 3명를 관련 지수와 함께 알려주세요.
            
            다음의 JSON 형식에 맞춰서 응답해주세요.
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 배우이름, "rate": 관련 지수(0.0~100.0)})으로 배우 3명]}
            }
        """.trimIndent()
        return prompt_actor
    }

    fun promptKeyWords(movieNames: String): String {
        val prompt_keyword = """
            Question: [${movieNames}] 이 영화들에서 뽑아낼 수 있는 주제 3개를 관련 지수와 함께 알려주세요.
            
            다음의 JSON 형식에 맞춰서 응답해주세요.
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 주제 이름, "rate": 관련 지수(0.0~100.0)})으로 주제 3개]}
            }
        """.trimIndent()
        return prompt_keyword
    }

    fun promptGenres(movieNames: String): String {
        val prompt_genre = """
            Question: [${movieNames}] 이 영화들과 공통적으로 관련있는 장르 3개를 관련 지수와 함께 알려주세요.
            
            다음의 JSON 형식에 맞춰서 응답해주세요.
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 장르 이름, "rate": 관련 지수(0.0~100.0)})으로 장르 3개]}
            }
        """.trimIndent()
        return prompt_genre
    }
}