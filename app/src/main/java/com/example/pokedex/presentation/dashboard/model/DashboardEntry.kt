package com.example.pokedex.presentation.dashboard.model

import androidx.compose.ui.graphics.Color
import com.example.pokedex.core.theme.lightBlue
import com.example.pokedex.core.theme.lightBrown
import com.example.pokedex.core.theme.lightPurple
import com.example.pokedex.core.theme.lightRed
import com.example.pokedex.core.theme.lightTeal
import com.example.pokedex.core.theme.lightYellow
import com.example.pokedex.presentation.navigation.NavigationDirection

enum class DashboardEntry(
    val title: String,
    val color: Color,
    val direction: NavigationDirection
) {
    POKEDEX("Pokedex", lightTeal, NavigationDirection.POKEMON_LIST),
    MOVES("Moves", lightRed, NavigationDirection.POKEMON_LIST),
    ABILITIES("Abilities", lightBlue, NavigationDirection.POKEMON_LIST),
    ITEMS("Items", lightYellow, NavigationDirection.POKEMON_LIST),
    LOCATIONS("Locations", lightPurple, NavigationDirection.POKEMON_LIST),
    TYPE_CHARTS("Type Charts", lightBrown, NavigationDirection.POKEMON_LIST);
}