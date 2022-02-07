package com.example.stuffedquiz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stuffedquiz.game.GameScreen
import com.example.stuffedquiz.game.GameViewModel
import com.example.stuffedquiz.home.HomeScreen
import com.example.stuffedquiz.home.HomeViewModel
import com.example.stuffedquiz.navigation.NavigationDestination.*
import com.example.stuffedquiz.result.ResultScreen

@Composable
fun NavigationContainer(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    gameViewModel: GameViewModel
) {
    NavHost(navController = navController, startDestination = Home.route) {
        composable(Home.route) {
            HomeScreen(
                viewModel = homeViewModel,
                onStart = {
                    navController.navigate(
                        Game(
                            category = homeViewModel.selectedCategory.value?.id!!,
                            difficulty = homeViewModel.selectedDifficulty.value?.name!!
                        ).build()
                    )
                },
            )
        }
        composable(
            route = Game.route,
            arguments = Game.navArgs
        ) {
            GameScreen(
                viewModel = gameViewModel,
                category = it.arguments?.getInt(Game.argCategory)!!,
                difficulty = it.arguments?.getString(Game.argDifficulty)!!,
                onFinishClick = {
                    navController.navigate(Result.route)
                },
                onQuitCLick = {
                    navController.navigate(Home.route)
                    homeViewModel.resetSelections()
                    gameViewModel.resetGame()
                }
            )
        }
        composable(Result.route) {
            ResultScreen(
                score = gameViewModel.score.value!!,
                numberOfQuestions = gameViewModel.questions.value!!.size
            ) {
                navController.navigate(Home.route)
                homeViewModel.resetSelections()
                gameViewModel.resetGame()
            }
        }
    }
}