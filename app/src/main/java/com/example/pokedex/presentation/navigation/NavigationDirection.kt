package com.example.pokedex.presentation.navigation

enum class NavigationDirection(
    val path: String
) {
    DASHBOARD("dashboard"),
    POKEMON_LIST("pokemonList"),
    POKEMON_INFO("pokemonList/{pokemonId}"),
    NAVIGATE_UP("navigateUp")
}

fun NavigationDirection.getPathWithArgs(args: List<String>): String {
    var result = path
    val parameters = "\\{(.*?)\\}".toRegex().findAll(path)

    parameters.forEachIndexed { index, parameter ->
        result = result.replace(parameter.value, args[index])
    }

    return result
}