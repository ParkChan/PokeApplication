package com.poke.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poke.domain.entitiy.res.PokemonListResponse
import com.poke.domain.entitiy.res.PokemonLocationListResponse
import com.poke.domain.entitiy.res.mapToModel
import com.poke.network.NetworkResult
import com.poke.ui.main.repository.PokemonListRepository
import com.poke.ui.main.model.PokemonModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val pokemonListRepository: PokemonListRepository
) : ViewModel() {

    private val _pokemonList = MutableLiveData<List<PokemonModel>>()
    val pokemonList: LiveData<List<PokemonModel>> get() = _pokemonList

    private val _errMsg = MutableLiveData<String>()
    val errMsg: LiveData<String> get() = _errMsg

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getPokemonList() = viewModelScope.launch {
        showLoading()
        val pokemonListDeferred = async { pokemonListRepository.getPokemonList() }
        val locationListDeferrd = async { pokemonListRepository.getLocationList() }

        var pokemonListResponse: PokemonListResponse? = null
        var pokemonLocationListResponse: PokemonLocationListResponse? = null

        when (val networkResult = pokemonListDeferred.await()) {
            is NetworkResult.Success -> {
                pokemonListResponse = networkResult.data
            }
            is NetworkResult.Failure -> {
                _errMsg.value = networkResult.exception.message
            }
        }

        when (val networkResult = locationListDeferrd.await()) {
            is NetworkResult.Success -> {
                pokemonLocationListResponse = networkResult.data
            }
            is NetworkResult.Failure -> {
                _errMsg.value = networkResult.exception.message
            }
        }

        result(
            pokemonListResponse = pokemonListResponse,
            pokemonLocationListResponse = pokemonLocationListResponse
        )
        hideLoading()
    }

    private fun result(
        pokemonListResponse: PokemonListResponse?,
        pokemonLocationListResponse: PokemonLocationListResponse?
    ) {
        if (pokemonListResponse != null && pokemonLocationListResponse != null) {
            val pokemonListModel = pokemonListResponse.mapToModel().pokemons
            val pokemonLocationListModel = pokemonLocationListResponse.mapToModel().pokemons
            _pokemonList.value = pokemonListModel
        }
    }

    private fun hideLoading() {
        _isLoading.value = false
    }

    private fun showLoading() {
        _isLoading.value = true
    }
}
