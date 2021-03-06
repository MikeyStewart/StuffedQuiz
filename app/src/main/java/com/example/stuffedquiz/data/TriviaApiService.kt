package com.example.stuffedquiz.data

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * A Retrofit service for the Open Trivia API (https://opentdb.com/)
 */
interface TriviaApiService {

    @GET("api_token.php?command=request")
    suspend fun getSessionToken(): SessionTokenResponse

    @GET("api_category.php")
    suspend fun getCategories(): CategoriesResponse

    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") amount: String,
        @Query("category") category: String,
        @Query("difficulty") difficulty: String
    ): QuestionsResponse
}