package com.example.stuffedquiz.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.stuffedquiz.R
import com.example.stuffedquiz.model.Category

@Composable
fun CategoryDropDown(
    categories: List<Category>?,
    onCategorySelected: (Category) -> Unit,
    selectedCategory: Category?
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        // TODO change to outlinedTextField to make it look noice
        Text(
            selectedCategory?.name ?: stringResource(R.string.select_category),
            modifier = Modifier
                .clickable(onClick = { expanded = true })
                .fillMaxWidth()
                .padding(32.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            categories?.forEach {
                DropdownMenuItem(
                    onClick = {
                        onCategorySelected(it)
                        expanded = false
                    }
                ) {
                    Text(text = it.name)
                }
            }
        }
    }
}