package com.example.stuffedquiz.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.stuffedquiz.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QuestionCard(
    index: Int,
    question: QuestionViewState,
    submitAnswer: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = stringResource(id = R.string.question, index + 1),
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
            allAnswers = question.allAnswers,
            submitAnswer = submitAnswer
        )
    }
}

@Composable
fun AnswerList(
    correctAnswer: String,
    allAnswers: List<String>,
    submitAnswer: (String) -> Unit
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
                    onClick = {
                        selectedAnswer = it
                        submitAnswer(it)
                    }
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
        question = QuestionViewState(
            question = "What is life?",
            correctAnswer = "42",
            allAnswers = listOf("Who knows", "24", "Magic", "42")
        ),
        {}
    )
}