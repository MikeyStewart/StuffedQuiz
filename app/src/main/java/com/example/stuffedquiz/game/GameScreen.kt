package com.example.stuffedquiz.game

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stuffedquiz.model.Question
import com.example.stuffedquiz.ui.common.LoadingIndicator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    category: Int,
    difficulty: String
) {
    val questions by viewModel.questions.observeAsState()
    val score by viewModel.score.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.load(category, difficulty)
    }

    if (questions.isNullOrEmpty()) {
        LoadingIndicator(modifier = Modifier.fillMaxSize())
    } else {
        GameScreenContent(
            questions = questions!!,
            score = score!!
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GameScreenContent(
    questions: List<Question>,
    score: Int
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.TopStart),
                onClick = { /*TODO quit game*/ }
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Quit game")
            }
            Text(
                text = "Score: $score/${questions.size}",
                modifier = Modifier
                    .padding(32.dp)
                    .align(Alignment.Center),
                style = MaterialTheme.typography.h6
            )
        }

        val pagerState = rememberPagerState()
        HorizontalPager(
            state = pagerState,
            count = questions.size,
            modifier = Modifier.weight(1f)
        ) { index ->
            QuestionCard(index, questions[index])
        }

        val scope = rememberCoroutineScope()
        TextButton(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.End),
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        page = pagerState.currentPage + 1
                    )
                }
            }
        ) {
            Text(text = "Next")
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenContentPreview() {
    GameScreenContent(
        listOf(
            Question(
                question = "What is life?",
                correctAnswer = "42",
                incorrectAnswers = listOf("Who knows", "24", "Magic")
            )
        ),
        0
    )
}