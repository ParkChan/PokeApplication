package com.poke.ui.main

import android.os.Bundle
import android.util.ArrayMap
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import com.poke.BR
import com.poke.R
import com.poke.common.BaseActivity
import com.poke.common.adapter.BaseRecyclerAdapter
import com.poke.common.adapter.ViewHolderIdData
import com.poke.common.component.ViewModelComponent
import com.poke.databinding.ActivityMainBinding
import com.poke.ui.main.model.PokemonModel
import com.poke.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
), ViewModelComponent {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViewModel()
        setupObserve()
        initAdapter()
        mainViewModel.getPokemonList()
    }

    override fun bindViewModel() {
        binding.mainViewModel = mainViewModel
    }

    override fun setupObserve() {
        mainViewModel.errMsg.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initAdapter() {
        val adapterViewModels = ArrayMap<Int, ViewModel>().apply {
            this[BR.mainViewModel] = mainViewModel
        }

        val viewHolderIdData =
            ViewHolderIdData(R.layout.item_pokemon, BR.pokemonModel, BR.itemPosition)

        val pokemonAdapter = BaseRecyclerAdapter<PokemonModel>(
            viewHolderIdData = viewHolderIdData,
            viewModels = adapterViewModels
        )

        binding.rvList.adapter = pokemonAdapter
        //add dividers
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.rvList.addItemDecoration(decoration)
    }
}