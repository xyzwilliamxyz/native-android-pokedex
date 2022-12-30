package com.example.pokedex.data.remote.model.response

import com.squareup.moshi.Json

data class PokemonListResponse(
    @Json(name = "count")
    val count: Int,

    @Json(name = "results")
    val results: List<ResultsItem>
) {
    data class ResultsItem(
        @Json(name = "name")
        val name: String,

        @Json(name = "url")
        val url: String
    )
}
