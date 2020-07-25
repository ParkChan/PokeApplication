package com.poke.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.poke.R
import com.poke.common.BaseFragmentDialog
import com.poke.databinding.DialogPokemonBinding
import com.poke.ui.main.model.PokemonModel
import com.poke.ui.map.MapsActivity

class PokemonDialog : BaseFragmentDialog<DialogPokemonBinding>(
    R.layout.dialog_pokemon
) {

    private lateinit var pokemonModel: PokemonModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindData()
    }

    private fun initBindData(){
        pokemonModel.let {
            binding.pokemonModel = it
        }
        binding.pokemonDialog = this
    }

    fun onClickConfirm(){
        dismiss()
    }

    fun onClickMap(pokemonModel: PokemonModel?){
        pokemonModel?.let{
            val intent = Intent(context, MapsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtras(bundleOf("pokemonModel" to pokemonModel))
            startActivity(intent)
        }
    }

    fun setData(pokemonModel: PokemonModel) {
        this.pokemonModel = pokemonModel
    }


}