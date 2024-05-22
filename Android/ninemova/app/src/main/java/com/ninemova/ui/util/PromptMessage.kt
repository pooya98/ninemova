package com.ninemova.ui.util


object PromptMessage {

    fun promptUserTag(movieNames: String): String {
        val prompt_userTag = """
            Please provide the information in the following JSON format:
            
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 칭호이름, "rate": 0.0})으로 칭호 3개]}
            }
            
            Question: [${movieNames}] 이 영화들을 모두 본 사용자에게 붙여줄 수 있는 칭호 3가지를 지어서 한글로 알려주세요.
        """.trimIndent()
        return prompt_userTag
    }


    fun promptActor(movieNames: String): String {
        val prompt_actor = """

              Please provide the information in the following JSON format:
            
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 배우이름, "rate": 연관성지수(%)})으로 배우 3명]}
            }
            
            Question: [${movieNames}] 이 영화들과 공통적으로 관련있는 배우 3명을 알려주세요.
        """.trimIndent()
        return prompt_actor
    }

    fun promptKeyWords(movieNames: String): String {
        val prompt_keyword = """
            Please provide the information in the following JSON format:
            
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 키워드이름, "rate": 연관성지수(%)})으로 키워드(또는 주제) 3개]}
            }
            
            Question: [${movieNames}] 이 영화들에서 뽑아낼 수 있는 키워드(또는 주제) 3개를 알려주세요.
        """.trimIndent()
        return prompt_keyword
    }

    fun promptGenres(movieNames: String): String {
        val prompt_genre = """
            Please provide the information in the following JSON format:
            
            {
              "question": "Your question here",
              "answer": {"resultElements": [JSON 형식({"name": 장르이름, "rate": 연관성지수(%)})으로 장르 3개]}
            }
            
            Question: [${movieNames}] 이 영화들과 공통적으로 관련있는 장르 3개를 알려주세요.
        """.trimIndent()
        return prompt_genre
    }
}