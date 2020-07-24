package com.poke.data.response

import com.poke.ui.main.model.SpritesModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpritesResponse(
    @field:Json(name = "back_default")
    val backDefault: String? = null,

    @field:Json(name = "back_female")
    val backFemale: String? = null,

    @field:Json(name = "back_shiny")
    val backShiny: String? = null,

    @field:Json(name = "back_shiny_female")
    val backShinyFemale: String? = null,

    @field:Json(name = "front_default")
    val frontDefault: String? = null,

    @field:Json(name = "front_female")
    val frontFemale: String? = null,

    @field:Json(name = "front_shiny")
    val frontShiny: String? = null,

    @field:Json(name = "front_shiny_female")
    val frontShinyFemale: String? = null
)

fun SpritesResponse?.mapToModel() = this?.let {
    SpritesModel(
        backDefault = backDefault ?: "",
        backFemale = backFemale ?: "",
        backShiny = backShiny ?: "",
        backShinyFemale = backShinyFemale ?: "",
        frontDefault = frontDefault ?: "",
        frontFemale = frontFemale ?: "",
        frontShiny = frontShiny ?: "",
        frontShinyFemale = frontShinyFemale ?: ""
    )
} ?: SpritesModel()
