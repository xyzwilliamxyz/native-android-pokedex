package com.example.pokedex.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokedex.presentation.dashboard.screen.DashboardScreen
import com.example.pokedex.presentation.navigation.NavigationDirection.NAVIGATE_UP
import com.example.pokedex.presentation.pokemoninfo.screen.PokemonInfoScreen
import com.example.pokedex.presentation.pokemonlist.screen.PokemonListScreen

@Composable
fun NavigationRoutes(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationDirection.DASHBOARD.path
    ) {
        composable(route = NavigationDirection.DASHBOARD.path) {
            DashboardScreen(onNavigate = { handleNavigation(navController, it) })
        }
        composable(route = NavigationDirection.POKEMON_LIST.path) {
            PokemonListScreen(onNavigate = { handleNavigation(navController, it) })
        }
        composable(
            route = NavigationDirection.POKEMON_INFO.path,
            arguments = listOf(
                navArgument(
                    name = "pokemonId"
                ) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            PokemonInfoScreen(
                pokemonId = backStackEntry.arguments?.getInt("pokemonId") ?: 1,
                onNavigate = { handleNavigation(navController, it) }
            )
        }
    }
}

private fun handleNavigation(navController: NavHostController, direction: String) {
    when (direction) {
        NAVIGATE_UP.path -> navController.navigateUp()
        else -> navController.navigate(direction)
    }
}