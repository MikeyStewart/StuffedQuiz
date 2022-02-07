package com.example.stuffedquiz.game

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.stuffedquiz.R
import com.example.stuffedquiz.ui.common.LoadingIndicator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    viewModel: GameViewModel,
    category: Int,
    difficulty: String,
    onQuitCLick: () -> Unit,
    onFinishClick: () -> Unit
) {
    val questions by viewModel.questions.observeAsState()
    val score by viewModel.score.observeAsState()
    var currentQuestion by remember {
        mutableStateOf(1)
    }

    LaunchedEffect(Unit) {
        viewModel.load(category, difficulty)
    }

    if (questions.isNullOrEmpty()) {
        LoadingIndicator(modifier = Modifier.fillMaxSize())
    } else {
        GameScreenContent(
            questions = questions!!,
            score = score!!,
            viewModel = viewModel,
            currentQuestion = currentQuestion,
            goToNextQuestion = { currentQuestion += 1 },
            onQuitClick = onQuitCLick,
            onFinishClick = onFinishClick
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GameScreenContent(
    viewModel: GameViewModel,
    questions: List<QuestionViewState>,
    score: Int,
    currentQuestion: Int,
    goToNextQuestion: () -> Unit,
    onFinishClick: () -> Unit,
    onQuitClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.TopStart),
                onClick = onQuitClick
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.quit_content_description)
                )
            }
            Text(
                text = stringResource(id = R.string.score, score, questions.size),
                modifier = Modifier
                    .padding(32.dp)
                    .align(Alignment.Center),
                style = MaterialTheme.typography.h6
            )
        }

        // TODO disable swipe gesture
        val pagerState = rememberPagerState()
        HorizontalPager(
            state = pagerState,
            count = questions.size,
            modifier = Modifier.weight(1f)
        ) { index ->
            val question = questions[index]
            QuestionCard(
                index = index,
                question = question,
                submitAnswer = { selectedAnswer ->
                    // TODO dumb the view down a bit. This stuff should be handled by the VM
                    if (selectedAnswer == question.correctAnswer) {
                        viewModel.incrementScore()
                    }
                }
            )
        }

        val scope = rememberCoroutineScope()
        TextButton(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.End),
            onClick = {
                if (currentQuestion == questions.size) {
                    onFinishClick()
                } else {
                    // TODO current question index should be stored only in one place
                    goToNextQuestion()
                    scope.launch {
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage + 1
                        )
                    }
                }
            }
        ) {
            Text(
                text = stringResource(R.string.next_button)
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null
            )
        }
    }
}