package com.poke.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.ArrayMap
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.poke.BR
import com.poke.R
import com.poke.common.BaseActivity
import com.poke.common.adapter.BaseRecyclerAdapter
import com.poke.common.adapter.ViewHolderIdData
import com.poke.common.component.BindViewModelComponent
import com.poke.common.key.BundleKey
import com.poke.databinding.ActivityMainBinding
import com.poke.ui.dialog.PokemonDialog
import com.poke.ui.main.model.PokemonModel
import com.poke.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
), BindViewModelComponent {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViewModel()
        setupObserve()
        initAdapter()
        initListener()
        initPokemonData()
    }

    override fun bindViewModel() {
        binding.vm = viewModel
    }

    override fun setupObserve() {
        viewModel.selectedItem.observe(
            this,
            Observer { pokemonModel ->
                val fragment =
                    supportFragmentManager.findFragmentByTag(getString(R.string.dialog_pokemon_tag))
                fragment ?: let {
                    val pokemonDialog = PokemonDialog()
                    pokemonDialog.arguments =
                        bundleOf(BundleKey.BUNDLE_POKEMON_DATA_KEY to pokemonModel)
                    pokemonDialog.show(
                        supportFragmentManager,
                        getString(R.string.dialog_pokemon_tag)
                    )
                }
            }
        )

        viewModel.errMsg.observe(this, Observer
        {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initAdapter() {
        val adapterViewModels = ArrayMap<Int, ViewModel>().apply {
            this[BR.vm] = viewModel
        }

        val viewHolderIdData =
            ViewHolderIdData(R.layout.item_pokemon, BR.pokemon_model, BR.position)

        val pokemonAdapter = BaseRecyclerAdapter<PokemonModel>(
            viewHolderIdData = viewHolderIdData,
            viewModels = adapterViewModels
        )

        binding.rvList.adapter = pokemonAdapter
        //add dividers
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.rvList.addItemDecoration(decoration)
    }

    private fun initListener() {
        binding.etInput.addTextChangedListener(object : TextWatcher {
            private var searchFor = ""
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val searchText = s.toString().trim()
                if (searchText == searchFor)
                    return
                searchFor = searchText
                lifecycleScope.launch {
                    delay(300)
                    if (searchText != searchFor)
                        return@launch
                    viewModel.searchPokemon(searchFor)
                }
            }
        })
    }

    private fun initPokemonData() {
        viewModel.initData()
    }

}