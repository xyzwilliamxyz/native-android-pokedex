package com.example.pokedex.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedex.core.theme.PokedexTheme
import com.example.pokedex.core.theme.lightCyan

@Composable
fun PokemonTypeLabel(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .background(Color.White.copy(alpha = 0.2f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp),
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.subtitle2,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonTypeLabelPreview() {
    PokedexTheme {
        Box(
            modifier = Modifier
                .background(color = lightCyan)
                .padding(16.dp)
        ) {
            PokemonTypeLabel(
                text = "Water"
            )
        }
    }
}