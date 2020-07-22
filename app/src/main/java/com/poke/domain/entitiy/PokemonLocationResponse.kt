package com.poke.domain.entitiy

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