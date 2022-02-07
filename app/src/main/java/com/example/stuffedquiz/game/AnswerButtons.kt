package com.example.stuffedquiz.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultAnswer(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick
    ) {
        Text(text = text)
    }
}

@Composable
fun SelectedCorrectAnswer(
    text: String
) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Green
        ),
        onClick = {}
    ) {
        Text(text = text)
    }
}

@Composable
fun UnselectedCorrectAnswer(
    text: String
) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = BorderStroke(1.dp, Color.Green),
        onClick = {}
    ) {
        Text(text = text)
    }
}

@Composable
fun SelectedIncorrectAnswer(
    text: String
) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Red
        ),
        onClick = {}
    ) {
        Text(text = text)
    }
}