package com.example.pokedex.core.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.pokedex.R

private val fontFamily = FontFamily(Font(R.font.circularstd_book))

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        color = Color.Black,
        lineHeight = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = fontFamily,
        fontSize = 16.sp,
        color = Color.Black
    ),
    subtitle2 = TextStyle(
        fontFamily = fontFamily,
        fontSize = 12.sp,
        color = Color.Black
    ),
    h1 = TextStyle(
        fontFamily = fontFamily,
        fontSize = 32.sp,
        color = Color.Black
    ),
    h5 = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        color = Color.Black
    )
)