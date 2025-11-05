package com.example.financeapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Paleta de colores CLARA
private val FinWiseLightColorScheme = lightColorScheme(
    primary = FinWiseLightPrimary,
    onPrimary = FinWiseLightOnPrimary,
    background = FinWiseLightBackground,
    onBackground = FinWiseLightOnBackground,
    surface = FinWiseLightSurface,
    onSurface = FinWiseLightOnSurface,
    surfaceVariant = FinWiseLightSurfaceVariant,
    onSurfaceVariant = FinWiseLightOnSurfaceVariant
)

// Paleta de colores OSCURA
private val FinWiseDarkColorScheme = darkColorScheme(
    primary = FinWiseDarkPrimary,
    onPrimary = FinWiseDarkOnPrimary,
    background = FinWiseDarkBackground,
    onBackground = FinWiseDarkOnBackground,
    surface = FinWiseDarkSurfaceVariant,
    onSurface = FinWiseDarkOnSurface,
    surfaceVariant = FinWiseDarkSurface,
    onSurfaceVariant = FinWiseDarkOnSurfaceVariant
)

@Composable
fun FinanceAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        FinWiseDarkColorScheme
    } else {
        FinWiseLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}