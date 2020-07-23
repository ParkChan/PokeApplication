package com.poke.ui.main.model

data class PokemonModel(
    val id: Int = 0,
    val names: List<String> = emptyList(),
    var lat: Double = 0.0,
    var lon: Double = 0.0,
    var filterName: String = ""
) {
    fun isLocation(): Boolean = lat > 0 && lon > 0
    fun getName(): String = names[0]
}
