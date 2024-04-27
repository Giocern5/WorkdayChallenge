package com.example.workdaychallenge.ui.screen

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workdaychallenge.ui.viewmodel.PokemonViewModel

@Composable
fun PokemonApp() {
    val navController = rememberNavController()
    // Used for shared viewmodel
    val viewModel =  hiltViewModel<PokemonViewModel>()

    NavHost(navController = navController, startDestination = "pokemonListScreen",
        modifier = Modifier.background(Color.DarkGray)
    ) {
        composable("pokemonListScreen") {
            PokemonListScreen(navController, viewModel)
        }
        composable(route = Screen.PokemonDetail.route) {
            PokemonDetailScreen(navController, viewModel)
        }
    }
}