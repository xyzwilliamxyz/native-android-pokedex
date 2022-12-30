package com.example.pokedex.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    placeholderText: String = "",
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(CircleShape),
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        placeholder = {
            Text(
                text = placeholderText,
                style = MaterialTheme.typography.subtitle1
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                tint = Color.Black,
                contentDescription = null
            )
        },
        textStyle = MaterialTheme.typography.subtitle1
    )
}