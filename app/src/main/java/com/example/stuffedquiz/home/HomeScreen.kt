package com.example.stuffedquiz.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.stuffedquiz.model.Category
import com.example.stuffedquiz.model.Difficulty

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onStart: () -> Unit
) {
    val categories by viewModel.categories.observeAsState()
    val selectedCategory by viewModel.selectedCategory.observeAsState()
    val selectedDifficulty by viewModel.selectedDifficulty.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            text = "Stuffed Quiz",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4
        )
        GameSettings(
            viewModel = viewModel,
            categories = categories,
            selectedCategory = selectedCategory,
            selectedDifficulty = selectedDifficulty,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(32.dp)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            onClick = onStart,
            enabled = selectedCategory != null && selectedDifficulty != null
        ) {
            Text(text = "Start")
        }
    }
}

@Composable
fun GameSettings(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    categories: List<Category>?,
    selectedCategory: Category?,
    selectedDifficulty: Difficulty?
) {
    Column(
        modifier = modifier,
    ) {
        CategoryDropDown(
            categories = categories,
            onCategorySelected = viewModel::updateSelectedCategory,
            selectedCategory = selectedCategory
        )
        DifficultyDropDown(
            onDifficultySelected = viewModel::updateSelectedDifficulty,
            selectedDifficulty = selectedDifficulty
        )
    }
}