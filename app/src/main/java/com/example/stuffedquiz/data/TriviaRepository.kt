package com.example.stuffedquiz.data

import com.example.stuffedquiz.model.Category
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
}