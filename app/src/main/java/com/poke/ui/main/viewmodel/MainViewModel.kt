package com.poke.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poke.data.PokemonDetailInfoRepository
import com.poke.data.PokemonListRepository
import com.poke.data.response.PokemonDetailInfoResponse
import com.poke.data.response.PokemonListResponse
import com.poke.data.response.PokemonLocationListResponse
import com.poke.data.response.mapToModel
import com.poke.network.NetworkResult
import com.poke.ui.main.model.PokemonModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val pokemonListRepository: PokemonListRepository,
    private val pokemonDetailInfoRepository: PokemonDetailInfoRepository
) : ViewModel() {

    private val _pokemonList = MutableLiveData<List<PokemonModel>>()
    val pokemonList: LiveData<List<PokemonModel>> get() = _pokemonList

    private val _errMsg = MutableLiveData<String>()
    val errMsg: LiveData<String> get() = _errMsg

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _searchName = MutableLiveData<String>()
    val searchName: LiveData<String> get() = _searchName

    private val _selectedItem = MutableLiveData<PokemonModel>()
    val selectedItem: LiveData<PokemonModel> get() = _selectedItem

    private val pokemonHashSet: MutableSet<PokemonModel> = HashSet()

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

        mergePokeListAndLocationInfo(
            pokemonListResponse = pokemonListResponse,
            pokemonLocationListResponse = pokemonLocationListResponse
        ).let {
            initPokemonData(it)
        }

        hideLoading()
    }

    private fun mergePokeListAndLocationInfo(
        pokemonListResponse: PokemonListResponse?,
        pokemonLocationListResponse: PokemonLocationListResponse?
    ): Map<Int, PokemonModel> {
        if (pokemonListResponse != null) {
            val pokemonMap =
                pokemonListResponse.mapToModel().pokemons.map { it.id to it }.toMap()

            pokemonLocationListResponse?.run {
                val pokemonLocationMap =
                    this.mapToModel().pokemons.map { it.id to it }.toMap()

                for ((id, data) in pokemonLocationMap) {
                    pokemonMap[id]?.apply {
                        lat = data.lat
                        lon = data.lon
                    }
                }
            }
            return pokemonMap
        }
        return emptyMap()
    }

    private fun initPokemonData(map: Map<Int, PokemonModel>) {
        pokemonHashSet.clear()
        for ((_, data) in map) {
            pokemonHashSet.add(data)
        }
    }

    fun searchPokemon(keyword: String) {
        _searchName.value = keyword

        if (keyword.isNotEmpty()) {
            val searchResult = mutableListOf<PokemonModel>()
            val it = pokemonHashSet.iterator()
            while (it.hasNext()) {
                val data = it.next()
                for (pokemonName in data.names) {
                    if (pokemonName.contains(keyword)) {
                        data.filterName = pokemonName
                        searchResult.add(data)
                    }
                }
            }
            _pokemonList.value = searchResult
        } else {
            _pokemonList.value = emptyList()
        }
    }

    fun onClick(pokemonModel: PokemonModel) = viewModelScope.launch {
        showLoading()

        var pokemonDetailInfoResponse: PokemonDetailInfoResponse? = null

        val pokemonDetailInfoDeferred =
            async { pokemonDetailInfoRepository.getPokemonDetailInfo(pokemonModel.id) }

        when (val networkResult = pokemonDetailInfoDeferred.await()) {
            is NetworkResult.Success -> {
                pokemonDetailInfoResponse = networkResult.data
            }
            is NetworkResult.Failure -> {
                _errMsg.value = networkResult.exception.message
            }
        }

        pokemonDetailInfoResponse?.let {
            pokemonModel.detailInfo = pokemonDetailInfoResponse.mapToModel()
        }

        _selectedItem.value = pokemonModel

        hideLoading()
    }

    private operator fun String.contains(other: String): Boolean =
        this.contains(other, ignoreCase = true)

    private fun hideLoading() {
        _isLoading.value = false
    }

    private fun showLoading() {
        _isLoading.value = true
    }
}
