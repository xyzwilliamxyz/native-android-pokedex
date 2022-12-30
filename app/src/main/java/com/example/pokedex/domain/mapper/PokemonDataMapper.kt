package com.example.pokedex.domain.mapper

import com.example.pokedex.BuildConfig.BASE_API_URL
import com.example.pokedex.data.model.PokemonLocalData
import com.example.pokedex.data.remote.model.response.PokemonListResponse
import com.example.pokedex.data.remote.model.response.PokemonResponse
import com.example.pokedex.data.remote.model.response.PokemonSpeciesResponse
import com.example.pokedex.domain.model.Pokemon
import javax.inject.Inject

class PokemonDataMapper @Inject constructor() {

    fun fromResponse(response: PokemonListResponse): List<Pokemon> {
        return response.results.map { pokemon ->
            val pokemonId = pokemon.url.replace(POKEMON_URL, "").replace("/", "").toInt()
            Pokemon(
                id = pokemonId,
                name = pokemon.name.replaceFirstChar { it.uppercase() },
                imageUrl = POKEMON_IMAGE.format(pokemonId),
                backgroundColor = PokemonLocalData.getBackgroundColor(pokemon.name)
            )
        }
    }

    fun fromResponse(pokemonResponse: PokemonResponse, speciesResponse: PokemonSpeciesResponse): Pokemon {
        return Pokemon(
            id = pokemonResponse.id,
            name = pokemonResponse.name.replaceFirstChar { it.uppercase() },
            imageUrl = POKEMON_IMAGE.format(pokemonResponse.id),
            backgroundColor = PokemonLocalData.getBackgroundColor(pokemonResponse.name),
            weight = pokemonResponse.weight * WEIGHT_HECTOGRAM,
            height = pokemonResponse.height * HEIGHT_DECIMETER,
            description = speciesResponse.flavorTextEntries.last { it.language.name == LANGUAGE_EN }.flavorText.replace("\n", ""),
            femalePercentage = speciesResponse.genderRate / GENDER_DENOMINATOR  * 100,
            malePercentage = (1.0 - (speciesResponse.genderRate / GENDER_DENOMINATOR)) * 100,
            type = pokemonResponse.types.map { it.type.name.replaceFirstChar { char -> char.uppercase() } },
            eggGroups = speciesResponse.eggGroups.joinToString(", ") { it.name.replaceFirstChar { char -> char.uppercase() } },
            genera = speciesResponse.genera.find { it.language.name == LANGUAGE_EN }?.genus ?: "",
            baseExp = pokemonResponse.baseExperience
        )
    }

    companion object {
        private const val GENDER_DENOMINATOR = 8.0
        private const val WEIGHT_HECTOGRAM = 0.1
        private const val HEIGHT_DECIMETER = 0.1
        private const val LANGUAGE_EN = "en"
        private const val POKEMON_URL = "${BASE_API_URL}pokemon/"
        private const val POKEMON_IMAGE = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/%s.png"
    }
}