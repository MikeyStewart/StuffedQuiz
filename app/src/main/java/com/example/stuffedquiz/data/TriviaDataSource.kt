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
}