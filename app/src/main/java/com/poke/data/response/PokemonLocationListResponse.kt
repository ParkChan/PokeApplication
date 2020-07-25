package com.poke.data.response

import com.poke.ui.main.model.PokemonLocationListModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonLocationListResponse(
    @field:Json(name = "pokemons")
    val locationList: List<PokemonLocationResponse>? = null
)

fun PokemonLocationListResponse.mapToModel() =
    PokemonLocationListModel(
        locationList = locationList?.map { it.mapToModel() } ?: emptyList()
    )