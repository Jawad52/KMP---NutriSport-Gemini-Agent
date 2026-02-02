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
    primary = Color(0xFF00E5FF), // Electric Blue
    secondary = Color(0xFF00FFD1), // Neon Mint
    tertiary = Color(0xFFBB86FC),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF00B8D4), // Darker Electric Blue for contrast
    secondary = Color(0xFF00BFA5), // Darker Neon Mint for contrast
    tertiary = Color(0xFF6200EE),
    background = Color(0xFFF5F5F5),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
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
