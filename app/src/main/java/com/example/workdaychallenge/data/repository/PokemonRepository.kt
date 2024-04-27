package com.example.workdaychallenge.data.repository

import com.example.workdaychallenge.data.model.PokemonDetails
import com.example.workdaychallenge.data.model.PokemonListQuery

interface PokemonRepository {
    suspend fun getPokemonList(): PokemonListQuery?
    suspend fun getPokemon(name: String): PokemonDetails?
}