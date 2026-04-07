package com.jucode.nutrisport

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

enum class ThemeMode {
    LIGHT, DARK, SYSTEM
}

// Simple singleton for demo purposes. In a real app, use DataStore or similar.
object ThemeSettings {
    var themeMode by mutableStateOf(ThemeMode.SYSTEM)
}

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFB300), // Warm Amber
    secondary = Color(0xFFFF8F00), // Deep Amber
    tertiary = Color(0xFFFF7043), // Warm Coral/Deep Orange
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFF57C00), // Orange
    secondary = Color(0xFFFFB300), // Amber
    tertiary = Color(0xFFE64A19), // Deep Orange
    background = Color(0xFFFFFBF0), // Warm Off-White
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun NutriSportTheme(
    content: @Composable () -> Unit
) {
    val darkTheme = when (ThemeSettings.themeMode) {
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
    }

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
