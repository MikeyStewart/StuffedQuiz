package com.example.stuffedquiz.model

import com.squareup.moshi.Json

data class Question(
    val question: String,
    @Json(name = "correct_answer")
    val correctAnswer: String,
    @Json(name = "incorrect_answers")
    val incorrectAnswers: List<String>
)