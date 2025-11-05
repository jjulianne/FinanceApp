package com.example.financeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.financeApp.R
import com.example.financeapp.ui.theme.FinanceAppTheme

/**
 * Bottom Navigation Bar component con forma curva
 * Este componente se reutiliza en todas las pantallas principales
 */
@Composable
fun BottomNavBar(
    currentRoute: String,
    darkTheme: Boolean,
    onNavigateToHome: () -> Unit,
    onNavigateToAnalysis: () -> Unit,
    onNavigateToTransactions: () -> Unit,
    onNavigateToCategory: () -> Unit,
    onNavigateToProfile: () -> Unit
) {

    val backgroundColor = if (!darkTheme) {
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    // El Box contenedor principal.
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp)
            .clip(RoundedCornerShape(topStart = 70.dp, topEnd = 70.dp))
            .background(backgroundColor)
            .padding(
                top = 36.dp,
                start = 60.dp,
                end = 60.dp,
                bottom = 41.dp
            )
    ) {
        // El Row con SpaceBetween es correcto para esparcir los items
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Home
            NavBarItem(
                icon = R.drawable.home_nav,
                iconWidth = 25.dp,
                iconHeight = 31.dp,
                isSelected = currentRoute == "home_route",
                darkTheme = darkTheme,
                onClick = onNavigateToHome
            )

            // Analysis
            NavBarItem(
                icon = R.drawable.analysis_nav,
                iconWidth = 31.dp,
                iconHeight = 30.dp,
                isSelected = currentRoute == "analysis_route",
                darkTheme = darkTheme,
                onClick = onNavigateToAnalysis
            )

            // Transactions
            NavBarItem(
                icon = R.drawable.transaction_nav,
                iconWidth = 33.dp,
                iconHeight = 25.dp,
                isSelected = currentRoute == "transactions_route",
                darkTheme = darkTheme,
                onClick = onNavigateToTransactions
            )

            // Category
            NavBarItem(
                icon = R.drawable.category_nav,
                iconWidth = 27.dp,
                iconHeight = 23.dp,
                isSelected = currentRoute == "category_route",
                darkTheme = darkTheme,
                onClick = onNavigateToCategory
            )

            // Profile
            NavBarItem(
                icon = R.drawable.profile_nav,
                iconWidth = 22.dp,
                iconHeight = 27.dp,
                isSelected = currentRoute == "profile_route",
                darkTheme = darkTheme,
                onClick = onNavigateToProfile
            )
        }
    }
}

/**
 * Item individual de la barra de navegacion
 * Modificado para aceptar tamaños de icono variables
 */
@Composable
private fun NavBarItem(
    icon: Int,
    iconWidth: Dp,
    iconHeight: Dp,
    isSelected: Boolean,
    darkTheme: Boolean,
    onClick: () -> Unit
) {


    // No Seleccionado: Color "on" del fondo de la barra
    val unselectedTint = if (!darkTheme) {
        MaterialTheme.colorScheme.onSurfaceVariant // (Oscuro en modo claro)
    } else {
        MaterialTheme.colorScheme.onSurface // (Claro en modo oscuro)
    }

    // Seleccionado: Color del fondo de la barra (para contrastar con el verde)
    // (Esto será 'Cyprus' en ambos modos, según tu tema)
    val selectedTint = if (!darkTheme) {
        MaterialTheme.colorScheme.onSurfaceVariant // (Oscuro en modo claro)
    } else {
        MaterialTheme.colorScheme.surface // (Oscuro en modo oscuro)
    }

    // Asignamos el tinte correcto
    val iconTint = if (isSelected) selectedTint else unselectedTint

    // Box contenedor del item (el "reborde seleccionado")
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(57.dp, 53.dp) // Mantenemos el tamaño para el área de click
            .then(
                if (isSelected) {
                    Modifier
                        .clip(RoundedCornerShape(22.dp))
                        // Usamos el color primario (#00D09E) como fondo
                        .background(MaterialTheme.colorScheme.primary)
                } else {
                    Modifier
                }
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            }
    ) {
        // Icono
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(width = iconWidth, height = iconHeight),
            tint = iconTint
        )
    }
}


@Preview(name = "BottomBar Light (Profile)", showBackground = true, widthDp = 430)
@Composable
fun BottomNavBarLightPreview() {
    FinanceAppTheme(darkTheme = false) {
        BottomNavBar(
            currentRoute = "profile_route",
            darkTheme = false,
            onNavigateToHome = {},
            onNavigateToAnalysis = {},
            onNavigateToTransactions = {},
            onNavigateToCategory = {},
            onNavigateToProfile = {}
        )
    }
}

@Preview(name = "BottomBar Dark (Home)", showBackground = true, widthDp = 430)
@Composable
fun BottomNavBarDarkPreview() {
    FinanceAppTheme(darkTheme = true) {
        BottomNavBar(
            currentRoute = "home_route",
            darkTheme = true,
            onNavigateToHome = {},
            onNavigateToAnalysis = {},
            onNavigateToTransactions = {},
            onNavigateToCategory = {},
            onNavigateToProfile = {}
        )
    }
}
