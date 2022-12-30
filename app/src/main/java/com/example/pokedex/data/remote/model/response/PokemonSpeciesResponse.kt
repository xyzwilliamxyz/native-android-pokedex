package com.example.pokedex.data.remote.model.response

import com.squareup.moshi.Json

data class PokemonSpeciesResponse(
    @Json(name = "gender_rate")
    val genderRate: Int,

    @Json(name = "flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntriesItem>,

    @Json(name = "genera")
    val genera: List<Genera>,

    @Json(name = "egg_groups")
    val eggGroups: List<EggGroupsItem>,
) {
    data class Version(
        @Json(name = "name")
        val name: String,

        @Json(name = "url")
        val url: String
    )

    data class Language(
        @Json(name = "name")
        val name: String,

        @Json(name = "url")
        val url: String
    )

    data class FlavorTextEntriesItem(
        @Json(name = "language")
        val language: Language,

        @Json(name = "version")
        val version: Version,

        @Json(name = "flavor_text")
        val flavorText: String
    )

    data class Genera(
        @Json(name = "genus")
        val genus: String,

        @Json(name = "language")
        val language: Language
    )

    data class EggGroupsItem(
        @Json(name = "name")
        val name: String,

        @Json(name = "url")
        val url: String
    )
}
