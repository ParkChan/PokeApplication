package com.poke.data.response

import com.poke.ui.main.model.PokemonListModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonListResponse(
    @field:Json(name = "pokemons")
    val pokemonList: List<PokemonResponse>? = null
)

fun PokemonListResponse.mapToModel() =
    PokemonListModel(
        pokemonList = pokemonList?.map { it.mapToModel() } ?: emptyList()
    )