package com.example.pokedex.presentation.pokemoninfo.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.pokedex.R
import com.example.pokedex.core.theme.PokedexTheme
import com.example.pokedex.core.theme.Purple40
import com.example.pokedex.core.theme.Spacing
import com.example.pokedex.core.theme.Typography
import com.example.pokedex.core.theme.lightBlue
import com.example.pokedex.core.theme.lightTeal
import com.example.pokedex.domain.model.Pokemon
import com.example.pokedex.domain.model.entryNumber
import com.example.pokedex.domain.model.formattedFemalePercentage
import com.example.pokedex.domain.model.formattedHeight
import com.example.pokedex.domain.model.formattedMalePercentage
import com.example.pokedex.domain.model.formattedWeight
import com.example.pokedex.presentation.components.Loading
import com.example.pokedex.presentation.components.PokeballGradient
import com.example.pokedex.presentation.components.PokedexTopAppBar
import com.example.pokedex.presentation.components.PokemonTypeLabel
import com.example.pokedex.presentation.components.SquareGradient
import com.example.pokedex.presentation.pokemoninfo.viewmodel.PokemonViewModel
import com.example.pokedex.presentation.pokemoninfo.viewmodel.PokemonViewModel.UIState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun PokemonInfoScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonViewModel = hiltViewModel(),
    pokemonId: Int,
    onNavigate: (String) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val uiState = viewModel.uiState.collectAsState().value
    var color by remember { mutableStateOf(Color.Transparent) }

    LaunchedEffect(key1 = Unit) {
        viewModel.getPokemon(pokemonId)
    }

    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        scaffoldState = scaffoldState,
        topBar = {
            PokedexTopAppBar(
                actionIcon = Icons.Default.FavoriteBorder,
                backgroundColor = color,
                onNavigate = onNavigate
            )
        }
    ) { padding ->
        when (uiState) {
            is UIState.Error -> Unit
            is UIState.Idle -> Unit
            is UIState.Loaded -> {
                color = uiState.data.backgroundColor
                Content(
                    modifier = Modifier.padding(padding),
                    pokemon = uiState.data
                )
            }
            is UIState.Loading -> Loading(
                modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    pokemon: Pokemon
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .background(pokemon.backgroundColor)
            .padding(top = 32.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Spacing.HORIZONTAL_MARGIN)
        ) {
            Text(
                style = Typography.h1,
                color = Color.White,
                text = pokemon.name,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                style = MaterialTheme.typography.subtitle2,
                color = Color.White,
                text = pokemon.entryNumber(),
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Spacing.HORIZONTAL_MARGIN)
        ) {
            Row {
                for (type in pokemon.type) {
                    PokemonTypeLabel(
                        modifier = Modifier.padding(end = 8.dp),
                        text = type
                    )
                }
            }

            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                style = MaterialTheme.typography.subtitle2,
                color = Color.White,
                text = pokemon.genera,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp)
        ) {
            SquareGradient(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = (-10).dp, y = (-200).dp)
            )

            PokeballGradient(
                modifier = Modifier
                    .size(160.dp)
                    .align(Alignment.Center),
                alpha = 0.2f,
                color = Color.White
            )

            AsyncImage(
                modifier = Modifier
                    .size(180.dp)
                    .align(Alignment.Center),
                model = pokemon.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        PokemonInfoTabs(
            modifier = Modifier
                .fillMaxWidth(),
            pokemon = pokemon
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
private fun PokemonInfoTabs(
    modifier: Modifier = Modifier,
    pokemon: Pokemon
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0)
    val tabItems = stringArrayResource(id = R.array.pokemon_info_tabs)

    TabRow(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    topStart = Spacing.LARGE_ROUNDED_CORNER,
                    topEnd = Spacing.LARGE_ROUNDED_CORNER
                )
            ),
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        indicator = { tabs ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabs[pagerState.currentPage]),
                color = Purple40
            )
        }
    ) {
        tabItems.forEachIndexed { index, item ->
            Tab(
                modifier = Modifier
                    .height(80.dp),
                text = {
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        text = item,
                        style = MaterialTheme.typography.h5,
                        color = if (pagerState.currentPage == index) Color.Black else Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                },
                selected = index == pagerState.currentPage,
                onClick = {
                    coroutineScope.launch { pagerState.animateScrollToPage(index) }
                }
            )
        }
    }

    HorizontalPager(
        modifier = modifier,
        count = tabItems.size,
        state = pagerState
    ) {
        when (currentPage) {
            0 -> AboutTab(pokemon = pokemon)
            else -> WipTab()
        }
    }
}

@Composable
fun AboutTab(
    modifier: Modifier = Modifier,
    pokemon: Pokemon
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = Spacing.HORIZONTAL_MARGIN)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            text = pokemon.description,
            style = MaterialTheme.typography.body1
        )

        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 32.dp)
                .shadow(
                    elevation = 16.dp,
                    ambientColor = Color.Black,
                    spotColor = Color.Black
                ),
            shape = RoundedCornerShape(Spacing.ROUNDED_CORNER)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                        .align(alignment = Alignment.TopStart),
                    text = stringResource(id = R.string.pokemoninfo_height),
                    color = Color.Gray,
                    style = MaterialTheme.typography.subtitle2
                )

                Text(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(alignment = Alignment.TopCenter),
                    text = stringResource(id = R.string.pokemoninfo_weight),
                    color = Color.Gray,
                    style = MaterialTheme.typography.subtitle2
                )

                Text(
                    modifier = Modifier
                        .padding(bottom = 8.dp, start = 16.dp)
                        .align(alignment = Alignment.BottomStart),
                    text = pokemon.formattedHeight(),
                    style = MaterialTheme.typography.subtitle2
                )

                Text(
                    modifier = Modifier
                        .padding(start = 4.dp, bottom = 8.dp)
                        .align(alignment = Alignment.BottomCenter),
                    text = pokemon.formattedWeight(),
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }

        Text(
            modifier = Modifier
                .padding(top = 16.dp),
            text = stringResource(R.string.pokemoninfo_breeding),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = stringResource(R.string.pokemoninfo_gender),
                color = Color.Gray,
                style = MaterialTheme.typography.subtitle2
            )

            Image(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .size(16.dp),
                contentScale = ContentScale.Inside,
                painter = painterResource(id = R.drawable.male),
                contentDescription = null
            )

            Text(
                modifier = Modifier.padding(start = 8.dp, end = 16.dp),
                text = pokemon.formattedMalePercentage(),
                style = MaterialTheme.typography.subtitle2
            )

            Image(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(16.dp),
                contentScale = ContentScale.Inside,
                painter = painterResource(id = R.drawable.female),
                contentDescription = null
            )

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = pokemon.formattedFemalePercentage(),
                style = MaterialTheme.typography.subtitle2
            )
        }

        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier.padding(end = 16.dp, top = 8.dp),
                text = stringResource(R.string.pokemoninfo_eggs_groups),
                color = Color.Gray,
                style = MaterialTheme.typography.subtitle2
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = pokemon.eggGroups,
                style = MaterialTheme.typography.subtitle2
            )
        }

        Text(
            modifier = Modifier.padding(end = 16.dp, top = 24.dp),
            text = stringResource(R.string.pokemoninfo_location),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(lightBlue)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp),
                contentScale = ContentScale.Inside,
                painter = painterResource(id = R.drawable.map_icon),
                contentDescription = null
            )
        }

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.pokemoninfo_training),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier.padding(end = 16.dp, top = 8.dp),
                text = stringResource(R.string.pokemoninfo_base_exp),
                color = Color.Gray,
                style = MaterialTheme.typography.subtitle2
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = pokemon.baseExp.toString(),
                style = MaterialTheme.typography.subtitle2
            )
        }
        Box(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun WipTab(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "WIP"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonInfoScreenPreview() {
    PokedexTheme {
        Content(
            pokemon = Pokemon(
                id = 1,
                "Bulbasaur",
                "aaa",
                type = listOf("Grass", "Poison"),
                genera = "Seed Pokémon",
                description = "There is a plant seed on its back right from the day this POKéMON is born. The seed slowly grows larger.",
                backgroundColor = lightTeal
            )
        )
    }
}