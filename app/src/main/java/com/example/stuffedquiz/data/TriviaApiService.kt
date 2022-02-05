package com.example.stuffedquiz.data

import retrofit2.http.GET

/**
 * A Retrofit service for the Open Trivia API (https://opentdb.com/)
 */
interface TriviaApiService {

    @GET("api_token.php?command=request")
    suspend fun getSessionToken(): SessionTokenResponse

    @GET("api_category.php")
    suspend fun getCategories(): CategoriesResponse

    // TODO: get questions based on category and difficulty
}