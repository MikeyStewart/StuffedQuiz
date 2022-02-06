package com.example.stuffedquiz.data

import com.example.stuffedquiz.model.Category
import com.example.stuffedquiz.model.Question
import java.util.*
import javax.inject.Inject

class TriviaRepository @Inject constructor(
    private val dataSource: TriviaDataSource
) {

    suspend fun getSessionToken(): String {
        return dataSource.getSessionToken().token
    }

    suspend fun getCategories(): List<Category> {
        return dataSource.getCategories().triviaCategories
    }

    suspend fun getQuestions(
        amount: String,
        category: Int,
        difficulty: String
    ): List<Question> {
        val response = dataSource.getQuestions(
            amount,
            category.toString(),
            difficulty.lowercase(Locale.getDefault())
        )
        return response.results
    }
}