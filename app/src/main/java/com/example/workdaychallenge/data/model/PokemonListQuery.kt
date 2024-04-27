package com.example.workdaychallenge.data.model

data class PokemonQuery(
    val name: String,
    val url: String
)

data class PokemonListQuery(val results:List<PokemonQuery>)