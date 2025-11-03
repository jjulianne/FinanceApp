package com.example.financeapp.core

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

/**
 * Extension function para hacer un Composable clickeable
 * sin mostrar el efecto 'ripple'.
 */
@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = this.then(
    Modifier.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
)

// Aca se pueden sumar futuras funciones