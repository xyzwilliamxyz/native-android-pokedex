package com.example.pokedex.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedex.R
import com.example.pokedex.core.theme.PokedexTheme
import com.example.pokedex.core.theme.Spacing
import com.example.pokedex.presentation.dashboard.model.DashboardEntry

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardItem(
    modifier: Modifier = Modifier,
    entry: DashboardEntry,
    onNavigate: (String) -> Unit
) {
    Card(
        modifier = modifier
            .shadow(
                elevation = 32.dp,
                ambientColor = entry.color,
                spotColor = entry.color
            ),
        shape = RoundedCornerShape(Spacing.ROUNDED_CORNER),
        onClick = { onNavigate(entry.direction.path) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(entry.color)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .offset(x = (-15).dp, y = (-25).dp)
                    .alpha(0.2f)
                    .clip(CircleShape)
                    .background(Color.White)
                    .align(Alignment.CenterStart)

            )
            Image(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(x = 15.dp)
                    .size(90.dp)
                ,
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.pokeball),
                colorFilter = ColorFilter.tint(Color.White),
                alpha = 0.2f,
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterStart),
                text = entry.title,
                style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokedexTheme {
        DashboardItem(
            Modifier.padding(32.dp),
            DashboardEntry.POKEDEX,
            onNavigate = {}
        )
    }
}