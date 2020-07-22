package com.poke.domain.entitiy

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "names")
    val names: List<String>? = null

)