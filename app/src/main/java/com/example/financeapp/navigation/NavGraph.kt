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
import com.example.financeapp.ui.screens.launch.OnboardingScreen
import com.example.financeapp.ui.screens.launch.SplashScreen

// Definicion de rutas de navegacion de la aplicacion
sealed class Screen(val route: String) {
    // Rutas de Inicio (Launch)
    object Splash : Screen("splash_route")
    object Onboarding : Screen("onboarding_route") // Ruta separada para el Onboarding
    object Home : Screen("home_route") // Pantalla principal

    // Rutas de Autenticación (Auth)
    object Login : Screen("login_route")
    object SignUp : Screen("signup_route")
    object ForgotPassword : Screen("forgot_password_route")

    // Rutas de Perfil y Configuración
    object Profile : Screen("profile_route")
    object Settings : Screen("settings_route")
    object Security : Screen("security_route")

    // Rutas de Features principales
    object Transactions : Screen("transactions_route")
    object Categories : Screen("categories_route")
    object SavingGoals : Screen("saving_goals_route")
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
            // El SplashScreen decide el destino (Home, Onboarding, o Login)
            SplashScreen(
                // Home (si ya esta autenticado)
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                // Onboarding (si es la primera vez)
                onNavigateToOnboarding = {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                // Login (si no esta autenticado, pero ya vio onboarding)
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // Pantalla Onboarding
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onNavigateToLogin = { navController.navigate(Screen.Login.route) },
                onNavigateToSignUp = { navController.navigate(Screen.SignUp.route) },
                onNavigateToForgotPassword = { navController.navigate(Screen.ForgotPassword.route) }
            )
        }

        // Rutas de Autenticacion
        composable(Screen.Login.route) {
            PlaceholderScreen("Login Screen") // Falta la logica
        }

        composable(Screen.SignUp.route) {
            PlaceholderScreen("Sign Up Screen") // Falta la logica
        }

        composable(Screen.ForgotPassword.route) {
            PlaceholderScreen("Forgot Password Screen") // Falta la logica
        }

        // Pantallas principales (ejemplo)
        composable(Screen.Home.route) {
            PlaceholderScreen("Home Screen") // Falta la logica
        }

        // Aca van mas pantallas (ejemplo)
        composable(Screen.SavingGoals.route) {
            PlaceholderScreen("Saving Goals Screen") // Falta la logica
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