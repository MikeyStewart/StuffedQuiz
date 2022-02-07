package com.example.stuffedquiz.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stuffedquiz.model.Question

@Composable
fun QuestionCard(
    index: Int,
    question: Question
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = "Question ${index + 1}",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = question.question,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
        AnswerList(
            correctAnswer = question.correctAnswer,
            allAnswers = question.incorrectAnswers.plus(question.correctAnswer).shuffled()
        )
    }
}

@Composable
fun AnswerList(
    correctAnswer: String,
    allAnswers: List<String>
) {
    var selectedAnswer: String? by remember {
        mutableStateOf(null)
    }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        allAnswers.forEach {
            if (selectedAnswer == null) {
                DefaultAnswer(
                    text = it,
                    onClick = { selectedAnswer = it }
                )
            } else {
                when {
                    it == selectedAnswer && it == correctAnswer -> SelectedCorrectAnswer(text = it)
                    it == selectedAnswer && it != correctAnswer -> SelectedIncorrectAnswer(text = it)
                    it != selectedAnswer && it == correctAnswer -> UnselectedCorrectAnswer(text = it)
                    else -> DefaultAnswer(text = it) {}
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionCardPreview() {
    QuestionCard(
        index = 1,
        question = Question(
            question = "What is life?",
            correctAnswer = "42",
            incorrectAnswers = listOf("Who knows", "24", "Magic")
        )
    )
}