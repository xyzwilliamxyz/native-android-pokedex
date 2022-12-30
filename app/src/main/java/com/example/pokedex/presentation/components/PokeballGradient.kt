package com.example.pokedex.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokedex.R

@Composable
fun PokeballGradient(
    modifier: Modifier = Modifier,
    alpha: Float = 0.5f,
    color: Color = Color.LightGray
) {
    Image(
        modifier = modifier.size(250.dp),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = R.drawable.pokeball),
        colorFilter = ColorFilter.tint(color),
        alpha = alpha,
        contentDescription = null
    )
}