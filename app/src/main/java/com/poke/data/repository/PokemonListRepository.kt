package com.poke.data.repository

import com.poke.data.datasource.PokemonListDataSource
import javax.inject.Inject

class PokemonListRepository @Inject constructor(
    private val pokemonListDataSource: PokemonListDataSource
) {
    suspend fun getPokemonList() =
        pokemonListDataSource.getPokemonList()

    suspend fun getLocationList() =
        pokemonListDataSource.getLocationList()

}