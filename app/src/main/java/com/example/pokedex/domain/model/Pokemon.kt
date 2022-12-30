package com.example.pokedex.domain.model

import androidx.compose.ui.graphics.Color

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val weight: Double = 0.0,
    val height: Double = 0.0,
    val description: String = "",
    val femalePercentage: Double = 0.0,
    val malePercentage: Double = 0.0,
    val type: List<String> = listOf(),
    val eggGroups: String = "",
    val baseExp: Int = 0,
    val genera: String = "",
    val backgroundColor: Color = Color.Gray
)

fun Pokemon.entryNumber() = "#" + id.toString().padStart(MAX_ENTRY_DIGITS, '0')

fun Pokemon.formattedHeight() = "%s' %s\"".format(height.toInt(), height.toString().split(".")[1].take(2))

fun Pokemon.formattedWeight() = "%.1f lbs".format(weight)

fun Pokemon.formattedMalePercentage() = "%.1f%%".format(malePercentage)

fun Pokemon.formattedFemalePercentage() = "%.1f%%".format(femalePercentage)

private const val MAX_ENTRY_DIGITS = 3