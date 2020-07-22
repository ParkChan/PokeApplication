package com.poke.domain.entitiy

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpritesResponse(
    @field:Json(name = "back_default")
    val backDefault: String? = null,

    @field:Json(name = "back_female")
    val backFemale: String? = null,

    @field:Json(name = "back_shiny")
    val back_shiny: String? = null,

    @field:Json(name = "back_shiny_female")
    val back_shiny_female: String? = null,

    @field:Json(name = "front_default")
    val front_default: String? = null,

    @field:Json(name = "front_female")
    val front_female: String? = null,

    @field:Json(name = "front_shiny")
    val front_shiny: String? = null,

    @field:Json(name = "front_shiny_female")
    val front_shiny_female: String? = null
)