package com.example.financeapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.financeapp.navigation.BottomNavItem
import com.example.financeApp.R
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.financeapp.ui.theme.*
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.clip


@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Stats, // Segundo icono: Stats/Análisis
        BottomNavItem.Transfer, // Tercer icono: Transfer/Transacciones
        BottomNavItem.Wallet,
        BottomNavItem.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoutes = items.map { it.route }

    // Rutas del grupo Transacciones (Botón central)
    val transferRoutes = listOf(
        BottomNavItem.Transfer.route, // "transfer_route"
        "income_route",
        "expense_route"
    )

    // Rutas que activan el botón Stats (Ahora solo home_route)
    val statsRoutes = listOf(
        BottomNavItem.Home.route, // La pantalla de Balance/Home
        BottomNavItem.Stats.route // La propia ruta Stats, si existiera
    )

    val transferTargetRoute = BottomNavItem.Transfer.route // "transfer_route"
    // Nuevo Target: El clic en Stats debe ir a Home/Balance
    val statsTargetRoute = BottomNavItem.Home.route

    // --- FUNCIÓN DE NAVEGACIÓN AUXILIAR DEFINIDA AQUÍ ---
    val defaultNavigation = { route: String ->
        navController.navigate(route) {
            // popUpTo DEBE usar la primera ruta de la barra (home_route)
            popUpTo(bottomBarRoutes.first()) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    // ----------------------------------------------------


    Surface(
        color = FinWiseLightGreen, // Fondo verde claro de la barra (Honeydew)
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp) // Solo esquinas superiores redondeadas
    ) {
        NavigationBar(
            containerColor = Color.Transparent, // El color lo da el Surface padre
            tonalElevation = 0.dp,
            modifier = Modifier.height(72.dp)
        ) {
            items.forEach { item ->

                // --- LÓGICA DE ACTIVACIÓN (isSelected) ---
                val isSelected = when (item.route) {
                    // El botón de Transferencia se activa si la ruta actual está en el grupo transferRoutes
                    BottomNavItem.Transfer.route -> currentRoute in transferRoutes

                    // CORRECCIÓN: El botón Stats/Análisis se activa si la ruta actual es Home/Balance
                    BottomNavItem.Stats.route -> currentRoute in statsRoutes

                    // Los demás botones (Home, Wallet, Profile) se activan si la ruta coincide exactamente.
                    else -> item.route == currentRoute
                }

                // --- CORRECCIÓN: Color de fondo del indicador (Stats y Transfer) ---
                val indicatorColor =
                    if (isSelected && (item.route == BottomNavItem.Transfer.route || item.route == BottomNavItem.Stats.route)) {
                        FinWiseGreen // Verde activado para ambos grupos principales
                    } else {
                        Color.Transparent
                    }

                // Color del icono
                val iconColor =
                    if (isSelected && (item.route == BottomNavItem.Transfer.route || item.route == BottomNavItem.Stats.route)) {
                        FinWiseWhite // Blanco sobre indicador verde
                    } else if (isSelected) {
                        FinWiseDarkGreen // Oscuro para contraste con barra clara
                    } else {
                        FinWiseDarkGreen.copy(alpha = 0.6f) // No seleccionado
                    }


                // --- LÓGICA DE CLICK Y NAVEGACIÓN ---
                val finalOnClickAction: () -> Unit = when (item.route) {
                    BottomNavItem.Transfer.route -> {
                        { // transferNavigation
                            // Si la ruta actual es Income/Expense, forzamos popUpTo Transfer para volver a la principal
                            if (currentRoute in transferRoutes && currentRoute != transferTargetRoute) {
                                navController.navigate(transferTargetRoute) {
                                    popUpTo(transferTargetRoute) { inclusive = false }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            } else {
                                // Si ya estamos en transfer_route o la ruta es diferente, usamos la navegación normal.
                                defaultNavigation(transferTargetRoute)
                            }
                        }
                    }
                    BottomNavItem.Stats.route -> {
                        {
                            // CORRECCIÓN: El clic del botón Stats navega a la ruta de Balance/Home
                            if (currentRoute != statsTargetRoute) {
                                defaultNavigation(statsTargetRoute)
                            }
                        }
                    }
                    else -> {
                        {
                            // Si el botón no es Transfer ni Stats, simplemente navegamos a su ruta
                            defaultNavigation(item.route)
                        }
                    }
                }


                NavigationBarItem(
                    onClick = finalOnClickAction,
                    selected = isSelected,
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(indicatorColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.label,
                                tint = iconColor,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    label = null,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Transparent,
                        unselectedIconColor = Color.Transparent,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}
