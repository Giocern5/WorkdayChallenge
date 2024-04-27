package com.example.workdaychallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.workdaychallenge.data.model.*
import com.example.workdaychallenge.network.PokemonService
import com.example.workdaychallenge.ui.viewmodel.PokemonViewModel
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.*
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: PokemonViewModel
    private lateinit var service: PokemonService
    private val pokemonName = "bulbasaur"
    private val sprites = Sprites(Other(Home("")))

    @Before
    fun setup() {
        service = mock()
        viewModel = PokemonViewModel(service)
    }


    @Test
    fun `test clearPokemonDetails`() {
        viewModel.setPokemonDetails(pokemonName)
        viewModel.clearPokemonDetails()
        assertNull(viewModel.pokemonDetails.value)
    }

}