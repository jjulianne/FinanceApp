package com.example.financeapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
        BottomNavItem.Stats,
        BottomNavItem.Transfer,
        BottomNavItem.Wallet,
        BottomNavItem.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoutes = items.map { it.route }
    val selectedRoute = items.find { it.route == currentRoute }?.route

    // El Surface principal de la BottomNavBar ahora usa FinWiseLightGreen
    // Las esquinas blancas alrededor de este Surface se manejarán en el Scaffold o en un Box envolvente.
    Surface(
        color = FinWiseLightGreen, // Fondo verde claro de la barra
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp) // Solo esquinas superiores redondeadas
    ) {
        NavigationBar(
            containerColor = Color.Transparent, // El color lo da el Surface padre
            tonalElevation = 0.dp,
            modifier = Modifier.height(72.dp)
        ) {
            items.forEach { item ->
                val isSelected = selectedRoute == item.route

                val indicatorColor = if (item.route == BottomNavItem.Transfer.route) {
                    if (isSelected) FinWiseGreen else Color.Transparent
                } else {
                    Color.Transparent
                }

                val iconColor = if (isSelected) FinWiseWhite else FinWiseDarkGreen

                NavigationBarItem(
                    onClick = {
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                popUpTo(bottomBarRoutes.first()) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
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
                                tint = if (item.route == BottomNavItem.Transfer.route && isSelected) FinWiseWhite else iconColor,
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
