package com.example.pokedex.data.remote.repository

import com.example.pokedex.data.executor.SourceExecutor
import com.example.pokedex.data.executor.SourceResult
import com.example.pokedex.data.remote.api.PokemonApi
import com.example.pokedex.data.remote.model.response.PokemonListResponse
import com.example.pokedex.data.remote.model.response.PokemonResponse
import com.example.pokedex.data.remote.model.response.PokemonSpeciesResponse
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val sourceExecutor: SourceExecutor,
    private val pokemonApi: PokemonApi
) {
    suspend fun getPokemonList(): SourceResult<PokemonListResponse> {
        return sourceExecutor.execute { pokemonApi.getPokemonList() }
    }

    suspend fun getPokemon(pokemonId: Int): SourceResult<PokemonResponse> {
        return sourceExecutor.execute { pokemonApi.getPokemon(pokemonId) }
    }

    suspend fun getPokemonSpecies(pokemonId: Int): SourceResult<PokemonSpeciesResponse> {
        return sourceExecutor.execute { pokemonApi.getPokemonSpecies(pokemonId) }
    }
}