package com.example.workdaychallenge.data.repository

import com.example.workdaychallenge.data.model.PokemonDetails
import com.example.workdaychallenge.data.model.PokemonQuery
import  com.example.workdaychallenge.data.model.Result

interface PokemonRepository {
    suspend fun getPokemonList(): Result<List<PokemonQuery>>
    suspend fun getPokemonDetails(name: String): Result<PokemonDetails>
}