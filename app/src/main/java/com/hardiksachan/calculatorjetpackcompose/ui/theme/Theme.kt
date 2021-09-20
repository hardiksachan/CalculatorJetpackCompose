package com.hardiksachan.calculatorjetpackcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Black,
    primaryVariant = BlackDark,
    secondary = White,
    secondaryVariant = WhiteLight,
    onPrimary = BlackText,
    onSecondary = WhiteText
)

private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = WhiteLight,
    secondary = Black,
    secondaryVariant = BlackDark,
    onPrimary = WhiteText,
    onSecondary = BlackText
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}