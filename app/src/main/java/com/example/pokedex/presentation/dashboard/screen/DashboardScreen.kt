package com.example.pokedex.presentation.dashboard.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedex.R
import com.example.pokedex.core.theme.PokedexTheme
import com.example.pokedex.core.theme.Spacing
import com.example.pokedex.presentation.components.DashboardItem
import com.example.pokedex.presentation.components.PokeballGradient
import com.example.pokedex.presentation.components.SearchTextField
import com.example.pokedex.presentation.dashboard.model.DashboardEntry

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        scaffoldState = scaffoldState
    ) { padding ->
        Box(
            Modifier.fillMaxWidth()
        ) {
            PokeballGradient(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 80.dp, y = (-125).dp)
            )

            Column(
                Modifier
                    .padding(padding)
                    .padding(horizontal = Spacing.HORIZONTAL_MARGIN)
                    .padding(top = 16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 32.dp),
                    style = MaterialTheme.typography.h1,
                    text = stringResource(R.string.dashboard_title),
                    fontWeight = FontWeight.Bold
                )

                SearchTextField(
                    modifier = Modifier.padding(top = 32.dp),
                    placeholderText = stringResource(R.string.dashboard_search_placeholder),
                    onValueChange = {}
                )

                LazyVerticalGrid(
                    modifier = Modifier.padding(top = 32.dp),
                    columns = GridCells.Adaptive(minSize = 128.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    val entries = DashboardEntry.values()
                    items(entries.size) { index ->
                        DashboardItem(entry = entries[index], onNavigate = onNavigate)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    PokedexTheme {
        DashboardScreen(Modifier) {}
    }
}