package com.example.stuffedquiz.result

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stuffedquiz.R

@Composable
fun ResultScreen(
    score: Int,
    numberOfQuestions: Int,
    onHomeClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.final_score, score, numberOfQuestions),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(32.dp)
                .weight(1f)
                .fillMaxWidth()
                .wrapContentHeight(Alignment.CenterVertically)
        )
        Button(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(),
            onClick = onHomeClick
        ) {
            Text(text = stringResource(R.string.home_button))
        }
    }
}

@Preview
@Composable
fun ResultScreenPreview() {
    ResultScreen(3, 10, {})
}