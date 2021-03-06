package com.poke.ui.dialog

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.poke.R
import com.poke.common.BaseFragmentDialog
import com.poke.common.component.BindViewModelComponent
import com.poke.common.key.BundleKey
import com.poke.databinding.DialogPokemonBinding
import com.poke.ui.main.model.PokemonModel
import com.poke.ui.map.MapsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDialog : BaseFragmentDialog<DialogPokemonBinding>(
    R.layout.dialog_pokemon
), BindViewModelComponent {

    private val viewModel by viewModels<PokemonDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        setupObserve()
    }

    fun onClickConfirm() {
        dismiss()
    }

    fun onClickMap(pokemonModel: PokemonModel?) {
        pokemonModel?.let {
            val intent = Intent(context, MapsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtras(bundleOf(BundleKey.BUNDLE_POKEMON_DATA_KEY to pokemonModel))
            startActivity(intent)
        }
    }

    override fun bindViewModel() {
        binding.vm = viewModel
        val data =
            arguments?.get(BundleKey.BUNDLE_POKEMON_DATA_KEY) as? PokemonModel ?: PokemonModel()
        binding.pokemonDialog = this

        viewModel.getPokemonDetailInfo(data)
    }

    override fun setupObserve() {
        viewModel.errMsg.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

    }
}