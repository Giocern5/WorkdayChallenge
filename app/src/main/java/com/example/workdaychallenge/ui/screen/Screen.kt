package com.example.workdaychallenge.ui.screen

sealed class Screen(val route: String) {
    object PokemonList : Screen("pokemonListScreen")
    object PokemonDetail : Screen("pokemonDetailScreen")

    fun routeWithArgument(argument: String): String {
        return "$route/$argument"
    }
}