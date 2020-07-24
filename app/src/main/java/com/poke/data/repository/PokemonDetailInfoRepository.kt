package com.poke.data.repository

import com.poke.data.datasource.PokemonDetailInfoDataSource
import javax.inject.Inject

class PokemonDetailInfoRepository @Inject constructor(
    private val pokemonDetailInfoDataSource: PokemonDetailInfoDataSource
) {
    suspend fun getPokemonDetailInfo(id: Int) =
        pokemonDetailInfoDataSource.getPokemonDetailInfo(id)
}