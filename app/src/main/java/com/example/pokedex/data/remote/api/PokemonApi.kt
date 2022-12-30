package com.example.pokedex.data.remote.api

import com.example.pokedex.data.remote.model.response.PokemonListResponse
import com.example.pokedex.data.remote.model.response.PokemonResponse
import com.example.pokedex.data.remote.model.response.PokemonSpeciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon?limit=20")
    suspend fun getPokemonList(): Response<PokemonListResponse>

    @GET("pokemon/{pokemonId}")
    suspend fun getPokemon(
        @Path("pokemonId") pokemonId: Int
    ): Response<PokemonResponse>

    @GET("pokemon-species/{pokemonId}")
    suspend fun getPokemonSpecies(
        @Path("pokemonId") pokemonId: Int
    ): Response<PokemonSpeciesResponse>
}