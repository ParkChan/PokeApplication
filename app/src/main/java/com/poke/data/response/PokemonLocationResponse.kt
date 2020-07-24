package com.poke.data.response

import com.poke.ui.main.model.PokemonLocationModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonLocationResponse(

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "lat")
    val lat: Double? = null,

    @field:Json(name = "lon")
    val lon: Double? = null
)

fun PokemonLocationResponse?.mapToModel() = this?.let {
    PokemonLocationModel(
        id = id ?: 0,
        lat = lat ?: 0.0,
        lon = lon ?: 0.0
    )
} ?: PokemonLocationModel()