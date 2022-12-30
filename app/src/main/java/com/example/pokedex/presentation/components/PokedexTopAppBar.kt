package com.example.pokedex.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.pokedex.presentation.navigation.NavigationDirection.NAVIGATE_UP

@Composable
fun PokedexTopAppBar(
    actionIcon: ImageVector = Icons.Filled.Menu,
    backgroundColor: Color = Color.Transparent,
    onNavigate: (String) -> Unit
) {
    val iconColor = if (backgroundColor == Color.Transparent) {
        LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
    } else {
        Color.White
    }
    TopAppBar(
        backgroundColor = backgroundColor,
        title = { },
        elevation = 0.dp,
        navigationIcon = {
            IconButton(
                onClick = { onNavigate(NAVIGATE_UP.path) }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = iconColor,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = actionIcon,
                    tint = iconColor,
                    contentDescription = null
                )
            }
        }
    )
}