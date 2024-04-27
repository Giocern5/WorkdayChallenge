package com.example.workdaychallenge.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workdaychallenge.data.model.PokemonDetails
import com.example.workdaychallenge.data.model.PokemonQuery
import com.example.workdaychallenge.network.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
private const val LIMIT = 10

@HiltViewModel
class PokemonViewModel
@Inject constructor(
    private val service: PokemonService
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
    private var offset = 0

    fun setPokemonList() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = service.getPokemonList( LIMIT, offset)
                if(res.isSuccessful) {
                    val newData = res.body()?.results
                    newData?.let {
                        val currentData = _pokemonList.value?.toMutableList() ?: mutableListOf()
                        currentData.addAll(it)
                        _pokemonList.postValue(currentData)
                        offset += LIMIT
                    }
                } else {
                    _errorMessage.postValue("No results found!")
                    Log.e(TAG, res.errorBody().toString())
                }
            } catch (err: IOException) {
                _errorMessage.postValue("Network error occurred")
                Log.e(TAG, err.message.toString())
            } finally {
                _isLoading.postValue( false)
            }
        }
    }

    fun setPokemonDetails(name: String) {
        // remove a clear details here
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = service.getPokemonDetails(name.lowercase())
                if(res.isSuccessful) {
                    _pokemonDetails.postValue(res.body())
                } else {
                    _errorMessage.postValue( "No results found!")
                    Log.e(TAG, res.errorBody().toString())
                    // maybe needed? test again later
                // _pokemonDetails.postValue(null)
                }

            } catch (err: IOException) {
                // customer friendly error message
                _errorMessage.postValue("Network error occurred")
                Log.e(TAG, err.message.toString())
            } finally {
                _isLoading.postValue( false)
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
