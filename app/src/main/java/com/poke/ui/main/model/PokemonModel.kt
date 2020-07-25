package com.poke.ui.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonModel(
    val id: Int = 0,
    val names: List<String> = emptyList(),
    val locationList: MutableList<PokemonLocationModel> = mutableListOf(),
    var filterName: String = "",
    var detailInfo: PokemonDetailInfoModel = PokemonDetailInfoModel()
) : Parcelable {
    fun isLocation(): Boolean = locationList.isNotEmpty()
    fun getAllName(): String = names.joinToString(
        separator = "/"
    )
}
