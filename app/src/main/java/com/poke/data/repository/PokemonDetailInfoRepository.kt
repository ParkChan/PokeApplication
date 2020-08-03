package com.poke.data.repository

import com.poke.data.response.PokemonDetailInfoResponse
import com.poke.network.NetworkResult
import javax.inject.Inject

class PokemonDetailInfoRepository @Inject constructor(
    private val pokemonDetailInfoRepositoryComponent: PokemonDetailInfoRepositoryComponent
){
    suspend fun getPokemonDetailInfo(id: Int) =
        pokemonDetailInfoRepositoryComponent.getPokemonDetailInfo(id)

    interface PokemonDetailInfoRepositoryComponent{
        suspend fun getPokemonDetailInfo(id: Int) : NetworkResult<PokemonDetailInfoResponse>
    }
}