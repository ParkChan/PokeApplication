package com.poke.di

import com.poke.data.datasource.PokemonDetailInfoDataSource
import com.poke.data.datasource.PokemonListDataSource
import com.poke.data.repository.PokemonDetailInfoRepository
import com.poke.data.repository.PokemonListRepository
import com.poke.network.api.DemoApi
import com.poke.network.api.PokeApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    class PokemonListRepositoryImpl @Inject constructor(
        demoApi: DemoApi
    ) : PokemonListDataSource(demoApi)

    class PokemonDetailInfoRepositoryImpl @Inject constructor(
        pokeApi: PokeApi
    ) : PokemonDetailInfoDataSource(pokeApi)

    @Binds
    abstract fun bindPokemonListRepository(
        pokemonListRepositoryImpl: PokemonListRepositoryImpl
    ): PokemonListRepository.PokemonListRepositoryComponent


    @Binds
    abstract fun bindPokemonDetailInfoRepository(
        pokemonDetailInfoRepositoryImpl: PokemonDetailInfoRepositoryImpl
    ): PokemonDetailInfoRepository.PokemonDetailInfoRepositoryComponent

}

