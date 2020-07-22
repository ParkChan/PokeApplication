package com.poke.domain.entitiy.res

import com.poke.domain.entitiy.PokemonLocationResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonLocationListResponse(

    @field:Json(name = "pokemons")
    val pokemons: List<PokemonLocationResponse>? = null

)