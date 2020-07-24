package com.poke.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDetailInfoResponse(
    @field:Json(name = "sprites")
    val sprites: SpritesResponse? = null,

    @field:Json(name = "height")
    val height: Int? = null,

    @field:Json(name = "weight")
    val weight: Int? = null
)