package com.example.pokedex.di

import com.example.pokedex.data.remote.repository.PokemonRepository
import com.example.pokedex.domain.interactor.PokemonUseCases
import com.example.pokedex.domain.mapper.PokemonDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideBaseUrl(dataMapper: PokemonDataMapper, pokemonRepository: PokemonRepository) =
        PokemonUseCases(
            dataMapper = dataMapper,
            pokemonRepository = pokemonRepository
        )
}