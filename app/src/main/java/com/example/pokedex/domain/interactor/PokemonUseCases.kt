package com.example.pokedex.domain.interactor

import com.example.pokedex.data.remote.repository.PokemonRepository
import com.example.pokedex.domain.mapper.PokemonDataMapper
import com.example.pokedex.domain.model.Pokemon

class PokemonUseCases constructor(
    private val dataMapper: PokemonDataMapper,
    private val pokemonRepository: PokemonRepository
) {
    suspend fun getPokemonList(): List<Pokemon> {
        val result = pokemonRepository.getPokemonList().getBodyOrThrow()
        return dataMapper.fromResponse(result)
    }

    suspend fun getPokemon(pokemonId: Int): Pokemon {
        val pokemonResult = pokemonRepository.getPokemon(pokemonId).getBodyOrThrow()
        val speciesResult = pokemonRepository.getPokemonSpecies(pokemonId).getBodyOrThrow()
        return dataMapper.fromResponse(
            pokemonResult, speciesResult
        )
    }
}