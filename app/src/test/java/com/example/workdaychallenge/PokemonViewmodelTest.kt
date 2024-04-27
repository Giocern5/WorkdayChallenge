package com.example.workdaychallenge

import com.example.workdaychallenge.data.repository.PokemonRepository
import com.example.workdaychallenge.data.repository.PokemonRepositoryImpl
import com.example.workdaychallenge.network.PokemonService
import com.example.workdaychallenge.ui.viewmodel.PokemonViewModel
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class PokemonViewModelTest {

//    private lateinit var viewModel: PokemonViewModel
//    private lateinit var service: PokemonService
//    private lateinit var repositoryImpl: PokemonRepositoryImpl
//
//    private val pokemonName = "bulbasaur"

//    @Before
//    fun setup() {
//        repositoryImpl = mock(PokemonRepositoryImpl::class.java)
//        service = mock()
//        viewModel = PokemonViewModel(PokemonRepositoryImpl(service))
//    }
//
//
//    @Test
//    fun `test clearPokemonDetails`() {
//        Mockito.`when`(repositoryImpl.getPokemonList()).thenReturn(Single.just(listOf<TrendingRepo>()))
//        trendingViewModel.fetchTrendingRepos()
//        verify(trendingRepository, times(1)).getTrendingRepos()
//
//
//        viewModel.setPokemonDetails(pokemonName)
//        viewModel.clearPokemonDetails()
//        assertNull(viewModel.pokemonDetails.value)
//    }

}