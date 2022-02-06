package com.example.stuffedquiz.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
            text = "Question ${index+1}",
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
            incorrectAnswers = question.incorrectAnswers
        )
    }
}

@Composable
fun AnswerList(
    correctAnswer: String,
    incorrectAnswers: List<String>,

) {
    val allAnswers = incorrectAnswers.plus(correctAnswer).shuffled()
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        allAnswers.forEach {
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {

                }
            ) {
                Text(text = it)
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