package com.poke.network

import com.poke.domain.entitiy.res.PokemonListResponse
import com.poke.domain.entitiy.res.PokemonLocationListResponse
import retrofit2.http.GET

interface DemoApi {

    @GET("pokemon_name")
    suspend fun getPokemonListAsync(): PokemonListResponse

    @GET("pokemon_locations")
    suspend fun getPokemonLocationAsync(): PokemonLocationListResponse

}