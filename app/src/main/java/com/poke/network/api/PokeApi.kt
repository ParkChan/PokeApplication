package com.poke.network.api

import com.poke.data.response.PokemonDetailInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonDetailInfoAsync(
        @Query("id") id: Int
    ): PokemonDetailInfoResponse
}