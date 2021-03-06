package com.poke.ui.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpritesModel(
    val backDefault: String = "",
    val backFemale: String = "",
    val backShiny: String = "",
    val backShinyFemale: String = "",
    val frontDefault: String = "",
    val frontFemale: String = "",
    val frontShiny: String = "",
    val frontShinyFemale: String = ""
) : Parcelable {
    fun getPokemonImageUri(): String {
        val uriArr = arrayOf(
            frontDefault,
            backDefault,
            backFemale,
            backShiny,
            backShinyFemale,
            frontFemale,
            frontShiny,
            frontShinyFemale
        )
        val nameList = mutableListOf<String>()
        for (uri in uriArr) {
            if (!uri.isBlank()) {
                nameList.add(uri)
            }
        }
        return if (nameList.size > 0) nameList[0] else ""
    }
}