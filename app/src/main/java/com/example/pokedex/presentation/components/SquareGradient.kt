package com.example.pokedex.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedex.core.theme.PokedexTheme

@Composable
fun SquareGradient(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(110.dp)
            .width(110.dp)
            .rotate(-150f)
            .alpha(0.1f)
            .clip(RectangleShape)
            .background(Color.White)
    )
}

@Preview(showBackground = true)
@Composable
fun SquareGradientPreview() {
    PokedexTheme {
        Box(
            modifier = Modifier
                .size(200.dp)
        ) {
            SquareGradient(Modifier)
        }
    }
}