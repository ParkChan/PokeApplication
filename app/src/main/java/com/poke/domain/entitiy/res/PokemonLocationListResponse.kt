package com.poke.domain.entitiy.res

import com.poke.domain.entitiy.PokemonLocationResponse
import com.poke.domain.entitiy.mapToModel
import com.poke.ui.main.model.PokemonLocationListModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonLocationListResponse(
    @field:Json(name = "pokemons")
    val pokemons: List<PokemonLocationResponse>? = null
)
fun PokemonLocationListResponse.mapToModel() =
    PokemonLocationListModel(
        pokemons = pokemons?.map { it.mapToModel() } ?: emptyList()
    )