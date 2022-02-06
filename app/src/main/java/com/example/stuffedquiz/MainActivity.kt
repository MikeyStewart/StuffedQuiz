package com.example.stuffedquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.stuffedquiz.game.GameViewModel
import com.example.stuffedquiz.home.HomeViewModel
import com.example.stuffedquiz.navigation.NavigationContainer
import com.example.stuffedquiz.ui.theme.StuffedQuizTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StuffedQuizTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationContainer(
                        navController = rememberNavController(),
                        homeViewModel = homeViewModel,
                        gameViewModel = gameViewModel
                    )
                }
            }
        }
    }
}