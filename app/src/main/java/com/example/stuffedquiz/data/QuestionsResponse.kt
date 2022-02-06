package com.example.stuffedquiz.data

import com.example.stuffedquiz.model.Question
import com.squareup.moshi.Json

data class QuestionsResponse(
    @Json(name = "response_code")
    val responseCode: Int,
    val results: List<Question>
)