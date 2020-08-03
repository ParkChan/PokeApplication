package com.poke.data.repository

import com.poke.data.response.PokemonListResponse
import com.poke.data.response.PokemonLocationListResponse
import com.poke.network.NetworkResult
import javax.inject.Inject

class PokemonListRepository @Inject constructor(
    private val pokemonListRepositoryComponent: PokemonListRepositoryComponent
) {
    suspend fun getPokemonList() =
        pokemonListRepositoryComponent.getPokemonList()

    suspend fun getLocationList() =
        pokemonListRepositoryComponent.getLocationList()

    interface PokemonListRepositoryComponent {
        suspend fun getPokemonList(): NetworkResult<PokemonListResponse>
        suspend fun getLocationList(): NetworkResult<PokemonLocationListResponse>
    }
}