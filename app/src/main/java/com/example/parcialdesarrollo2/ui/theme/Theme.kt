package com.example.parcialdesarrollo2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Green,
    onPrimary = White,
    secondary = DarkGreen,
    onSecondary = White,
    background = White,
    onBackground = DarkGray,
    surface = LightGray,
    onSurface = DarkGray,
    error = ErrorRed,
    onError = White
)

@Composable
fun ParcialDesarrollo2Theme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
