package com.example.stuffedquiz.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavigationDestination(
    val route: String,
    val navArgs: List<NamedNavArgument> = emptyList()
) {
    open fun build(): String {
        return route
    }

    object Home : NavigationDestination("home")

    class Game(
        val category: String,
        val difficulty: String
    ) : NavigationDestination(route, navArgs) {
        companion object {
            const val argCategory = "category"
            const val argDifficulty = "difficulty"
            const val route = "game/{$argCategory}/{$argDifficulty}"
            val navArgs = listOf(
                navArgument(argCategory) {
                    type = NavType.StringType
                },
                navArgument(argDifficulty) {
                    type = NavType.StringType
                }
            )
        }

        override fun build(): String {
            return route
                .replace("{$argCategory}", category)
                .replace("{$argDifficulty}", difficulty)
        }
    }
}