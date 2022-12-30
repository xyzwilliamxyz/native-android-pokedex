package com.example.pokedex.data.model

import androidx.compose.ui.graphics.Color
import com.example.pokedex.core.theme.lightBlue
import com.example.pokedex.core.theme.lightTeal
import com.example.pokedex.core.theme.lightRed

/**
 * Complements data that's not provided by poke api.
 */
enum class PokemonLocalData(
    val pokemon: String,
    val backgroundColor: Color
) {
    BULBASAUR("bulbasaur", lightTeal),
    IVYSAUR("ivysaur", lightTeal),
    VENUSAUR("venusaur", lightTeal),
    CHARMANDER("charmander", lightRed),
    CHARMELEON("charmeleon", lightRed),
    CHARIZARD("charizard", lightRed),
    SQUIRTLE("squirtle", lightBlue),
    WARTORTLE("wartortle", lightBlue),
    BLASTOISE("blastoise", lightBlue),
    CATERPIE("caterpie", lightTeal),
    METAPOD("metapod", lightTeal),
    BUTTERFREE("butterfree", lightTeal),
    WEEDLE("weedle", lightTeal),
    KAKUNA("kakuna", lightTeal),
    BEEDRILL("beedrill", lightTeal),
    PIDGEY("pidgey", Color.Gray),
    PIDGEOTTO("pidgeotto", Color.Gray),
    PIDGEOT("pidgeot", Color.Gray),
    RATTATA("rattata", Color.Gray),
    RATICATE("raticate", Color.Gray);

    companion object {
        fun getBackgroundColor(pokemon: String): Color {
            return values().firstOrNull { it.pokemon == pokemon }?.backgroundColor ?: Color.Gray
        }
    }
}