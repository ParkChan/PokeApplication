package com.poke.domain.entitiy.res

import com.poke.domain.entitiy.PokemonResponse
import com.poke.domain.entitiy.mapToModel
import com.poke.ui.main.model.PokemonListModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonListResponse(
    @field:Json(name = "pokemons")
    val pokemons: List<PokemonResponse>? = null
)

fun PokemonListResponse.mapToModel() =
    PokemonListModel(
        pokemons = pokemons?.map { it.mapToModel() } ?: emptyList()
    )