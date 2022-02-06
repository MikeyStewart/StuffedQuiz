package com.example.stuffedquiz.data

class TriviaDataSource(
    private val service: TriviaApiService
) {

    suspend fun getSessionToken(): SessionTokenResponse {
        return service.getSessionToken()
    }

    suspend fun getCategories(): CategoriesResponse {
        return service.getCategories()
    }

    suspend fun getQuestions(
        amount: String,
        category: String,
        difficulty: String
    ): QuestionsResponse {
        return service.getQuestions(amount, category, difficulty)
    }
}