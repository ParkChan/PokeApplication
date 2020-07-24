package com.poke.network.api

import com.poke.data.response.PokemonDetailInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("pokemon/{id}")
    suspend fun getPokemonDetailInfoAsync(
        @Path("id") id: Int
    ): PokemonDetailInfoResponse
}