package com.poke.data

import javax.inject.Inject

class PokemonDetailInfoRepository @Inject constructor(
    private val pokemonDetailInfoDataSource: PokemonDetailInfoDataSource
) {
    suspend fun getPokemonDetailInfo(id: Int) =
        pokemonDetailInfoDataSource.getPokemonDetailInfo(id)
}