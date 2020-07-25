package com.poke.ui.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonDetailInfoModel(
    val sprites: SpritesModel = SpritesModel(),
    val height: Int = 0,
    val weight: Int = 0
): Parcelable