package com.example.financeapp.components // Package remains 'components'

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
import com.example.financeApp.R

/**
 * Sealed class to define all possible bottom navigation items and their associated properties.
 */
sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {
    // 1. Home (Maps to home_nav.xml)
    object Home : BottomNavItem("home_route", R.drawable.home_nav, "Home")
    // 2. Stats / Analysis (Maps to analysis_nav.xml)
    object Stats : BottomNavItem("stats_route", R.drawable.analysis_nav, "Stats")
    // 3. Transfer / Exchange (Maps to transaction_nav.xml)
    object Transfer : BottomNavItem("transfer_route", R.drawable.transaction_nav, "Transfer")
    // 4. Wallet / Savings (Maps to category_nav.xml)
    object Wallet : BottomNavItem("wallet_route", R.drawable.category_nav, "Wallet")
    // 5. Profile / Account (Maps to profile_route", R.drawable.profile_nav, "Profile")
    object Profile : BottomNavItem("profile_route", R.drawable.profile_nav, "Profile")
}

@Composable
fun BottomNavBar(navController: NavController) { // FIX: Renamed function to BottomNavBar
    // 1. Define the list of all items
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Stats,
        BottomNavItem.Transfer,
        BottomNavItem.Wallet,
        BottomNavItem.Profile,
    )

    // Used to observe the current route to determine which item is selected
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Define the routes that belong to the bottom bar for easy checking
    val bottomBarRoutes = items.map { it.route }

    // Find the currently selected item route by matching against the known bottom bar routes
    val selectedRoute = items.find { it.route == currentRoute }?.route

    // Use Surface for the background and rounded corners to match the design
    Surface(
        // The background color of the bar itself (looks light gray/white in the image)
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        shape = MaterialTheme.shapes.extraLarge // Use a large shape for rounded corners
    ) {
        NavigationBar(
            // Set the background color of the NavigationBar transparent
            containerColor = Color.Transparent,
            tonalElevation = 0.dp // Remove default shadow
        ) {
            items.forEach { item ->
                // Check if the current route matches the item's route
                val isSelected = selectedRoute == item.route

                NavigationBarItem(
                    // Set the click action
                    onClick = {
                        // Navigate to the new destination only if it's not the current one
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                // Important: Pop up to the start destination of the bottom bar graph
                                popUpTo(bottomBarRoutes.first()) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    },
                    selected = isSelected,
                    icon = {
                        Icon(
                            // Now uses painterResource to load the custom XML drawable
                            painter = painterResource(id = item.icon),
                            contentDescription = item.label,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    },
                    // We don't need the label text for this specific design
                    label = null,
                    // Define the colors for the item states
                    colors = NavigationBarItemDefaults.colors(
                        // The primary color from your design (the bright green)
                        selectedIconColor = Color(0xFF1ABC9C),
                        // A dark, slightly transparent gray for inactive icons
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        indicatorColor = Color.Transparent // No background indicator needed
                    )
                )
            }
        }
    }
}
