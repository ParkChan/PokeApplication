package com.poke.network.api

import com.poke.data.response.PokemonListResponse
import com.poke.data.response.PokemonLocationListResponse
import retrofit2.http.GET

interface DemoApi {

    @GET("pokemon_name")
    suspend fun getPokemonListAsync(): PokemonListResponse

    @GET("pokemon_locations")
    suspend fun getPokemonLocationAsync(): PokemonLocationListResponse

}