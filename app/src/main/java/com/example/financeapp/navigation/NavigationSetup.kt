package com.example.financeapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeapp.ui.screens.SplashScreen

// Definicion de rutas de navegacion
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Launch : Screen("launch")
    object Home : Screen("home")
    // Aca van mas pantallas
}

@Composable
fun FinWiseNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Pantalla Splash (inicial)
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToNext = {
                    navController.navigate(Screen.Launch.route) {
                        // Eliminar Splash del back stack
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // Pantalla Launch
        composable(Screen.Launch.route) {
            // LaunchScreen() - Implementaras esto despues
            // Por ahora puedes poner un placeholder
            PlaceholderScreen("Launch Screen")
        }

        // Aca van mas pantallas
        composable(Screen.Home.route) {
            // HomeScreen()
            PlaceholderScreen("Home Screen")
        }
    }
}

// Pantalla temporal de placeholder
@Composable
private fun PlaceholderScreen(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 24.sp
        )
    }
}