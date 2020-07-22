package com.poke.domain.entitiy

import com.poke.network.DemoApi
import javax.inject.Inject

class PokemonListDataSource @Inject constructor(
    private val demoApi: DemoApi
) {
    
}