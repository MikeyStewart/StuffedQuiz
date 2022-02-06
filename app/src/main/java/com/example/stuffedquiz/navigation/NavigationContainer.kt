package com.example.stuffedquiz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stuffedquiz.game.GameScreen
import com.example.stuffedquiz.game.GameViewModel
import com.example.stuffedquiz.home.HomeScreen
import com.example.stuffedquiz.home.HomeViewModel

@Composable
fun NavigationContainer(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    gameViewModel: GameViewModel
) {
    NavHost(navController = navController, startDestination = NavigationDestination.Home.route) {
        composable(NavigationDestination.Home.route) {
            HomeScreen(
                viewModel = homeViewModel,
                onStart = {
                    navController.navigate(
                        NavigationDestination.Game(
                            category = homeViewModel.selectedCategory.value?.id!!,
                            difficulty = homeViewModel.selectedDifficulty.value?.name!!
                        ).build()
                    )
                },
            )
        }
        composable(
            route = NavigationDestination.Game.route,
            arguments = NavigationDestination.Game.navArgs
        ) {
            GameScreen(
                gameViewModel,
                it.arguments?.getInt(NavigationDestination.Game.argCategory)!!,
                it.arguments?.getString(NavigationDestination.Game.argDifficulty)!!,
            )
        }
    }
}