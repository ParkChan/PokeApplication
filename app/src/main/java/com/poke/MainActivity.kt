package com.poke

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.poke.common.BaseActivity
import com.poke.databinding.ActivityMainBinding
import com.poke.network.DemoApi
import com.poke.network.PokeApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    @Inject
    lateinit var demoApi: DemoApi

    @Inject
    lateinit var pokeapi: PokeApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch() {
            demoApi.getPokemonListAsync()
            demoApi.getPokemonLocationAsync()
            pokeapi.getPokemonDetailInfoAsync(1)
        }

    }
}