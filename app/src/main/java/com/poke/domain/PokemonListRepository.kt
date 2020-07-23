package com.poke.domain

import javax.inject.Inject

class PokemonListRepository @Inject constructor(
    private val pokemonListDataSource: PokemonListDataSource
) {
    suspend fun getPokemonList() =
        pokemonListDataSource.getPokemonList()

    suspend fun getLocationList() =
        pokemonListDataSource.getLocationList()

}