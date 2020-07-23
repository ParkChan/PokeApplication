package com.poke.domain.entitiy

import com.poke.ui.main.model.PokemonModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "names")
    val names: List<String>? = null
)

fun PokemonResponse?.mapToModel() = this?.let{
    PokemonModel(
        id = id ?: 0,
        names = names ?: emptyList()
    )
} ?: PokemonModel()