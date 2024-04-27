package com.example.workdaychallenge.network

import com.example.workdaychallenge.data.model.PokemonDetails
import com.example.workdaychallenge.data.model.PokemonListQuery
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int,
                               @Query("offset") offset: Int): Response<PokemonListQuery>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String): Response<PokemonDetails>
}