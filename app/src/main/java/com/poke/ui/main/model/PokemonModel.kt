package com.poke.ui.main.model

data class PokemonModel(
    private val id: Int = 0,
    private val names: List<String> = emptyList(),
    private val lat: Double = 0.0,
    private val lon: Double = 0.0,
    private val filterName: String = ""
) {
    fun isLocation(): Boolean = lat > 0 && lon > 0
    fun getName(): String = names[0]
}
