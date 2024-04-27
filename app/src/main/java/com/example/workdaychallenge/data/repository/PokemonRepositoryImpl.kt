package com.example.workdaychallenge.data.repository

import com.example.workdaychallenge.data.model.PokemonDetails
import com.example.workdaychallenge.data.model.PokemonQuery
import com.example.workdaychallenge.network.PokemonService
import javax.inject.Inject
import  com.example.workdaychallenge.data.model.Result
import java.io.IOException

private const val LIMIT = 10

class PokemonRepositoryImpl @Inject constructor(private val service: PokemonService) : PokemonRepository {
    private var offset = 0

    override suspend fun getPokemonList(): Result<List<PokemonQuery>> {
        return try {
            val response = service.getPokemonList(LIMIT, offset)
            if (response.isSuccessful) {
                val newData = response.body()?.results
                if (newData != null) {
                    offset += LIMIT
                    Result.Success(newData)
                } else {
                    Result.Error(Exception("Empty response"))
                }
            } else {
                Result.Error(Exception(response.message()))
            }
        } catch (e: IOException) {
            Result.Error(e)
        }
    }

    override suspend fun getPokemonDetails(name: String): Result<PokemonDetails> {
        return try {
            val response = service.getPokemonDetails(name.lowercase())
            if (response.isSuccessful) {
                val details = response.body()
                if (details != null) {
                    Result.Success(details)
                } else {
                    Result.Error(Exception("Empty response"))
                }
            } else {
                Result.Error(Exception("Empty response"))
            }
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}