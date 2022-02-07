package com.example.stuffedquiz.util

import android.text.Html
import com.example.stuffedquiz.model.Question

/**
 * Helper function that decodes funky Html characters from each question and answer.
 * E.g &quot; becomes "
 */
fun List<Question>.decodeHtml(): List<Question> {
    return map { question ->
        val flag = Html.FROM_HTML_MODE_LEGACY
        question.copy(
            question = Html.fromHtml(question.question, flag).toString(),
            correctAnswer = Html.fromHtml(question.correctAnswer, flag)
                .toString(),
            incorrectAnswers = question.incorrectAnswers.map { answer ->
                Html.fromHtml(answer, flag).toString()
            }
        )
    }
}