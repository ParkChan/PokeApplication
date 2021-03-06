package com.poke.ui.map

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.poke.ui.main.model.PokemonModel

class MapViewModel @ViewModelInject constructor() : ViewModel() {

    private val _pokemonModel = MutableLiveData<PokemonModel>()
    val pokemonModel: LiveData<PokemonModel> get() = _pokemonModel

    fun setUpPokemonModel(model: PokemonModel) {
        _pokemonModel.value = model
    }
}