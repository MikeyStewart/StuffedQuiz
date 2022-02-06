package com.example.stuffedquiz.game

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun GameScreen(
    category: String,
    difficulty: String
) {
    Text(text = category + difficulty)
}