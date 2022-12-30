package com.example.pokedex.data.remote.model.response

import com.squareup.moshi.Json

data class PokemonResponse(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "types")
    val types: List<TypesItem>,

    @Json(name = "stats")
    val stats: List<StatsItem>,

    @Json(name = "base_experience")
    val baseExperience: Int,

    @Json(name = "weight")
    val weight: Int,

    @Json(name = "height")
    val height: Int
) {
    data class TypesItem(
        @Json(name = "slot")
        val slot: Int,

        @Json(name = "type")
        val type: Type
    )

    data class Stat(
        @Json(name = "name")
        val name: String,

        @Json(name = "url")
        val url: String
    )

    data class Type(
        @Json(name = "name")
        val name: String,

        @Json(name = "url")
        val url: String
    )

    data class StatsItem(
        @Json(name = "stat")
        val stat: Stat,

        @Json(name = "base_stat")
        val baseStat: Int,

        @Json(name = "effort")
        val effort: Int
    )
}
