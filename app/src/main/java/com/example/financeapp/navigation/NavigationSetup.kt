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
import com.example.financeapp.ui.screens.category.CategoriesScreen
import com.example.financeapp.ui.screens.category.FoodScreen
import com.example.financeapp.ui.screens.expense.add_expense.AddExpenseScreen

// Definicion de rutas de navegacion
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Launch : Screen("launch")
    object Home : Screen("home")
    // Aca van mas pantallas

    object Categories : Screen("category_route")
    object Food : Screen("food_route")

    object AddExpenses : Screen( "add_expense")



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


        composable(Screen.Categories.route) {
            CategoriesScreen(navController = navController, darkTheme = false)
        }
        composable(Screen.Food.route) {
            FoodScreen(
                navController = navController, darkTheme = false)
        }
        composable(Screen.AddExpenses.route) {
            AddExpenseScreen(
                darkTheme = false
            )
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