package com.example.pokedex.presentation.pokemonlist.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedex.R
import com.example.pokedex.core.theme.PokedexTheme
import com.example.pokedex.core.theme.Spacing
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.presentation.components.Loading
import com.example.pokedex.presentation.components.PokeballGradient
import com.example.pokedex.presentation.components.PokedexTopAppBar
import com.example.pokedex.presentation.components.PokemonGridItem
import com.example.pokedex.presentation.pokemonlist.viewmodel.PokemonListViewModel
import com.example.pokedex.presentation.pokemonlist.viewmodel.PokemonListViewModel.UIState

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        scaffoldState = scaffoldState,
        topBar = { PokedexTopAppBar(onNavigate = onNavigate) }
    ) { padding ->

        when (uiState) {
            is UIState.Error -> Unit
            is UIState.Idle -> Unit
            is UIState.Loaded -> Content(
                modifier = Modifier.padding(padding),
                pokemonList = uiState.data,
                onNavigate = onNavigate
            )
            is UIState.Loading -> Loading(
                modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    pokemonList: List<Pokemon>,
    onNavigate: (String) -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        PokeballGradient(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 90.dp, y = (-135).dp)
        )

        Column(
            Modifier
                .fillMaxWidth()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    horizontal = Spacing.HORIZONTAL_MARGIN,
                    vertical = 16.dp
                )
            ) {
                item(span = { GridItemSpan(2) }) {
                    Text(
                        modifier = Modifier
                            .padding(top = 40.dp),
                        style = MaterialTheme.typography.h1,
                        text = stringResource(R.string.pokemonlist_title),
                        fontWeight = FontWeight.Bold
                    )
                }

                items(pokemonList.size) { index ->
                    PokemonGridItem(
                        pokemon = pokemonList[index],
                        onNavigate = onNavigate
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonListScreenPreview() {
    PokedexTheme {
        PokemonListScreen(Modifier) {}
    }
}

