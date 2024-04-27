package com.example.workdaychallenge.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workdaychallenge.data.model.PokemonDetails
import com.example.workdaychallenge.data.model.PokemonQuery
import com.example.workdaychallenge.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import  com.example.workdaychallenge.data.model.Result

@HiltViewModel
class PokemonViewModel
@Inject constructor(
    private val repository: PokemonRepository,
    ) : ViewModel() {

    companion object {
        const val TAG = "PostFeedViewModel"
    }
    // TODO: Use a sealed class to hold this
    private val _pokemonList = MutableLiveData<List<PokemonQuery>> ()
    val pokemonList: LiveData<List<PokemonQuery>> get() = _pokemonList
    private val _pokemonDetails = MutableLiveData<PokemonDetails?> ()
    val pokemonDetails: LiveData<PokemonDetails?> get() = _pokemonDetails
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

     fun setPokemonList() {
        _isLoading.value = true
        viewModelScope.launch {
            when (val result = repository.getPokemonList()) {
                is Result.Success -> {
                    val currentData = _pokemonList.value.orEmpty().toMutableList()
                    currentData.addAll(result.data)
                    _pokemonList.value = currentData
                    _isLoading.value = false
                }
                is Result.Error -> {
                    Log.e(TAG, result.exception.message.toString())
                    _errorMessage.value = result.exception.message.toString()
                    _isLoading.value = false
                }  is Result.Loading -> {}
            }
        }
    }

    fun setPokemonDetails(name: String) {
        _isLoading.value = true
        viewModelScope.launch {
            when (val result = repository.getPokemonDetails(name)) {
                is Result.Success -> {
                    _pokemonDetails.value = result.data
                    _isLoading.value = false
                }
                is Result.Error -> {
                    Log.e(TAG, result.exception.message.toString())
                    _errorMessage.value = result.exception.message.toString()
                    _isLoading.value = false
                } is Result.Loading -> {}
            }
        }
    }

    fun clearPokemonDetails() {
        _pokemonDetails.postValue(null)
    }

    fun clearErrorMessage() {
        _errorMessage.postValue("")
    }
}
