package com.poke.ui.main

import android.os.Bundle
import android.view.View
import com.poke.R
import com.poke.common.BaseFragmentDialog
import com.poke.databinding.DialogPokemonBinding
import com.poke.ui.main.model.PokemonModel

class PokemonDialog : BaseFragmentDialog<DialogPokemonBinding>(
    R.layout.dialog_pokemon
) {

    lateinit var pokemonModel: PokemonModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonModel.let {
            binding.pokemonModel = it
        }
    }

    fun setData(pokemonModel: PokemonModel) {
        this.pokemonModel = pokemonModel
    }
}