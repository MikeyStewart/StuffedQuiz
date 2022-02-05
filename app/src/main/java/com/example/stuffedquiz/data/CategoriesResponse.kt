package com.example.stuffedquiz.data

import com.example.stuffedquiz.model.Category
import com.squareup.moshi.Json

data class CategoriesResponse(
    @Json(name = "trivia_categories")
    val triviaCategories: List<Category>
)