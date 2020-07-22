package com.poke.network

import com.poke.domain.entitiy.res.PokemonDetailInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonDetailInfoAsync(
        @Query("id") id: Int
    ): PokemonDetailInfoResponse
}