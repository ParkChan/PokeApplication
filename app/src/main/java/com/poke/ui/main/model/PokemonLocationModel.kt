package com.poke.ui.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonLocationModel(
    val id: Int = 0,
    val lat: Double = 0.0,
    val lng: Double = 0.0
): Parcelable
