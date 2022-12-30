package com.example.pokedex.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedex.R
import com.example.pokedex.core.theme.PokedexTheme
import com.example.pokedex.core.theme.Spacing
import com.example.pokedex.core.theme.lightBlue
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.model.entryNumber
import com.example.pokedex.presentation.navigation.NavigationDirection
import com.example.pokedex.presentation.navigation.getPathWithArgs

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokemonGridItem(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onNavigate: (String) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(Spacing.ROUNDED_CORNER),
        onClick = {
            onNavigate(
                NavigationDirection.POKEMON_INFO.getPathWithArgs(
                    listOf(pokemon.id.toString())
                )
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(pokemon.backgroundColor)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(x = 15.dp, y = 30.dp)
                    .size(90.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.pokeball),
                colorFilter = ColorFilter.tint(Color.White),
                alpha = 0.2f,
                contentDescription = null
            )

            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(22.dp)
                    .width(110.dp)
                    .alpha(0.2f)
                    .clip(RectangleShape)
                    .background(Color.White)
                    .align(Alignment.TopStart)
            )

            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(10.dp)
                    .width(60.dp)
                    .offset(y = 25.dp)
                    .alpha(0.2f)
                    .clip(RectangleShape)
                    .background(Color.White)
                    .align(Alignment.TopStart)
            )

            Column {
                Text(
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White,
                    text = pokemon.name,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 2.dp),
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.White.copy(alpha = 0.5f),
                    text = pokemon.entryNumber()
                )
            }

            AsyncImage(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(90.dp),
                model = pokemon.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonGridItemPreview() {
    PokedexTheme {
        PokemonGridItem(
            Modifier
                .padding(32.dp)
                .width(180.dp),
            Pokemon(
                id = 1,
                name = "Bulbasaur",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                backgroundColor = lightBlue
            ),
            onNavigate = {}
        )
    }
}