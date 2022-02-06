package com.example.stuffedquiz.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.stuffedquiz.model.Question

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    category: Int,
    difficulty: String
) {
    val questions by viewModel.questions.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.load(category, difficulty)
    }

    Test(questions = questions)
}

@Composable
fun Test(
    questions: List<Question>?
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        questions?.forEach {
            Text(text = it.question)
        }
    }
}